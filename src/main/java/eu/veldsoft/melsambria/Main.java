package eu.veldsoft.melsambria;

import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	private static int evaluation(Genotype<IntegerGene> genotype) {
		Melsambria game = new Melsambria();
		for(int i = 0, index=0; i < game.model.baseReels.length; i++) {
			for(int j = 0; j < game.model.baseReels[i].length; j++, index++) {
				game.model.baseReels[i][j] = genotype.chromosome().as(IntegerChromosome.class).get(index).intValue();
			}
		}

		game.simulate();

		return (int)(1000000 * game.score(g -> (g.model.valid) ? (Math.abs(0.968D -
		                                  (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) +
		                                  (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ) : 2D));
	}

	public static void main(String[] args) {
		// Melsambria game = new Melsambria();

		// game.simulate(args);

		// System.out.println("Score: " + game.score(g -> Math.abs(0.968D -
		//                    (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) +
		//                    (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ) );

		Melsambria game = new Melsambria();

		List<Integer> values = Arrays.stream(game.model.baseReels)
		                       .flatMapToInt(Arrays::stream).boxed().toList();

		List<Integer> unique = new ArrayList<>(values.stream()
		                                       .distinct().sorted().toList());

		List<Genotype<IntegerGene>> population = new ArrayList<>();

		IntegerGene[] genes = values.stream()
		                      .map(index -> IntegerGene.of(index, 0, unique.size() - 1))
		                      .toArray(IntegerGene[]::new);

		for(int i = 1; i < 100; i++) {
			population.add(Genotype.of(IntegerChromosome.of(genes)));
			Collections.shuffle(Arrays.asList(genes));
		}

		Genotype<IntegerGene> factory = Genotype.of(
		                                    IntegerChromosome.of(0, unique.size() - 1, values.size()));

		Engine<IntegerGene, Integer> engine = Engine.builder(Main::evaluation, factory)
		                                      .populationSize(population.size()).build();

		Genotype<IntegerGene> result = engine.stream(population)
		                               .limit(100).collect(EvolutionResult.toBestGenotype());

		System.out.println(result);
	}
}
