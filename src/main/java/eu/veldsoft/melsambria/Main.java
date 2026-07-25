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
		// 	{3, 6, 10, 6, 6, 2, 2, 4, 5, 7, 5, 10, 6, 5, 5, 3, 0, 2, 5, 3, 10, 2, 3, 5, 7, 5, 4, 5, 7, 6, 8, 2, 6, 7, 5, 10, 5, 3, 8},
		// 	{3, 9, 7, 9, 7, 9, 8, 6, 2, 7, 2, 7, 7, 3, 2, 9, 8, 10, 2, 8, 9, 8, 10, 7, 7, 7, 4, 7, 8, 8, 9, 9, 2, 3, 8, 7, 4, 7, 9, 4},
		// 	{7, 9, 3, 5, 0, 8, 4, 10, 9, 8, 10, 3, 9, 3, 8, 10, 3, 7, 5, 6, 9, 9, 4, 2, 9, 10, 2, 5, 5, 6, 4, 9, 2, 7, 8, 8},
		// 	{5, 2, 1, 8, 7, 4, 7, 10, 2, 10, 1, 4, 8, 6, 9, 3, 4, 2, 10, 6, 5, 9, 6, 7, 8, 5, 2, 9, 4, 7, 7, 6, 7, 5, 7, 2, 5, 8, 8, 2, 7, 10, 9, 2, 1, 4, 8, 4, 9, 0, 4, 8, 1, 10, 7, 7, 10, 8, 3, 9, 4, 6, 0, 8, 1, 6, 2, 3},
		// 	{4, 9, 6, 0, 4, 2, 4, 4, 6, 4, 2, 9, 6, 2, 5, 5, 4, 5, 10, 10, 0, 10, 3, 3, 6, 0, 7, 6, 10, 8, 8, 7, 0, 4, 10, 7, 8, 1, 6, 9, 7, 3, 5, 9, 8, 3, 1, 3, 6, 5, 5, 7, 0, 4, 9, 2, 6, 7, 3, 9, 1, 5, 7, 1, 3, 1, 1, 4, 0, 9, 7, 5, 7, 1, 7, 1, 9, 6, 1, 0, 6, 9, 6, 2, 1, 8, 6, 1, 10, 5, 9, 3, 6, 8, 0, 3, 5, 5, 10, 5, 1, 0, 0, 1, 4, 5, 3, 10, 6, 2, 7, 1, 1, 1, 9, 7, 3, 6, 4, 9, 2, 2, 5, 3, 6, 0, 4, 7, 0, 3, 8, 5, 4, 5, 9, 8, 1, 9, 3, 7, 9, 10, 4, 10, 0, 3, 2, 2, 7, 8, 6, 0, 3, 2, 0, 5, 9, 5, 3, 3, 9, 1, 4, 0, 4, 3, 10, 3, 6, 9, 10, 5, 0, 9, 3, 3, 1, 10, 10, 10, 7, 9, 6, 5, 3, 10, 7, 6, 1, 9, 4, 4, 9, 1, 9, 1, 0, 10, 1, 8, 5, 6, 2, 6, 8, 4, 9, 0, 1, 1, 6, 9, 3, 9, 2, 10, 2, 3, 3, 8, 3, 3, 0, 2, 6, 4, 7, 8, 1, 10, 7, 10, 1, 3, 4, 0, 4, 10, 8, 4, 6, 8, 5, 2, 2, 4, 3, 8, 10, 0, 6, 1, 3, 1, 0, 7, 7, 4, 1, 1, 8, 6, 5, 9, 3, 7, 1, 6, 2, 5, 9, 10, 8, 8, 6, 3, 9, 1, 4, 8, 6, 2, 10, 9, 9, 2, 2, 8, 1, 6, 2, 10, 1, 5, 6, 4, 7, 9, 5, 7, 6, 3, 4, 2, 5, 9, 9, 7, 8, 0, 6}
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
