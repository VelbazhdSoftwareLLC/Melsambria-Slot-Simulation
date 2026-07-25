package eu.veldsoft.melsambria;

import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.UniformCrossover;
import io.jenetics.EliteSelector;
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

		game.simulate();
		if(game.model.valid == false) {
			return Integer.MAX_VALUE;
		}

		return (int)(1000000 * game.score(g -> Math.abs(0.968D -
		                                  (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) +
		                                  (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ));
	}

	public static void main(String[] args) {
		Melsambria game = new Melsambria();

		// game.model.baseReels = new int[][] {
		// 	{8, 7, 4, 3, 5, 7, 5, 7, 5, 6, 2, 5, 7, 4, 9, 3, 7, 5, 9, 4, 4, 6, 2, 10, 8, 4, 2, 9, 7, 10, 8, 7, 3, 4, 6, 7, 4, 9, 7},
		// 	{8, 9, 3, 8, 6, 10, 4, 8, 9, 7, 7, 4, 10, 3, 10, 5, 9, 2, 4, 3, 8, 6, 9, 4, 10, 8, 2, 10, 7, 10, 2, 10, 3, 6, 2, 2, 9, 5, 10, 9},
		// 	{3, 2, 5, 8, 8, 6, 8, 8, 7, 6, 7, 10, 5, 0, 7, 9, 4, 8, 5, 2, 0, 10, 3, 3, 3, 7, 7, 9, 5, 6, 2, 8, 5, 5, 2, 0},
		// 	{2, 10, 1, 5, 7, 10, 9, 5, 5, 9, 1, 3, 8, 2, 6, 7, 3, 4, 9, 5, 2, 5, 9, 4, 8, 8, 7, 10, 1, 5, 6, 6, 4, 9, 4, 9, 9, 10, 4, 4, 10, 1, 7, 2, 4, 6, 2, 4, 7, 3, 7, 10, 7, 0, 9, 6, 7, 1, 7, 2, 1, 9, 9, 10, 2, 10, 9, 5},
		// 	{7, 2, 7, 5, 1, 1, 7, 10, 1, 0, 1, 7, 0, 7, 2, 10, 5, 0, 9, 6, 8, 10, 0, 8, 10, 8, 0, 3, 3, 5, 1, 5, 4, 7, 4, 5, 10, 7, 0, 1, 1, 10, 2, 5, 7, 2, 8, 10, 0, 5, 4, 9, 1, 0, 8, 4, 2, 1, 1, 5, 4, 10, 5, 4, 10, 8, 6, 5, 4, 10, 7, 1, 1, 5, 10, 5, 1, 4, 3, 7, 3, 10, 10, 9, 2, 5, 3, 2, 7, 1, 3, 7, 1, 2, 8, 6, 8, 1, 5, 4, 4, 8, 10, 3, 7, 7, 3, 1, 2, 8, 8, 9, 4, 1, 5, 6, 5, 8, 4, 0, 10, 2, 10, 3, 1, 0, 6, 4, 7, 0, 3, 4, 8, 9, 4, 8, 0, 6, 8, 4, 7, 0, 0, 9, 0, 1, 6, 9, 3, 7, 2, 0, 5, 1, 10, 0, 8, 8, 4, 3, 9, 10, 8, 4, 3, 4, 5, 2, 5, 7, 1, 6, 8, 6, 9, 10, 10, 5, 5, 1, 2, 3, 6, 5, 6, 6, 8, 0, 6, 2, 6, 1, 9, 7, 9, 1, 10, 2, 7, 10, 5, 4, 7, 1, 0, 7, 2, 5, 5, 7, 6, 5, 1, 9, 1, 3, 7, 5, 10, 8, 2, 0, 5, 7, 8, 3, 6, 4, 9, 7, 2, 9, 6, 4, 4, 7, 3, 7, 0, 7, 10, 2, 5, 9, 3, 2, 8, 2, 0, 6, 9, 3, 8, 5, 10, 6, 2, 5, 7, 6, 0, 3, 7, 3, 9, 9, 3, 8, 8, 7, 8, 5, 8, 2, 0, 9, 8, 3, 4, 4, 0, 6, 5, 3, 0, 8, 8, 8, 4, 1, 2, 7, 10, 5, 1, 2, 0, 4, 3, 8, 5, 2, 3, 8, 1, 10, 4, 2, 2, 8, 10}
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

		for(int i = 1; i < 100; i++) {
			population.add(Genotype.of(IntegerChromosome.of(genes)));
			Collections.shuffle(Arrays.asList(genes));
		}

		Genotype<IntegerGene> factory = Genotype.of(
		                                    IntegerChromosome.of(0, unique.size() - 1, values.size()));

		Engine<IntegerGene, Integer> engine = Engine.builder(Main::evaluation, factory)
		                                      .populationSize(population.size())
		                                      .optimize(Optimize.MINIMUM)
		                                      .survivorsFraction(0.05)
		                                      .survivorsSelector(new EliteSelector<>())
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
