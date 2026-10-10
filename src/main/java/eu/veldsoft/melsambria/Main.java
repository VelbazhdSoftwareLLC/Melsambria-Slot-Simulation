package eu.veldsoft.melsambria;

import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.UniformCrossover;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	private static double bestFitness = Double.MAX_VALUE;

	private static double evaluation(Genotype<IntegerGene> genotype) {
		Melsambria game = new Melsambria();
		for (int i = 0, index = 0; i < game.model.baseReels.length; i++) {
			for (int j = 0; j < game.model.baseReels[i].length; j++, index++) {
				game.model.baseReels[i][j] = genotype.chromosome().as(IntegerChromosome.class).get(index).intValue();
			}
		}

		// TODO ANN for estimation of the RTP by mapping 90-100% to 0.0-1.0 sigmoid
		// function output. The estimated RTP can be used as a fitness value, and only
		// the most promising configurations can be evaluated by a Monte Carlo
		// simulation.

		if (bestFitness > 9) {
			game.statistics.numberOfSimulations = 100_000;
		} else if (bestFitness > 6) {
			game.statistics.numberOfSimulations = 500_000;
		} else if (bestFitness > 3) {
			game.statistics.numberOfSimulations = 1_000_000;
		} else if (bestFitness > 1) {
			game.statistics.numberOfSimulations = 5_000_000;
		} else {
			game.statistics.numberOfSimulations = 10_000_000;
		}

		game.simulate();
		if (game.model.valid == false) {
			return Double.MAX_VALUE;
		}

		return game.score(g -> 10D *
				Math.abs(0.968D - (double) g.statistics.wonMoney / (double) g.statistics.lostMoney) +
		// 1 -
				(double) g.statistics.baseHitFrequency / (double) g.statistics.totalNumberOfGames);
	}

	private static void print(PrintStream out, Genotype<IntegerGene> solution) {
		Melsambria game = new Melsambria();
		for (int i = 0, index = 0; i < game.model.baseReels.length; i++) {
			for (int j = 0; j < game.model.baseReels[i].length; j++, index++) {
				game.model.baseReels[i][j] = solution.chromosome().as(IntegerChromosome.class).get(index).intValue();
			}
		}
		out.println(Arrays.deepToString(game.model.baseReels).replace("]", "}").replace("[", "{")
				.replace("}, {", "},\n\t{").replace("{{", "{\n\t{").replace("}}", "}\n}"));
	}

	public static void main(String[] args) {
		Melsambria game = new Melsambria();

		// game.model.baseReels = new int[][] {
		// };
		// game.simulate(args);
		// System.out.println("Score: " + game.score(g -> Math.abs(0.968D -
		// (double) g.statistics.wonMoney / (double) g.statistics.lostMoney) +
		// (double) g.statistics.baseHitFrequency /
		// (double) g.statistics.totalNumberOfGames));
		// System.exit(0);

		game = new Melsambria();
		List<Integer> values = Arrays.stream(game.model.baseReels)
				.flatMapToInt(Arrays::stream).boxed().toList();
		List<Integer> unique = new ArrayList<>(values.stream()
				.distinct().sorted().toList());

		List<Genotype<IntegerGene>> population = new ArrayList<>();

		IntegerGene[] genes = values.stream()
				.map(index -> IntegerGene.of(index, 0, unique.size() - 1))
				.toArray(IntegerGene[]::new);

		for (int i = 0; i < 50; i++) {
			population.add(Genotype.of(IntegerChromosome.of(genes)));
		}
		for (int i = 0; i < 50; i++) {
			population.add(Genotype.of(IntegerChromosome.of(genes)));
			Collections.shuffle(Arrays.asList(genes));
		}

		Genotype<IntegerGene> factory = Genotype.of(
				IntegerChromosome.of(0, unique.size(), values.size()));

		Engine<IntegerGene, Double> engine = Engine.builder(Main::evaluation, factory)
				.populationSize(population.size())
				.optimize(Optimize.MINIMUM)
				// .survivorsFraction(0.05)
				// .survivorsSelector(new EliteSelector<>())
				.alterers(
						new UniformCrossover<>(0.5),
						new Mutator<>(0.05))
				.build();

		Genotype<IntegerGene> result = engine.stream(population).limit(100).peek(intermediate -> {
			print(System.err, intermediate.bestPhenotype().genotype());
			System.out.println(LocalTime.now() + "\t" +
					intermediate.generation() + "\t" +
					(bestFitness = intermediate.bestFitness()));
		}).collect(EvolutionResult.toBestGenotype());

		print(System.out, result);
	}
}
