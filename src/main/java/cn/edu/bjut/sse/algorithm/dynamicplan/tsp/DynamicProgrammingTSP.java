package cn.edu.bjut.sse.algorithm.dynamicplan.tsp;

public class DynamicProgrammingTSP extends TSP {

	private final int INF = 1000;

	public static void main(String[] args) {

		/*
		 * int numberOfCity = 6; int[][] d = new int[][]{ {0,2,100,2,1,1},
		 * {2,0,3,100,1,100}, {100,3,0,2,100,3}, {2,100,2,0,4,100},
		 * {1,1,100,4,0,3}, {1,100,3,100,3,0} };
		 */

		int numberOfCity = 4;
		int[][] d = new int[][] { { 0, 2, 5, 7 }, { 2, 0, 8, 3 },
				{ 5, 8, 0, 1 }, { 7, 3, 1, 0 } };

		DynamicProgrammingTSP tsp = new DynamicProgrammingTSP();

		tsp.solve(numberOfCity, d);

	}

	private void solve(int numberOfCity, int[][] adjiontMatrix) {

		int[][] dp = new int[numberOfCity][1 << (numberOfCity - 1)];

		for (int i = 0; i < numberOfCity; ++i) {
			for (int j = 0; j < 1 << (numberOfCity - 1); ++j) {
				dp[i][j] = INF;
			}
		}

		int s = 1 << (numberOfCity - 1);

		for (int j = 0; j < numberOfCity; ++j) {
			dp[j][0] = adjiontMatrix[j][0];
		}

		dp[0][s - 1] = INF;

		for (int j = 1; j < (s - 1); ++j) {
			for (int i = 1; i < numberOfCity; ++i) {
				if ((j & (1 << (i - 1))) == 0) {
					int m = INF;
					for (int k = 1; k < numberOfCity; ++k) {
						if ((j & (1 << (k - 1))) > 0) {
							int tmp = adjiontMatrix[k][i]
									+ dp[k][(j - (1 << (k - 1)))];
							if (tmp < m) {
								m = tmp;
							}
						}
					}
					dp[i][j] = m;
				}
			}
		}

		for (int i = 0; i < numberOfCity; ++i) {
			for (int j = 0; j < 1 << numberOfCity - 1; ++j) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.print("\n");
		}

		dp[0][s - 1] = INF;

		for (int i = 1; i < numberOfCity; ++i) {
			dp[0][s - 1] = Math.min(dp[0][s - 1], adjiontMatrix[0][i]
					+ dp[i][(s - 1) - (1 << (i - 1))]);
		}

		System.out.print("Minimum travle distance:" + dp[0][s - 1]);
		System.out.print("Routine:");

		int lastCity = 0;
		for (int j = (1 << (numberOfCity - 1)) - 1; j > 0;) {
			for (int i = 1; i < numberOfCity; ++i) {
				if (dp[i][j - (1 << (i - 1))] + adjiontMatrix[i][lastCity] == dp[lastCity][j]) {//
					lastCity = i;
					j -= (1 << (i - 1));
					System.out.print(i + ",");
					break;
				}
			}
		}

		System.out.print("0\n");
	}
}
