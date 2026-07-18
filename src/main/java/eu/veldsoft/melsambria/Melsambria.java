package eu.veldsoft.melsambria;

import java.security.SecureRandom;
import java.util.function.ToDoubleFunction;

public class Melsambria {
	public class Model {
		public int[][] baseReels = {
			{
				10, 6, 11, 8, 5, 9, 12, 7, 10, 0, 12, 6, 10, 11, 4, 8, 5, 12, 9, 7, 10, 3, 1, 7, 11, 12, 4, 11, 6, 12,
				3, 11, 8, 5, 11, 9, 8, 12, 4,
			},
			{
				10, 4, 12, 6, 9, 8, 3, 1, 10, 7, 0, 12, 4, 9, 10, 5, 11, 7, 8, 10, 6, 12, 3, 11, 4, 9, 7, 1, 11, 6, 8,
				5, 1, 3, 8, 9, 7, 12, 5, 11,
			},
			{
				10, 6, 8, 5, 9, 12, 7, 10, 8, 9, 4, 12, 3, 11, 6, 10, 7, 12, 8, 5, 10, 3, 11, 7, 8, 9, 11, 6, 0, 7,
				12, 5, 11, 9, 4, 8,
			},
			{
				12, 5, 9, 8, 6, 9, 7, 10, 0, 5, 9, 11, 6, 8, 10, 5, 12, 10, 7, 9, 0, 8, 11, 4, 10, 12, 5, 9, 8, 7, 10,
				12, 6, 9, 7, 11, 9, 7, 8, 12, 9, 8, 6, 10, 3, 12, 11, 5, 8, 11, 6, 12, 10, 0, 11, 10, 5, 12, 9,
				6, 11, 7, 8, 1, 10, 8, 4, 11,
			},
			{
				9, 6, 10, 12, 7, 8, 9, 6, 11, 12, 9, 10, 0, 5, 11, 10, 4, 9, 11, 6, 10, 12, 7, 0, 11, 9, 12, 7, 8, 9,
				3, 10, 11, 4, 8, 9, 6, 11, 12, 5, 8, 10, 5, 9, 11, 4, 10, 12, 7, 11, 12, 4, 9, 12, 8, 6, 11, 12,
				7, 10, 3, 11, 8, 5, 12, 6, 8, 5, 12, 6, 9, 10, 7, 1, 11, 10, 7, 8, 9, 6, 10, 12, 7, 8, 9, 6, 11,
				12, 5, 8, 10, 4, 9, 11, 6, 10, 12, 7, 11, 9, 0, 11, 7, 8, 9, 3, 10, 12, 0, 4, 8, 9, 6, 11, 0,
				12, 5, 8, 10, 5, 9, 11, 4, 10, 12, 7, 8, 12, 4, 9, 12, 8, 6, 11, 12, 7, 10, 11, 0, 10, 3, 9, 8,
				5, 12, 6, 8, 5, 12, 6, 9, 10, 7, 11, 10, 7, 8, 9, 6, 10, 12, 7, 8, 9, 6, 11, 10, 9, 12, 0, 5, 8,
				10, 4, 9, 11, 6, 10, 12, 7, 11, 9, 8, 7, 11, 9, 3, 10, 12, 4, 8, 9, 6, 11, 12, 5, 8, 10, 5, 9,
				11, 4, 10, 12, 7, 11, 12, 4, 10, 12, 8, 6, 11, 12, 7, 11, 3, 9, 8, 5, 12, 6, 8, 5, 12, 6, 9, 10,
				7, 1, 11, 10, 7, 8, 9, 6, 10, 12, 7, 8, 9, 6, 11, 12, 5, 8, 10, 4, 9, 11, 6, 10, 12, 7, 11, 9,
				0, 11, 7, 8, 9, 3, 10, 12, 4, 8, 9, 6, 11, 0, 12, 5, 8, 10, 5, 9, 11, 4, 10, 12, 7, 11, 12, 4,
				9, 12, 8, 6, 11, 10, 7, 12, 9, 10, 3, 11, 8, 5, 12, 6, 8, 5, 12, 6, 9, 10, 7, 11, 10, 7, 8,
			},
		};

