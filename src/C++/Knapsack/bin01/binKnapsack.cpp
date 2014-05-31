#include <iostream>
#include <memory.h>


const int n = 5;
const int value[n] = {13,10,24,15,28};
const int weight[n] = {2,1,3,2,4};
const int volume = 7;

/*

const int n = 8;
const int value[n] = { 13, 10, 24, 15, 28, 33, 20, 8 };
const int weight[n] = { 2, 1, 3, 2, 4, 5, 3, 1 };
const int volume = 12;

*/
using namespace std;
int max(int a, int b){
    return a > b ? a :b;
}
//problem 0-1 knapsack
int main () {

    int dp[n][volume + 1];
    memset(dp,sizeof(int)*n*volume,0);

    //初始化第一个物品
    for(int i = 0; i <= volume; ++i){
        dp[0][i] = i < weight[0] ? 0 : value[0];
    }

    //对每一个物品做判断
    for(int i = 1; i < n; ++i){
        for(int j = 0; j <= volume; ++j){
            dp[i][j] = j < weight[i] ?//若当前容量j能装下物品i，则判断max(dp[i-1][j], dp[i -1][j - weight[i]] + value[i])
                dp[i - 1][j] : max( dp[i - 1][j], dp[i -1][j - weight[i]] + value[i]);
        }
    }

    //打印动态规划数组
    for(int i = 0; i < n; ++i){
        for(int j = 0; j <= volume; ++j){
            cout<<dp[i][j]<<",";
        }
        cout<<endl;
    }

    //输出结果
    cout<<dp[n-1][volume]<<endl;
}
