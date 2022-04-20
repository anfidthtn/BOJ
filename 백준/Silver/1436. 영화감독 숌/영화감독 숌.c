#define _CRT_SECURE_NO_WARNINGS

#define MAXSIZE 10000

#include<stdio.h>
#include<stdlib.h>

int isInclude666(int num);

int main() {
	int N = 0;
	int i = 0;
	int count = 0;

	scanf("%d", &N);
	
	while(count < N) {
		if (isInclude666(++i)) count++;
	}

	printf("%d\n", i);

	return 0;
}

int isInclude666(int num) {
	while (num >= 666) {
		if (num % 1000 == 666) return 1;
		else num /= 10;
	}
	return 0;
}