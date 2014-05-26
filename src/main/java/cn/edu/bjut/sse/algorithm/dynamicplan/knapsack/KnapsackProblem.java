package cn.edu.bjut.sse.algorithm.dynamicplan.knapsack;

import java.util.LinkedList;
import java.util.List;

public class KnapsackProblem {
	/** 指定物品 */
	private Knapsack[] bags;

	/** 总承重 */
	private int totalWeight;

	/** 给定物品数量 */
	private int n;

	/** 前 n个物品，总承重为 totalWeight的最优值矩阵 */
	private int[][] bestValues;

	/** 前 n个物品，总承重为 totalWeight的最优值 */
	private int bestValue;

	/** 前 n个物品，总承重为 totalWeight的最优解的物品组成 */
	private List<Knapsack> bestSolution;

	public KnapsackProblem(Knapsack[] bags, int totalWeight) {
		this.bags = bags;
		this.totalWeight = totalWeight;
		this.n = bags.length;
		if (bestValues == null) {
			bestValues = new int[n][totalWeight + 1];
		}
	}

	/**
	 * 求解前 n个物品、给定总承重为 totalWeight下的完全背包问题
	 * 
	 */
	public void solveComplete() {

		System.out.println("给定物品：");
		for (Knapsack b : bags) {
			System.out.println(b);
		}
		System.out.println("给定总承重: " + totalWeight);

		// 求解最优值
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j <= totalWeight; j++) {

				// 第 i个物品的重量 iweight 和价值 ivalue
				int iweight = bags[i].getWeight();
				int ivalue = bags[i].getValue();
				
				if (i == 0) {
					bestValues[i][j] = 0;
				} else if (j < iweight) { 
					// 如果第 i个物品重量大于总承重，则最优解存在于前 i-1 个背包中，注意：第 i个物品是 bags[i-1]
					bestValues[i][j] = bestValues[i - 1][j];
				} else {
					// 如果第 i个物品不大于总承重，则最优解要么是包含第 i个物品的最优解，
					// 要么是不包含第 i个物品的最优解， 取两者最大值
					bestValues[i][j] = Math.max(bestValues[i - 1][j], bestValues[i][j - iweight] + ivalue);
				}
			}
		}

		// 求解物品组成
		if (bestSolution == null) {
			bestSolution = new LinkedList<Knapsack>();
		}
		
		int tempWeight = totalWeight;
		for (int i = n - 1; i >= 1; i--) {
			if (bestValues[i][tempWeight] > bestValues[i - 1][tempWeight]) {
				Knapsack b = bags[i];
				int n = (bestValues[i][tempWeight] - bestValues[i - 1][tempWeight]) / b.getValue();
				
				for (int k = 1; k <= n ; k++) {
					bestSolution.add(bags[i]); // bags[i] 表示第i个物品
				}
				
				tempWeight -= bags[i].getWeight() * n;
			}
			
			if (tempWeight == 0) {
				break;
			}
		}
		
		bestValue = bestValues[n-1][totalWeight];
	}
	
	/**
	 * 求解前 n个物品、给定总承重为 totalWeight下的0/1背包问题
	 * 
	 */
	public void solve() {

		System.out.println("给定物品：");
		for (Knapsack b : bags) {
			System.out.println(b);
		}
		System.out.println("给定总承重: " + totalWeight);

		// 求解最优值
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j <= totalWeight; j++) {
				
				int iweight = bags[i].getWeight();
				int ivalue = bags[i].getValue();
				
				if (i == 0) {
					bestValues[i][j] = 0;
				} else if (j < iweight) { 
					// 如果第 i个物品重量大于总承重，则最优解存在于前 i-1 个背包中，注意：第 i个物品是 bags[i-1]
					bestValues[i][j] = bestValues[i - 1][j];
				} else {
					// 如果第 i个物品不大于总承重，则最优解要么是包含第 i个物品的最优解，
					// 要么是不包含第 i个物品的最优解， 取两者最大值
					// 第 i个物品的重量 iweight 和价值 ivalue
					bestValues[i][j] = Math.max(bestValues[i - 1][j], bestValues[i - 1][j - iweight] + ivalue);
				}
			}
		}

		// 求解物品组成
		if (bestSolution == null) {
			bestSolution = new LinkedList<Knapsack>();
		}
		
		int tempWeight = totalWeight;
		for (int i = n-1; i >= 1; i--) {
			if (bestValues[i][tempWeight] > bestValues[i - 1][tempWeight]) {
				bestSolution.add(bags[i]); // bags[i-1] 表示第i个物品
				tempWeight -= bags[i].getWeight();
			}
			
			if (tempWeight == 0) {
				break;
			}
		}
		
		bestValue = bestValues[n-1][totalWeight];
	}
	
	/**
	 * 打印最优值矩阵： 必须先调用 solve 方法
	 * 
	 */
	public void printBestValueMatrix() {
		for (int i = 0; i < bestValues.length; i++) {
			for (int j = 0; j < bestValues[i].length; j++) {
				System.out.printf("%-5d", bestValues[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 获得前 n个物品， 总承重为 totalWeight的背包问题的最优解值 调用条件： 必须先调用 solve 方法
	 * 
	 */
	public int getBestValue() {
		return bestValue;
	}

	/**
	 * 获得前 n个物品， 总承重为 totalWeight的背包问题的最优解值矩阵 调用条件： 必须先调用 solve 方法
	 * 
	 */
	public List<Knapsack> getBestSolution() {
		return bestSolution;
	}
}
