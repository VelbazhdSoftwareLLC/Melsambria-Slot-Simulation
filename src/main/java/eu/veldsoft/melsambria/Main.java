package eu.veldsoft.melsambria;

import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.UniformCrossover;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	private static int evaluation(Genotype<IntegerGene> genotype) {
		Melsambria game = new Melsambria();
		for(int i = 0, index=0; i < game.model.baseReels.length; i++) {
			for(int j = 0; j < game.model.baseReels[i].length; j++, index++) {
				game.model.baseReels[i][j] = genotype.chromosome().
				                             as(IntegerChromosome.class).get(index).intValue();
			}
		}

		//TODO ANN for estimation of the RTP by mapping 90-100% to 0.0-1.0 sigmoid function output. The estimated RTP can be used as a fitness value, and only the most promising configurations can be evaluated by a Monte Carlo simulation.

		game.simulate();
		if(game.model.valid == false) {
			return Integer.MAX_VALUE;
		}

		return (int)(1000000 * game.score(g -> 10D * Math.abs(0.968D -
		                                  (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) -
		                                  (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ));
	}

	public static void main(String[] args) {
		Melsambria game = new Melsambria();

		// game.model.baseReels = new int[][] {
		// 	{6, 3, 10, 7, 6, 4, 6, 9, 4, 10, 4, 9, 9, 10, 7, 8, 2, 6, 10, 10, 7, 1, 8, 3, 10, 6, 8, 2, 5, 1, 10, 10, 8, 9, 2, 8, 5, 6, 1},
		// 	{2, 10, 7, 8, 9, 7, 9, 9, 8, 3, 3, 2, 5, 6, 2, 5, 0, 4, 9, 3, 9, 7, 9, 2, 5, 10, 7, 8, 6, 1, 5, 10, 7, 10, 4, 3, 8, 4, 5, 2},
		// 	{2, 4, 7, 7, 10, 9, 9, 5, 8, 6, 7, 10, 9, 5, 10, 4, 8, 5, 10, 9, 10, 5, 9, 8, 9, 2, 9, 10, 3, 6, 5, 7, 7, 7, 8, 7},
		// 	{4, 2, 10, 3, 3, 6, 7, 8, 4, 6, 5, 1, 3, 10, 8, 3, 10, 10, 2, 4, 8, 9, 3, 9, 8, 6, 9, 10, 10, 7, 9, 7, 10, 10, 3, 4, 7, 9, 5, 9, 6, 5, 5, 7, 4, 6, 2, 2, 9, 7, 8, 8, 3, 6, 10, 1, 5, 1, 6, 8, 3, 4, 6, 4, 1, 2, 3, 2},
		// 	{2, 0, 9, 1, 4, 8, 0, 8, 5, 0, 5, 5, 4, 0, 7, 5, 9, 6, 8, 3, 8, 8, 8, 6, 9, 5, 2, 8, 10, 10, 9, 5, 4, 3, 0, 3, 4, 2, 2, 10, 8, 0, 9, 1, 8, 1, 1, 9, 3, 7, 3, 8, 6, 5, 6, 1, 1, 4, 3, 2, 5, 0, 3, 6, 10, 3, 8, 2, 0, 1, 9, 7, 4, 6, 3, 10, 4, 2, 3, 0, 3, 4, 2, 0, 9, 7, 8, 2, 8, 9, 2, 1, 3, 5, 7, 3, 10, 2, 8, 6, 4, 2, 2, 9, 2, 6, 10, 7, 1, 1, 10, 4, 4, 10, 5, 9, 3, 0, 3, 9, 4, 10, 8, 10, 7, 1, 10, 10, 4, 6, 1, 8, 9, 3, 10, 10, 10, 1, 6, 10, 10, 2, 5, 7, 3, 10, 7, 3, 1, 7, 8, 7, 6, 0, 5, 9, 9, 2, 8, 0, 0, 4, 7, 6, 10, 6, 4, 8, 8, 3, 5, 10, 9, 10, 4, 3, 6, 0, 8, 5, 5, 7, 0, 3, 9, 7, 0, 2, 5, 3, 7, 1, 0, 2, 8, 5, 6, 1, 7, 7, 1, 7, 5, 9, 6, 3, 1, 6, 3, 6, 7, 9, 5, 2, 10, 1, 3, 5, 3, 8, 8, 5, 2, 5, 4, 7, 4, 5, 9, 3, 0, 7, 8, 4, 1, 3, 3, 9, 2, 6, 3, 4, 7, 0, 7, 3, 10, 3, 2, 3, 10, 5, 7, 4, 4, 8, 9, 5, 0, 9, 9, 9, 1, 5, 2, 8, 6, 7, 10, 6, 5, 5, 5, 3, 5, 6, 3, 5, 0, 9, 8, 7, 10, 7, 5, 5, 9, 2, 7, 9, 7, 2, 1, 5, 0, 9, 3, 9, 6, 10, 6, 1, 9, 3, 9, 3, 3, 5, 4, 4, 8}
		// };
		// game.simulate(args);
		// System.out.println("Score: " + game.score(g -> Math.abs(0.968D -
		//                    (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) +
		//                    (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ) );
		// System.exit( 0 );

		game = new Melsambria();
		List<Integer> values = Arrays.stream(game.model.baseReels)
		                       .flatMapToInt(Arrays::stream).boxed().toList();
		List<Integer> unique = new ArrayList<>(values.stream()
		                                       .distinct().sorted().toList());

		List<Genotype<IntegerGene>> population = new ArrayList<>();

		IntegerGene[] genes = values.stream()
		                      .map(index -> IntegerGene.of(index, 0, unique.size() - 1))
		                      .toArray(IntegerGene[]::new);

		for(int i = 0; i < 50; i++) {
			population.add(Genotype.of(IntegerChromosome.of(genes)));
		}
		for(int i = 0; i < 50; i++) {
			population.add(Genotype.of(IntegerChromosome.of(genes)));
			Collections.shuffle(Arrays.asList(genes));
		}

		Genotype<IntegerGene> factory = Genotype.of(
		                                    IntegerChromosome.of(0, unique.size(), values.size()));

		Engine<IntegerGene, Integer> engine = Engine.builder(Main::evaluation, factory)
		                                      .populationSize(population.size())
		                                      .optimize(Optimize.MINIMUM)
		                                      //   .survivorsFraction(0.05)
		                                      //   .survivorsSelector(new EliteSelector<>())
		                                      .alterers(
		                                              new UniformCrossover<>(0.5),
		                                              new Mutator<>(0.05)
		                                      )
		                                      .build();

		Genotype<IntegerGene> result = engine.stream(population).
		limit(100).peek(intermediate -> {
			System.out.println(LocalTime.now() + "\t" +
			                   intermediate.generation() + "\t" +
			                   intermediate.bestFitness());
		}).
		collect(EvolutionResult.toBestGenotype());

		for(int i = 0, index=0; i < game.model.baseReels.length; i++) {
			for(int j = 0; j < game.model.baseReels[i].length; j++, index++) {
				game.model.baseReels[i][j] = result.chromosome().
				                             as(IntegerChromosome.class).get(index).intValue();
			}
		}
		System.out.println(Arrays.deepToString(game.model.baseReels).
		                   replace("]", "}").
		                   replace("[", "{").
		                   replace("}, {", "},\n\t{").
		                   replace("{{", "{\n\t{").
		                   replace("}}", "}\n}")
		                  );
	}
}
