#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {
	int i;
	int H, W, N;
	int T;

	scanf("%d", &T);
	
	for (i = 0; i < T; i++) {
		scanf("%d %d %d", &H, &W, &N);
		printf("%d%02d\n", (N - 1) % H + 1, (N - 1) / H + 1);
	}
	return 0;
}