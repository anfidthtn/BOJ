#define _CRT_SECURE_NO_WARININGS

#include<stdio.h>

int recuresive(int N, int M);

int main() {
	int N, M;
	scanf("%d %d", &N, &M);
	printf("%d\n", recuresive(N, M));
	return 0;
}
int recuresive(int N, int M) {
	if ((N %= M) == 0) {
		return 0;
	}
	if (N < M) {
		return N + recuresive(N, M - N);
	}
}