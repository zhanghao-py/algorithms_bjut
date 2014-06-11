package cn.edu.bjut.sse.algorithm.backtracking.stamp;

/**
 * 最大连续邮资问题，回溯法
 * 
 * @author Marco Jee
 * @date 2010-12-17
 */
public class StampCost {

	private final static int MAX_NM = 10;

	private final static int MAX_POSTAGE = 1024;

	private final static int INF = Integer.MAX_VALUE;

	private int n;// n种不同面值的邮票
	private int m;// 一封信最多帖m张邮票

	private int values[];// 从x[0]到x[i]表示当前已经确定的i+1个面值
	private int stamps[];// 求出来的邮票面值最终结果
	private int leastStamp[];// 存放能由当前面值贴出某个邮资使用的最少邮票。如leastStamp[10] = 1，表示贴出10只需要一张，就是10本身。
	private int maxStamp;// 满足条件的邮资最大区间的最终结果
	private int ranges;// 表示能由x贴出的最大连续区间

	public StampCost(int n, int m) {
		this.values = new int[MAX_NM];
		this.values[0] = 1;// 第一个永远是1
		this.stamps = new int[MAX_NM];
		this.leastStamp = new int[MAX_POSTAGE];
		this.n = n;
		this.m = m;
		this.ranges = m;// 初始为m (m*1=m)
		int i = 0;
		for (i = 0; i <= this.ranges; i++) {
			leastStamp[i] = i;
		}
		
		while (i < MAX_POSTAGE) {
			leastStamp[i++] = INF;
		}
		
		this.maxStamp = 0;
		// 第一张永远为1，所以从第二张开始计算
		this.backtrack(1);
	}

	/**
	 * 从第(i+1)张邮票到第n张邮票的面值
	 * @param i
	 */
	private void backtrack(int i) {

		// 如果到达发行邮票的张数，则更新最终结果值，并返回结果
		if (i >= this.n) {
			// 用ranges计算可贴出的连续邮资最大值，而maxStamp存放最终结果
			if (this.ranges > this.maxStamp) {
				this.maxStamp = this.ranges;
				// 用values[i]表示当前以确定的第i+1张邮票的面值，stamps保存最终结果
				for (int tmp = 0; tmp < this.n; tmp++) {
					this.stamps[tmp] = this.values[tmp];
				}
			}
			return;
		}

		// 保存leastStamp和backupR值，避免递归调用时被更新。由于计算下一次时，y和r的值都需要改变，因此相当于是初始化
		int[] backupY = new int[MAX_POSTAGE];
		for (int tmp = 0; tmp < MAX_POSTAGE; tmp++) {
			backupY[tmp] = this.leastStamp[tmp];
		}
		int backupR = this.ranges;

		// next 表示下一个邮票面值的可能值
		// 在当前已确定的基础上，下一张邮票面值的取值范围为当前最大面值+1到当前可组合的连续邮资最大值+1
		for (int next = values[i - 1] + 1; next <= this.ranges + 1; next++) {
			// 更新第i张的值
			this.values[i] = next;

			// 搜索当前已确定的邮票能表示出的最大邮资范围（最小值为0，最大值为邮票最大值乘以最大张数）
			for (int postage = 0; postage < values[i - 1] * m; postage++) {
				
				if (this.leastStamp[postage] >= m) 
					continue;
				
				// 计算表示postage还可以使用的邮票数目
				for (int num = 1; num <= this.m - this.leastStamp[postage]; num++) {

					// 如果组合出postage的最少邮票数加上num张，小于组合出postage+num*next的邮票数，说明组合出postage+num*next并不需要使用num张下一张邮票
					// 那么组合出postage+num*next的邮票数，就等于组合出postage的邮票数加上num
					if (this.leastStamp[postage] + num < this.leastStamp[postage
							+ num * next]
							&& (postage + num * next < MAX_POSTAGE)) {
						leastStamp[postage + num * next] = leastStamp[postage]
								+ num;
					}
				}
			}

			while (this.leastStamp[this.ranges + 1] < INF) {
				this.ranges++;
			}

			// 递归调用求下一个值
			backtrack(i + 1);

			this.ranges = backupR;
			for (int tmp = 0; tmp < MAX_POSTAGE; tmp++) {
				this.leastStamp[tmp] = backupY[tmp];
			}
		}
	}

	public void print() {
		for (int i = 0; i < n; i++) {
			System.out.print(stamps[i] + " ");
		}
		System.out.println();
		System.out.println("最大邮资为:" + this.maxStamp);
	}

	public static void main(String[] args) {
		StampCost sc = new StampCost(5, 4);
		sc.print();
	}
}
