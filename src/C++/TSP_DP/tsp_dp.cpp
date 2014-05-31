#include <iostream>
using namespace std;

const int MAX_CITY_NUM = 30;
const int INF = 100;
int main() {

//测试数据1

    const int nCity = 4;
    const int dCity[nCity][nCity] =
    {   {0,2,5,7},
        {2,0,8,3},
        {5,8,0,1},
        {7,3,1,0}
    };


//测试数据2
/*
    const int nCity = 6;
    const int dCity[nCity][nCity] =
    {   {0,2,INF,2,1,1},
        {2,0,3,INF,1,INF},
        {INF,3,0,2,INF,3},
        {2,INF,2,0,4,INF},
        {1,1,INF,4,0,3},
        {1,INF,3,INF,3,0}
    };
*/
    int c[nCity][1 << nCity];   //DP状态 c[i][j]代表从0开始经过集合j到达i的最短路径

    for(int i = 0; i <nCity; ++i){  //初始化
        for(int j = 0; j < 1<< nCity; ++j){
            c[i][j] = INF;
        }
    }

    int s = 1 <<(nCity-1);

    for(int j=0; j < nCity; ++j){
        c[j][0] = dCity[j][0];		//从第一个城市出发，不经过任何城市（S集为空）到达第i个城市的距离。
    }

    c[0][s-1] = INF;

    //填满c[][]
    for(int j = 1; j < (s-1); ++j){

        for(int i = 1; i < nCity; ++i){

            //如果城市i不在j集合中，计算从初始点开始经过集合j到i的最短距离
            if(( j & (1 <<(i - 1) ) ) == 0){

                //c[i][j]min(dCity[k][i] + c[k][ (j - ( 1 << (k - 1) )) ])
                int m  = INF;
                for(int k = 1; k < nCity ; ++k){

                    if( (j & ( 1 << (k - 1) ) ) > 0){//如果城市k在集合j中
                        int tmp = dCity[k][i] + c[k][ (j - ( 1 << (k - 1) )) ];

                        if(tmp < m){
                            m = tmp;
                        }
                    }

                }
                c[i][j] = m;
            }
        }
    }

    //判断每一个回路的最小值
    c[0][s-1] = INF;
    for(int i = 1; i < nCity; ++i){
        c[0][s-1] = min(c[0][s-1],dCity[0][i] + c[i][(s-1)-(1<<(i-1))]);
    }

    //打印c[][]
    for(int i = 0; i < nCity; i++){
        for(int j = 0; j < 1<<nCity; j ++){
            cout<<c[i][j]<<",";
        }
        cout<<endl;
    }


    //输出结果
    cout<<c[0][s-1]<<endl;


}
