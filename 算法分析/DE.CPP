#include <iostream>
#include <limits>
using namespace std;

void ak47(int de[][], int n, int *ans[]) {
   
    
}

int main() {
    int n;
    int de[100][100];
    int ans[100];
    string *way;
    while(cin >> n){
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++){
                cin >> de[i][j];
            }
        }
        ak47(de,n,&ans);
    }