		public int[][] freeReels = {
			{
				10, 6, 11, 8, 5, 9, 12, 7, 10, 6, 0, 12, 4, 11, 6, 10, 8, 5, 6, 11, 7, 10, 3, 9, 7, 12, 1, 4, 9, 11,
				6, 12, 3, 11, 8, 5, 9, 1, 8, 4,
			},
			{
				10, 3, 12, 5, 11, 7, 8, 4, 5, 12, 9, 6, 8, 10, 3, 0, 12, 5, 9, 10, 7, 8, 4, 12, 7, 10, 3, 9, 4, 11,
				12, 6, 8, 10, 3, 11, 5, 7, 0, 8, 4, 5, 10, 6, 9, 11, 5, 8, 10, 3, 12, 11, 6, 9, 7, 1, 11, 8, 5,
				7, 10, 1, 0, 12, 4, 9, 6, 8,
			},
			{
				11, 6, 12, 10, 7, 11, 9, 3, 12, 8, 5, 11, 4, 10, 0, 12, 9, 4, 8, 11, 6, 10, 12, 7, 9, 11, 4, 8, 12, 5,
				11, 3, 12, 6, 10, 7, 11, 5, 12, 9, 3, 8,
			},
			{
				5, 9, 4, 8, 3, 6, 9, 7, 10, 0, 5, 9, 11, 6, 8, 10, 5, 12, 4, 10, 9, 7, 8, 11, 4, 10, 12, 5, 9, 8, 4,
				11, 9, 6, 12, 7, 10, 9, 6, 8, 12, 3, 9, 8, 7, 10, 3, 12, 11, 5, 8, 11, 6, 12, 0, 11, 10, 5, 12,
				8, 4, 11, 7, 9, 1, 10, 8, 3, 11, 5, 9, 4, 8, 3, 6, 9, 7, 10, 0, 5, 9, 11, 4, 8, 1, 10, 5, 12, 4,
				10, 9, 7, 8, 11, 6, 10, 12, 5, 9, 8, 4, 11, 12, 6, 9, 7, 10, 9, 6, 8, 12, 3, 10, 8, 7, 9, 3, 12,
				11, 6, 8, 11, 5, 12, 0, 11, 10, 5, 8, 9, 3, 11, 7, 12, 1, 10, 8, 4, 11,
			},
			{
				9, 6, 10, 12, 7, 8, 9, 6, 11, 12, 5, 8, 10, 7, 9, 11, 6, 10, 12, 4, 11, 7, 8, 9, 6, 11, 12, 5, 8, 10,
				7, 9, 12, 4, 10, 11, 5, 12, 6, 11, 12, 8, 4, 9, 10, 7, 12, 11, 10, 3, 9, 8, 5, 12, 6, 10, 7, 12,
				6, 9, 8, 5, 11, 10, 7, 8, 9, 6, 10, 12, 7, 8, 9, 6, 11, 5, 8, 10, 4, 9, 11, 6, 10, 1, 12, 7, 11,
				9, 10, 7, 8, 9, 3, 11, 12, 4, 8, 9, 6, 11, 0, 4, 8, 10, 5, 9, 12, 4, 10, 11, 7, 12, 5, 8, 9, 6,
				12, 7, 10, 8, 12, 3, 9, 11, 5, 10, 6, 8, 7, 12, 6, 9, 5, 8, 11, 10, 7, 8,
			},
		};

