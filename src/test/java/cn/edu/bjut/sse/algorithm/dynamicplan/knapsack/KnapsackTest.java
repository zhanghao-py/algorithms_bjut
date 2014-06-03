package cn.edu.bjut.sse.algorithm.dynamicplan.knapsack;

import cn.edu.bjut.sse.algorithm.dynamicplan.knapsack.Knapsack;
import cn.edu.bjut.sse.algorithm.dynamicplan.knapsack.KnapsackProblem;

public class KnapsackTest {
	
	public static void main(String[] args) {

		// [Given]
//		Knapsack[] bags = new Knapsack[] { new Knapsack(3, 5),
//				new Knapsack(2, 10), new Knapsack(2, 20) };
//		int totalWeight = 5;
		
		
		Knapsack[] bags = new Knapsack[] { new Knapsack(2, 6),
				new Knapsack(1, 3), new Knapsack(6, 5), new Knapsack(5, 4),
				new Knapsack(4, 6) };
		int totalWeight = 11;
		
		
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
