package cn.edu.bjut.sse.algorithm.dynamicplan.knapsack.ref;

public class ZeroOnePack {

	public static void main(String[] args) {
		int[] value = new int[] { 13, 10, 24, 15, 28, 33, 20, 8 };
		int[] weight = new int[] { 2, 1, 3, 2, 4, 5, 3, 1 };
		int W = 12;
		System.out.println(knapsack(value, weight, W));
	}

	public static int knapsack(int[] v, int[] w, int W) {
		int[] tmp = new int[W + 1];

		for (int i = 0; i < v.length; i++)
			zeroOnePack(tmp, v[i], w[i], W);

		return tmp[W];
	}

	/**
	 * 
	 * @param tmp
	 *            辅助数组
	 * @param value
	 *            当前v[i]的价值
	 * @param weight
	 *            当前w[i]的重量
	 * @param W
	 *            总重量
	 */
	public static void zeroOnePack(int[] tmp, int value, int weight, int W) {
		for (int i = W; i >= 1; i--) {
			tmp[i] = i - weight >= 0 ? Math
					.max(tmp[i], tmp[i - weight] + value) : tmp[i];
		}
	}
}
