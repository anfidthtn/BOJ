#include<stdio.h>

int main(){
    int input;
    int i, j;
    
    scanf("%d", &input);
    
    for(i = 0; i < input; i++){
        for(j = input; j > i + 1; j--){
            printf(" ");
        }
        for(j = -1; j < i; j++){
            printf("*");
        }
        printf("\n");
    }
}