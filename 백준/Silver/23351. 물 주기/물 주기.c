#include<stdio.h>

int main() {
	int N, K, A, B;
	scanf("%d %d %d %d", &N, &K, &A, &B);
	int answer = 0;
	int cycle = N / A;
	while (K >= cycle) {
		answer += cycle;
		K += B - cycle;
	}
	answer += K;
	printf("%d", answer);
}