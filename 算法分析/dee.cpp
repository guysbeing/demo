#include <iostream>
#include <limits>
#include <string>
#include <math.h>
#include <cstring>
#include <stdlib.h>

using namespace std;
int n;
int de[100][100];
int ans[100];
int found[100];
char road[100][100];
void dijk(){
    ans[1]=0;
    road[1][0]='1';
    for(int i=2;i<=n;i++){
        if(de[1][i]!=-1){
            road[i][0]='1';
            strcat(road[i], " ");
            char ok[10];
            itoa(i,ok,10);
            strcat(road[i], ok);
            ans[i]=de[1][i];
        }
    }

    for(int i=2;i<=n;i++){
        int sign;
        int min1=INT_MAX;
        for(int j=2;j<=n;j++)
        if(ans[j]<min1&&!found[j]){
            min1=ans[j];
            sign=j;
        }
        found[sign]++;
        for(int j=2;j<=n;j++){
            if(de[sign][j]>0){
                if (ans[j] > de[sign][j] + ans[sign]) {
                    ans[j] = de[sign][j] + ans[sign];
                    strcpy(road[j],road[sign]);
                    strcat(road[j], " ");
                    char ok[10];
                    itoa(j,ok,10);
                    strcat(road[j], ok);
                }
            }
        }
    }
}

int main() {
    while(cin >> n){
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++){
                cin >> de[i][j];
            }
        }
        memset(ans+1,0x7fff,sizeof(ans));
        dijk();
        for(int i=1;i<=n;i++)
        cout << ans[i] << " ";
        cout << endl;
        for(int i=1;i<=n;i++)
        {
            cout << i << " "<< road[i]<< endl;
        }
    }
}