		public int[][] sharkReels = new int[][] {
			{ 10, 6, 8, 11, 5, 9, 7, 10, 3, 0, 7, 6, 9, 4, 12, 6, 5, 9, 7, 3, 1, 12, 4, 6, 3, 11, 5, 1, 8, 4, },
			{
				10, 3, 11, 5, 12, 7, 8, 4, 5, 6, 8, 10, 3, 0, 12, 5, 9, 8, 7, 12, 4, 8, 1, 7, 10, 6, 12, 4, 11, 9, 6,
				8, 10, 3, 11, 5, 7, 8, 4, 6, 10, 5, 9, 11, 6, 10, 7, 12, 5, 9, 7, 1, 8, 5, 7, 10, 4, 9, 6, 8,
			},
			{
				11, 6, 12, 7, 9, 8, 5, 11, 4, 12, 0, 10, 9, 11, 6, 12, 10, 7, 9, 4, 12, 8, 5, 11, 3, 12, 6, 7, 11, 5,
				10, 3, 8,
			},
			{
				1, 5, 9, 4, 8, 3, 6, 7, 10, 5, 9, 11, 6, 8, 1, 10, 5, 12, 4, 10, 7, 9, 0, 11, 4, 12, 5, 9, 8, 4, 11,
				1, 12, 6, 9, 7, 10, 9, 7, 8, 12, 3, 9, 8, 6, 10, 3, 12, 5, 8, 11, 6, 10, 0, 11, 10, 5, 12, 9, 3,
				8, 7, 11, 1, 10, 8, 4, 11,
			},
			{
				9, 6, 10, 12, 7, 8, 9, 6, 11, 12, 5, 8, 10, 4, 9, 11, 6, 10, 12, 7, 11, 9, 8, 7, 11, 9, 3, 10, 12, 4,
				8, 9, 6, 11, 0, 12, 5, 8, 10, 7, 9, 11, 4, 10, 12, 5, 8, 12, 4, 7, 12, 8, 6, 12, 7, 10, 9, 0,
				10, 3, 9, 8, 5, 12, 6, 8, 5, 12, 6, 9, 10, 7, 11, 10, 7, 8, 9, 6, 10, 12, 7, 8, 9, 6, 11, 12, 5,
				8, 10, 4, 9, 11, 6, 10, 12, 7, 11, 9, 0, 11, 7, 8, 9, 3, 10, 12, 5, 8, 9, 6, 11, 12, 4, 8, 10,
				5, 9, 11, 4, 10, 12, 7, 11, 1, 4, 11, 12, 8, 6, 11, 5, 7, 10, 9, 0, 10, 5, 9, 8, 3, 12, 6, 8, 5,
				12, 6, 9, 10, 7, 11, 10, 7, 8,
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
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 40, 25, 25, 20, 20, 5, 5, 5, 5, 5 },
			new int[] { 0, 0, 0, 200, 125, 100, 75, 60, 40, 25, 25, 20, 20 },
			new int[] { 0, 0, 0, 2000, 1000, 750, 500, 350, 125, 100, 75, 60, 50 }
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

	private static final SecureRandom PRNG = new SecureRandom();

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

		for (int i = 0; i < 5 && (symbol == 1 || symbol == 2); i++) {
			symbol = line[i];
		}

		for (int i = 0; i < line.length; i++) {
			if (line[i] == 1) {
				line[i] = symbol;
			}
			if (line[i] == 2) {
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

		for (int l = 0; l < model.lines.length; l++) {
			int[] line = { -1, -1, -1, -1, -1 };

			for (int i = 0; i < 5; i++) {
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
				if (view[i][j] == 2) {
					k = i;
					l = j;
				}
			}
		}

		if (k == -1 && l == -1) {
			return (0);
		}

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

						int[][] copy = {
							{ view[0][0], view[0][1], view[0][2] },
							{ view[1][0], view[1][1], view[1][2] },
							{ view[2][0], view[2][1], view[2][2] },
							{ view[3][0], view[3][1], view[3][2] },
							{ view[4][0], view[4][1], view[4][2] },
						};

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

		// TODO Check for free games retrigger.
		statistics.freeGamesNumber += model.scatters[number];
	}

	private void fullRespinGame(int[][] reels, int[][] view) {
		int k = -1, l = -1;

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 5; i++) {
				if (view[i][j] == 2) {
					k = i;
					l = j;
				}
			}
		}

		if (k == -1 && l == -1) {
			return;
		}

		spin(reels, view);
		view[k][l] = 2;
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
		view[2][PRNG.nextInt(3)] = 2;

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
		}
	}

	public void simulate(Model model, Statistics statistics) {
		this.model = model;
		this.statistics = statistics;
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
