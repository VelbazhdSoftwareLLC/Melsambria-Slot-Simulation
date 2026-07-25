package eu.veldsoft.melsambria;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToDoubleFunction;

public class Melsambria {
	public class Model {
		public boolean valid = true;

		public int[][] baseReels = {
			{
				9, 5, 10, 7, 4, 8, 11, 6, 9, 0, 11, 5, 9, 10, 3, 7, 4, 11, 8, 6, 9, 2, 1, 6, 10, 11, 3, 10, 5, 11,
				2, 10, 7, 4, 10, 8, 7, 11, 3,
			},
			{
				9, 3, 11, 5, 8, 7, 2, 1, 9, 6, 0, 11, 3, 8, 9, 4, 10, 6, 7, 9, 5, 11, 2, 10, 3, 8, 6, 1, 10, 5, 7,
				4, 1, 2, 7, 8, 6, 11, 4, 10,
			},
			{
				9, 5, 7, 4, 8, 11, 6, 9, 7, 8, 3, 11, 2, 10, 5, 9, 6, 11, 7, 4, 9, 2, 10, 6, 7, 8, 10, 5, 0, 6,
				11, 4, 10, 8, 3, 7,
			},
			{
				11, 4, 8, 7, 5, 8, 6, 9, 0, 4, 8, 10, 5, 7, 9, 4, 11, 9, 6, 8, 0, 7, 10, 3, 9, 11, 4, 8, 7, 6, 9,
				11, 5, 8, 6, 10, 8, 6, 7, 11, 8, 7, 5, 9, 2, 11, 10, 4, 7, 10, 5, 11, 9, 0, 10, 9, 4, 11, 8,
				5, 10, 6, 7, 1, 9, 7, 3, 10,
			},
			{
				8, 5, 9, 11, 6, 7, 8, 5, 10, 11, 8, 9, 0, 4, 10, 9, 3, 8, 10, 5, 9, 11, 6, 0, 10, 8, 11, 6, 7, 8,
				2, 9, 10, 3, 7, 8, 5, 10, 11, 4, 7, 9, 4, 8, 10, 3, 9, 11, 6, 10, 11, 3, 8, 11, 7, 5, 10, 11,
				6, 9, 2, 10, 7, 4, 11, 5, 7, 4, 11, 5, 8, 9, 6, 1, 10, 9, 6, 7, 8, 5, 9, 11, 6, 7, 8, 5, 10,
				11, 4, 7, 9, 3, 8, 10, 5, 9, 11, 6, 10, 8, 0, 10, 6, 7, 8, 2, 9, 11, 0, 3, 7, 8, 5, 10, 0,
				11, 4, 7, 9, 4, 8, 10, 3, 9, 11, 6, 7, 11, 3, 8, 11, 7, 5, 10, 11, 6, 9, 10, 0, 9, 2, 8, 7,
				4, 11, 5, 7, 4, 11, 5, 8, 9, 6, 10, 9, 6, 7, 8, 5, 9, 11, 6, 7, 8, 5, 10, 9, 8, 11, 0, 4, 7,
				9, 3, 8, 10, 5, 9, 11, 6, 10, 8, 7, 6, 10, 8, 2, 9, 11, 3, 7, 8, 5, 10, 11, 4, 7, 9, 4, 8,
				10, 3, 9, 11, 6, 10, 11, 3, 9, 11, 7, 5, 10, 11, 6, 10, 2, 8, 7, 4, 11, 5, 7, 4, 11, 5, 8, 9,
				6, 1, 10, 9, 6, 7, 8, 5, 9, 11, 6, 7, 8, 5, 10, 11, 4, 7, 9, 3, 8, 10, 5, 9, 11, 6, 10, 8,
				0, 10, 6, 7, 8, 2, 9, 11, 3, 7, 8, 5, 10, 0, 11, 4, 7, 9, 4, 8, 10, 3, 9, 11, 6, 10, 11, 3,
				8, 11, 7, 5, 10, 9, 6, 11, 8, 9, 2, 10, 7, 4, 11, 5, 7, 4, 11, 5, 8, 9, 6, 10, 9, 6, 7,
			},
		};

