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

		return (int)(1000000 * game.score(g -> 10D * Math.abs(0.968D -
		                                  (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) -
		                                  (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ));
	}

	public static void main(String[] args) {
		Melsambria game = new Melsambria();

		// game.model.baseReels = new int[][] {
		// 	{5, 5, 9, 4, 10, 7, 8, 5, 8, 5, 5, 3, 2, 10, 9, 5, 4, 9, 4, 8, 10, 8, 5, 10, 3, 9, 10, 7, 8, 5, 10, 6, 5, 4, 8, 8, 8, 3, 6},
		// 	{0, 10, 5, 10, 8, 2, 8, 8, 0, 6, 3, 4, 2, 6, 7, 6, 1, 5, 7, 1, 9, 3, 5, 4, 3, 7, 9, 4, 6, 8, 6, 7, 4, 8, 7, 2, 9, 0, 2, 6},
		// 	{2, 7, 9, 4, 3, 4, 9, 10, 3, 9, 3, 6, 7, 6, 6, 7, 2, 10, 9, 6, 7, 6, 4, 7, 9, 7, 9, 7, 2, 7, 10, 10, 5, 10, 10, 5},
		// 	{5, 2, 10, 6, 9, 3, 9, 10, 8, 8, 1, 3, 4, 10, 8, 8, 7, 1, 2, 0, 3, 1, 5, 5, 5, 10, 2, 8, 10, 4, 6, 7, 3, 0, 3, 9, 10, 7, 8, 10, 1, 10, 2, 8, 5, 7, 3, 1, 8, 6, 9, 9, 6, 9, 7, 10, 6, 2, 8, 4, 7, 7, 7, 7, 9, 2, 6, 5},
		// 	{8, 1, 1, 4, 0, 9, 1, 5, 4, 3, 2, 1, 4, 3, 4, 9, 0, 6, 7, 4, 4, 4, 2, 1, 3, 8, 3, 5, 4, 10, 4, 6, 6, 3, 5, 0, 9, 7, 0, 1, 5, 7, 9, 0, 9, 3, 6, 7, 8, 7, 0, 7, 2, 8, 9, 8, 1, 10, 0, 10, 7, 6, 3, 1, 10, 2, 9, 5, 2, 1, 3, 8, 0, 4, 9, 8, 3, 5, 3, 8, 3, 10, 7, 0, 9, 6, 8, 8, 8, 1, 2, 8, 3, 4, 4, 0, 6, 8, 10, 0, 2, 2, 9, 2, 0, 9, 0, 9, 10, 7, 2, 10, 6, 9, 8, 0, 1, 6, 7, 5, 1, 3, 3, 2, 9, 3, 10, 9, 1, 3, 9, 10, 9, 8, 1, 10, 9, 2, 6, 7, 5, 5, 5, 4, 3, 3, 5, 6, 6, 3, 7, 0, 4, 9, 4, 1, 10, 5, 4, 6, 7, 4, 10, 6, 7, 1, 4, 3, 3, 7, 6, 5, 10, 3, 3, 7, 9, 0, 2, 4, 3, 7, 0, 7, 1, 8, 0, 5, 1, 10, 9, 1, 4, 10, 7, 2, 4, 1, 5, 4, 10, 4, 8, 8, 6, 10, 8, 3, 7, 1, 4, 8, 9, 6, 1, 5, 6, 9, 7, 6, 7, 6, 1, 6, 7, 0, 4, 8, 0, 2, 4, 3, 4, 9, 8, 8, 1, 8, 0, 3, 3, 1, 6, 8, 5, 5, 2, 9, 9, 6, 3, 5, 9, 0, 3, 6, 7, 6, 10, 7, 4, 8, 10, 9, 3, 1, 10, 2, 10, 2, 1, 7, 4, 1, 5, 10, 7, 9, 0, 5, 8, 6, 0, 3, 7, 1, 5, 3, 10, 6, 1, 9, 10, 2, 3, 0, 5, 9, 0, 3, 6, 2, 7, 4, 7, 0, 4, 9, 5, 5, 8}
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
		                                    IntegerChromosome.of(0, unique.size() - 1, values.size()));

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
