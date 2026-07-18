public class Main {
	public static void main(String[] args) {
		Melsambria game = new Melsambria();

		game.simulate(args);

		System.out.println("Score: " + game.score(g -> Math.abs(0.968D -
		                   (double)g.statistics.wonMoney / (double)g.statistics.lostMoney) +
		                   (double)g.statistics.baseHitFrequency / (double)g.statistics.totalNumberOfGames ) );
	}
}
