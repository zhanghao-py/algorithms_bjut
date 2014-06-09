/**
 * 
 */
package cn.edu.bjut.sse.algorithm.dynamicplan.tsp;

/**
 * @author Zi-Zi
 *
 */
public class TSPproblemSet {
	
	private int n;
	private int[][] adjiontMatrix;
	
	public TSPproblemSet(int numberOfCity, int[][] adjiontMatrix){
		
		if(numberOfCity * numberOfCity != adjiontMatrix.length)
			throw new RuntimeException("The number of City is not suitable for the adjiont Matrix.Please check.");
		
		this.n = numberOfCity;
		this.adjiontMatrix = adjiontMatrix;
	}
	
	public int getCityCount() {
		return n;
	}
	
	public int getDistanceBetween(int from, int to){
		if(from <= 0 || to <= 0)
			throw new RuntimeException("Inoput data error!Please check.");
		
		return adjiontMatrix[from][to];
	}
	
	public void printMatrix(){
		for(int i =0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				System.out.println(adjiontMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
}