		public int[][] freeReels = {
			{
				9, 5, 10, 7, 4, 8, 11, 6, 9, 5, 0, 11, 3, 10, 5, 9, 7, 4, 5, 10, 6, 9, 2, 8, 6, 11, 1, 3, 8, 10,
				5, 11, 2, 10, 7, 4, 8, 1, 7, 3,
			},
			{
				9, 2, 11, 4, 10, 6, 7, 3, 4, 11, 8, 5, 7, 9, 2, 0, 11, 4, 8, 9, 6, 7, 3, 11, 6, 9, 2, 8, 3, 10,
				11, 5, 7, 9, 2, 10, 4, 6, 0, 7, 3, 4, 9, 5, 8, 10, 4, 7, 9, 2, 11, 10, 5, 8, 6, 1, 10, 7, 4,
				6, 9, 1, 0, 11, 3, 8, 5, 7,
			},
			{
				10, 5, 11, 9, 6, 10, 8, 2, 11, 7, 4, 10, 3, 9, 0, 11, 8, 3, 7, 10, 5, 9, 11, 6, 8, 10, 3, 7, 11, 4,
				10, 2, 11, 5, 9, 6, 10, 4, 11, 8, 2, 7,
			},
			{
				4, 8, 3, 7, 2, 5, 8, 6, 9, 0, 4, 8, 10, 5, 7, 9, 4, 11, 3, 9, 8, 6, 7, 10, 3, 9, 11, 4, 8, 7, 3,
				10, 8, 5, 11, 6, 9, 8, 5, 7, 11, 2, 8, 7, 6, 9, 2, 11, 10, 4, 7, 10, 5, 11, 0, 10, 9, 4, 11,
				7, 3, 10, 6, 8, 1, 9, 7, 2, 10, 4, 8, 3, 7, 2, 5, 8, 6, 9, 0, 4, 8, 10, 3, 7, 1, 9, 4, 11, 3,
				9, 8, 6, 7, 10, 5, 9, 11, 4, 8, 7, 3, 10, 11, 5, 8, 6, 9, 8, 5, 7, 11, 2, 9, 7, 6, 8, 2, 11,
				10, 5, 7, 10, 4, 11, 0, 10, 9, 4, 7, 8, 2, 10, 6, 11, 1, 9, 7, 3, 10,
			},
			{
				8, 5, 9, 11, 6, 7, 8, 5, 10, 11, 4, 7, 9, 6, 8, 10, 5, 9, 11, 3, 10, 6, 7, 8, 5, 10, 11, 4, 7, 9,
				6, 8, 11, 3, 9, 10, 4, 11, 5, 10, 11, 7, 3, 8, 9, 6, 11, 10, 9, 2, 8, 7, 4, 11, 5, 9, 6, 11,
				5, 8, 7, 4, 10, 9, 6, 7, 8, 5, 9, 11, 6, 7, 8, 5, 10, 4, 7, 9, 3, 8, 10, 5, 9, 1, 11, 6, 10,
				8, 9, 6, 7, 8, 2, 10, 11, 3, 7, 8, 5, 10, 0, 3, 7, 9, 4, 8, 11, 3, 9, 10, 6, 11, 4, 7, 8, 5,
				11, 6, 9, 7, 11, 2, 8, 10, 4, 9, 5, 7, 6, 11, 5, 8, 4, 7, 10, 9, 6, 7,
			},
		};

		public int[][] sharkReels = new int[][] {
			{ 9, 5, 7, 10, 4, 8, 6, 9, 2, 0, 6, 5, 8, 3, 11, 5, 4, 8, 6, 2, 1, 11, 3, 5, 2, 10, 4, 1, 7, 3, },
			{
				9, 2, 10, 4, 11, 6, 7, 3, 4, 5, 7, 9, 2, 0, 11, 4, 8, 7, 6, 11, 3, 7, 1, 6, 9, 5, 11, 3, 10, 8, 5,
				7, 9, 2, 10, 4, 6, 7, 3, 5, 9, 4, 8, 10, 5, 9, 6, 11, 4, 8, 6, 1, 7, 4, 6, 9, 3, 8, 5, 7,
			},
			{
				10, 5, 11, 6, 8, 7, 4, 10, 3, 11, 0, 9, 8, 10, 5, 11, 9, 6, 8, 3, 11, 7, 4, 10, 2, 11, 5, 6, 10, 4,
				9, 2, 7,
			},
			{
				1, 4, 8, 3, 7, 2, 5, 6, 9, 4, 8, 10, 5, 7, 1, 9, 4, 11, 3, 9, 6, 8, 0, 10, 3, 11, 4, 8, 7, 3, 10,
				1, 11, 5, 8, 6, 9, 8, 6, 7, 11, 2, 8, 7, 5, 9, 2, 11, 4, 7, 10, 5, 9, 0, 10, 9, 4, 11, 8, 2,
				7, 6, 10, 1, 9, 7, 3, 10,
			},
			{
				8, 5, 9, 11, 6, 7, 8, 5, 10, 11, 4, 7, 9, 3, 8, 10, 5, 9, 11, 6, 10, 8, 7, 6, 10, 8, 2, 9, 11, 3,
				7, 8, 5, 10, 0, 11, 4, 7, 9, 6, 8, 10, 3, 9, 11, 4, 7, 11, 3, 6, 11, 7, 5, 11, 6, 9, 8, 0,
				9, 2, 8, 7, 4, 11, 5, 7, 4, 11, 5, 8, 9, 6, 10, 9, 6, 7, 8, 5, 9, 11, 6, 7, 8, 5, 10, 11, 4,
				7, 9, 3, 8, 10, 5, 9, 11, 6, 10, 8, 0, 10, 6, 7, 8, 2, 9, 11, 4, 7, 8, 5, 10, 11, 3, 7, 9,
				4, 8, 10, 3, 9, 11, 6, 10, 1, 3, 10, 11, 7, 5, 10, 4, 6, 9, 8, 0, 9, 4, 8, 7, 2, 11, 5, 7, 4,
				11, 5, 8, 9, 6, 10, 9, 6, 7,
			},
		};

