package cn.edu.bjut.sse.algorithm.dynamicplan.knapsack;

import cn.edu.bjut.sse.algorithm.dynamicplan.knapsack.Knapsack;
import cn.edu.bjut.sse.algorithm.dynamicplan.knapsack.KnapsackProblem;

public class KnapsackTest {
	
	public static void main(String[] args) {

		// [Given]
		Knapsack[] bags = new Knapsack[] { new Knapsack(2, 13),
				new Knapsack(1, 10), new Knapsack(3, 24), new Knapsack(2, 15),
				new Knapsack(4, 28), new Knapsack(5, 33), new Knapsack(3, 20),
				new Knapsack(1, 8) };
		int totalWeight = 12;
		
		// [When]
		KnapsackProblem kp = new KnapsackProblem(bags, totalWeight);
		kp.solveComplete();
		
		// [Then]
		System.out.println(" -------- 该背包问题实例的解: --------- ");
		System.out.println("最优值：" + kp.getBestValue());
		System.out.println("最优解[选取的物品]: ");
		System.out.println(kp.getBestSolution());
		System.out.println("最优值矩阵：");
		kp.printBestValueMatrix();

	}
}
