#include <iostream>
using namespace std;

const int MAX_CITY_NUM = 30;
const int INF = 100;
int main() {

//��������1
/*
    const int nCity = 4;
    const int dCity[nCity][nCity] =
    {   {0,2,5,7},
        {2,0,8,3},
        {5,8,0,1},
        {7,3,1,0}
    };
*/

//��������2

    const int nCity = 6;
    const int dCity[nCity][nCity] =
    {   {0,2,INF,2,1,1},
        {2,0,3,INF,1,INF},
        {INF,3,0,2,INF,3},
        {2,INF,2,0,4,INF},
        {1,1,INF,4,0,3},
        {1,INF,3,INF,3,0}
    };

    int c[nCity][1 << nCity-1];   //DP״̬ c[i][j]�����0��ʼ��������j����i�����·��

    for(int i = 0; i <nCity; ++i){  //��ʼ��
        for(int j = 0; j < 1<< nCity; ++j){
            c[i][j] = INF;
        }
    }

    int s = 1 <<(nCity-1);

    for(int j=0; j < nCity; ++j){
        c[j][0] = dCity[j][0];		//�ӵ�һ�����г������������κγ��У�S��Ϊ�գ������i�����еľ��롣
    }

    c[0][s-1] = INF;

    //����c[][]
    for(int j = 1; j < (s-1); ++j){//����������һ�����У��ӵڶ������г�����һֱ�����г��ж���ѡ�ж��ڶ�������δ��ѡ��

        for(int i = 1; i < nCity; ++i){
            //�������i����j�����У�����ӳ�ʼ�㿪ʼ��������j��i����̾���
            if(( j & (1 <<(i - 1) ) ) == 0){

                //c[i][j]min(dCity[k][i] + c[k][ (j - ( 1 << (k - 1) )) ])
                int m  = INF;
                for(int k = 1; k < nCity ; ++k){

                    if( (j & ( 1 << (k - 1) ) ) > 0){//�������k�ڼ���j��
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

    //ѡȡ��С�Ļ�·
    c[0][s-1] = INF;
    for(int i = 1; i < nCity; ++i){
        c[0][s-1] = min(c[0][s-1],dCity[0][i] + c[i][(s-1)-(1<<(i-1))]);
    }

/*
    //��ӡc[][]
    for(int i = 0; i < nCity; i++){
        for(int j = 0; j < 1 << nCity-1; j ++){
            cout<<c[i][j]<<", ";
        }
        cout<<endl;
    }*/

    cout<<"Minimum travle distance:"<<c[0][s-1]<<endl<<"Routine:";

    //������

    int lastCity = 0;
    for( int j = ( 1 << nCity-1 ) - 1; j > 0; ){//����һ���г�����-1��1��int���ɰ棬������j��������һ��Ԫ�����������Ԫ��
        for(int i = 1; i < nCity; ++i){
            if(c[i][j - (1 << i-1)] + dCity[i][lastCity] == c[lastCity][j]){//
                lastCity = i;
                j -= (1 << i-1);
                cout<<i<<",";
                break;
            }
        }
    }

    cout<<"0"<<endl;
}