		public int[][] lines = {
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 1, 1, 1, 1, 1 },
			new int[] { 2, 2, 2, 2, 2 },
			new int[] { 0, 1, 2, 1, 0 },
			new int[] { 2, 1, 0, 1, 2 },
			new int[] { 0, 0, 1, 0, 0 },
			new int[] { 2, 2, 1, 2, 2 },
			new int[] { 1, 2, 2, 2, 1 },
			new int[] { 1, 0, 0, 0, 1 },
			new int[] { 1, 0, 1, 0, 1 },
			new int[] { 1, 2, 1, 2, 1 },
			new int[] { 0, 1, 0, 1, 0 },
			new int[] { 2, 1, 2, 1, 2 },
			new int[] { 1, 1, 0, 1, 1 },
			new int[] { 1, 1, 2, 1, 1 },
			new int[] { 0, 1, 1, 1, 0 },
			new int[] { 2, 1, 1, 1, 2 },
			new int[] { 0, 2, 0, 2, 0 },
			new int[] { 2, 0, 2, 0, 2 },
			new int[] { 0, 2, 2, 2, 0 }
		};

		public int[][] paytable = {
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 40, 25, 25, 20, 20, 5, 5, 5, 5, 5 },
			new int[] { 0, 0, 200, 125, 100, 75, 60, 40, 25, 25, 20, 20 },
			new int[] { 0, 0, 2000, 1000, 750, 500, 350, 125, 100, 75, 60, 50 }
		};

		public int[] scatters = { 0, 0, 0, 8, 16, 24 };
	}

	public class Statistics {
		public long wonMoney = 0L;
		public long lostMoney = 0L;
		public long baseMoney = 0L;
		public long freeMoney = 0L;
		public long baseSharkMoney = 0L;
		public long freeSharkMoney = 0L;
		public long baseHitFrequency = 0L;
		public long freeHitFrequency = 0L;
		public long freeGamesNumber = 0;
		public long totalNumberOfGames = 0L;
		public long totalNumberOfFreeGames = 0L;
		public long numberOfSimulations = 10_000_000L;
	}

	//TODO new SecureRandom()
	private static final Random PRNG = ThreadLocalRandom.current();

	public Model model = new Model();
	public Statistics statistics = new Statistics();

	private void spin(int[][] reels, int[][] view) {
		for (int i = 0; i < view.length && i < reels.length; i++) {
			int r = PRNG.nextInt(reels[i].length);
			int u = r - 1;
			int d = r + 1;

			if (u < 0) {
				u = reels[i].length - 1;
			}

			if (d >= reels[i].length) {
				d = 0;
			}

			view[i][0] = reels[i][u];
			view[i][1] = reels[i][r];
			view[i][2] = reels[i][d];
		}
	}

	private int lineWin(int[] line) {
		int win = 0;
		int sharkMultiplier = 1;
		int symbol = line[0];

		for (int i = 0; i < 5 && (symbol == 1 || symbol == 12); i++) {
			symbol = line[i];
		}

		for (int i = 0; i < line.length; i++) {
			if (line[i] == 1) {
				line[i] = symbol;
			}
			if (line[i] == 12) {
				line[i] = symbol;
				sharkMultiplier = 2;
			}
		}

		int number = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] == symbol) {
				number++;
			} else {
				break;
			}
		}

		win = model.paytable[number][symbol];

		return (win * sharkMultiplier);
	}

	private int linesWin(int[][] view) {
		int win = 0;

		int[] line = { -1, -1, -1, -1, -1 };
		for (int l = 0; l < model.lines.length; l++) {
			for (int i = 0; i < line.length; i++) {
				int index = model.lines[l][i];
				line[i] = view[i][index];
			}

			win += lineWin(line);
		}

		return (win);
	}

	private int bestSwapsWin(int[][] view) {
		int k = -1, l = -1;
		int win = 0, best = 0;

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 5; i++) {
				if (view[i][j] == 12) {
					k = i;
					l = j;
				}
			}
		}

		if (k == -1 && l == -1) {
			return (0);
		}

		int[][] copy = {
			{ -1, -1, -1 },
			{ -1, -1, -1 },
			{ -1, -1, -1 },
			{ -1, -1, -1 },
			{ -1, -1, -1 },
		};
		for (int j = l - 1; j <= l + 1; j++) {
			for (int i = k - 1; i <= k + 1; i++) {
				if (i < 0 || i >= 5 || j < 0 || j >= 3) {
					continue;
				}
				if (i == k && j == l) {
					continue;
				}

				for (int n = l - 1; n <= l + 1; n++) {
					for (int m = k - 1; m <= k + 1; m++) {
						if (m < 0 || m >= 5 || n < 0 || n >= 3) {
							continue;
						}
						if (m == k && n == l) {
							continue;
						}

						if ((j * 10 + i) >= (n * 10 + m)) {
							continue;
						}

						copy[0][0] = view[0][0];
						copy[0][1] = view[0][1];
						copy[0][2] = view[0][2];
						copy[1][0] = view[1][0];
						copy[1][1] = view[1][1];
						copy[1][2] = view[1][2];
						copy[2][0] = view[2][0];
						copy[2][1] = view[2][1];
						copy[2][2] = view[2][2];
						copy[3][0] = view[3][0];
						copy[3][1] = view[3][1];
						copy[3][2] = view[3][2];
						copy[4][0] = view[4][0];
						copy[4][1] = view[4][1];
						copy[4][2] = view[4][2];

						int swap = copy[i][j];
						copy[i][j] = copy[m][n];
						copy[m][n] = swap;

						boolean equal = true;
						for (int t = 0; t < 3; t++) {
							for (int s = 0; s < 5; s++) {
								if (view[s][t] != copy[s][t]) {
									equal = false;
								}
							}
						}
						if (equal == true) {
							continue;
						}

						win = linesWin(copy);
						if (win > best) {
							best = win;
						}
					}
				}
			}
		}

		return (best);
	}

	private void freeGamesSetup(int[][] view) {
		int number = 0;

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 5; i++) {
				if (view[i][j] == 0) {
					number++;
				}
			}
		}

		if (number >= model.scatters.length) {
			number = model.scatters.length - 1;
			model.valid = false;
		}

		// TODO Check for free games retrigger.
		statistics.freeGamesNumber += model.scatters[number];
	}

	private void fullRespinGame(int[][] reels, int[][] view) {
		int k = -1, l = -1;

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 5; i++) {
				if (view[i][j] == 12) {
					k = i;
					l = j;
				}
			}
		}

		if (k == -1 && l == -1) {
			return;
		}

		spin(reels, view);
		view[k][l] = 12;
		// TODO Check for free games retrigger.
		freeGamesSetup(view);

		int win = linesWin(view);
		int best = bestSwapsWin(view);
		if (best > win) {
			if (reels == model.sharkReels) {
				statistics.baseSharkMoney += best;
				statistics.wonMoney += best;
			}
			if (reels == model.freeReels) {
				statistics.freeSharkMoney += best;
				statistics.wonMoney += best;
			}
		} else if (best <= win) {
			if (reels == model.sharkReels) {
				statistics.baseSharkMoney += win;
				statistics.wonMoney += win;
			}
			if (reels == model.freeReels) {
				statistics.freeSharkMoney += win;
				statistics.wonMoney += win;
			}

			fullRespinGame(reels, view);
		}
	}

	private void singleSharkGame(int[][] view) {
		boolean freeMode = false;

		if (statistics.freeGamesNumber > 0) {
			freeMode = true;
			spin(model.freeReels, view);
		} else {
			spin(model.sharkReels, view);
		}
		view[2][PRNG.nextInt(3)] = 12;

		freeGamesSetup(view);

		int win = linesWin(view);
		int best = bestSwapsWin(view);

		if (freeMode == false && best > win) {
			statistics.baseSharkMoney += best;
			statistics.wonMoney += best;
			statistics.baseHitFrequency++;
		} else if (freeMode == false && best <= win) {
			statistics.baseSharkMoney += win;
			statistics.wonMoney += win;
			statistics.baseHitFrequency++;
			fullRespinGame(model.sharkReels, view);
		} else if (freeMode == true && best > win) {
			statistics.freeSharkMoney += best;
			statistics.wonMoney += best;
			statistics.freeHitFrequency++;
		} else if (freeMode == true && best <= win) {
			statistics.freeSharkMoney += win;
			statistics.wonMoney += win;
			statistics.freeHitFrequency++;
			fullRespinGame(model.freeReels, view);
		}
	}

	private void singleFreeGame(int[][] view) {
		spin(model.freeReels, view);

		freeGamesSetup(view);

		int win = linesWin(view);
		if (win > 0) {
			statistics.freeHitFrequency++;
			statistics.freeMoney += win;
			statistics.wonMoney += win;
		}
	}

	private void singleBaseGame(int[][] view) {
		spin(model.baseReels, view);

		freeGamesSetup(view);

		int win = linesWin(view);
		if (win > 0) {
			statistics.baseHitFrequency++;
			statistics.baseMoney += win;
			statistics.wonMoney += win;
		}

		while (statistics.freeGamesNumber > 0) {
			statistics.totalNumberOfFreeGames++;

			if (PRNG.nextDouble() < 0.58) {
				singleSharkGame(view);
			} else {
				singleFreeGame(view);
			}

			statistics.freeGamesNumber--;
		}
		statistics.freeGamesNumber = 0;
	}

	public void simulate() {
		int[][] view = {
			{ -1, -1, -1 },
			{ -1, -1, -1 },
			{ -1, -1, -1 },
			{ -1, -1, -1 },
			{ -1, -1, -1 },
		};

		for (long g = 0L; g < statistics.numberOfSimulations; g++) {
			statistics.totalNumberOfGames++;
			statistics.lostMoney += model.lines.length;

			if (PRNG.nextDouble() < 0.03921) {
				singleSharkGame(view);
			} else {
				singleBaseGame(view);
			}

			/* It is useless to simulate invalid model. */
			if(model.valid == false) {
				return;
			}
		}
	}

	public void simulate(Model model, Statistics statistics) {
		this.model = model;
		this.statistics = statistics;

		simulate();
	}

	public void simulate(String[] args) {
		System.out.println("Output will be on the screen!");
		System.out.println();
		System.out.println("Ctrl+C to abort simulation.");
		System.out.println();
		System.out.println("java Main -l1000");
		System.out.println("Do 1 000 iterations.");
		System.out.println();
		System.out.println("java Main -l1000k");
		System.out.println("Do 1 000 000 iterations.");
		System.out.println();
		System.out.println("java Main -l10m");
		System.out.println("Do 10 000 000 iterations.");
		System.out.println();
		System.out.println("java Main");
		System.out.println("Do 10 000 000 iterations as default value.");
		System.out.println();

		if (args.length > 0 && args[0].contains("-l")) {
			String lParameter = args[0].substring(2);

			if (lParameter.contains("k")) {
				lParameter = lParameter.substring(0, lParameter.length() - 1);
				lParameter += "000";
			}

			if (lParameter.contains("m")) {
				lParameter = lParameter.substring(0, lParameter.length() - 1);
				lParameter += "000000";
			}

			try {
				statistics.numberOfSimulations = Long.parseLong(lParameter);
			} catch (Exception exception) {
			}
		}

		simulate(model, statistics);

		// TODO Hunt for 96.8% RTP.
		System.out.println("Won money: " + statistics.wonMoney);
		System.out.println("Lost money: " + statistics.lostMoney);
		System.out.println("Total RTP%: " + ((double) statistics.wonMoney * 100.0 / (double) statistics.lostMoney));
		System.out
		.println("Base Game RTP%: " + ((double) statistics.baseMoney * 100.0 / (double) statistics.lostMoney));
		System.out
		.println("Free Game RTP%: " + ((double) statistics.freeMoney * 100.0 / (double) statistics.lostMoney));
		System.out.println("Base Hit Frequency: " + statistics.baseHitFrequency);
		System.out.println("Free Hit Frequency: " + statistics.freeHitFrequency);
		System.out.println("Base+Shark Game RTP%: " + ((double) (statistics.baseMoney + statistics.baseSharkMoney)
		                   * 100.0 / (double) statistics.lostMoney));
		System.out.println("Free+Shark Game RTP%: " + ((double) (statistics.freeMoney + statistics.freeSharkMoney)
		                   * 100.0 / (double) statistics.lostMoney));
		System.out.println("Total Number of Games: " + statistics.totalNumberOfGames);
		System.out.println("Total Number of Free Games: " + statistics.totalNumberOfFreeGames);
	}

	public double score(ToDoubleFunction<Melsambria> formula) {
		return formula.applyAsDouble(this);
	}
}
