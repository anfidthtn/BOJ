#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<stdlib.h>

#define MAXSIZE 100
#define min(a, b) (a > b ? b : a)

#define code 0 // 0 : final // 1 : check

int EE(int left, int right, int dir);
int **EE_arr[3];
int WS(int point);
int *WS_arr;

int N, M, *D, *W;

int main() {
	int i;

	scanf("%d %d", &N, &M);

	D = (int *)malloc(sizeof(int) * (N + 1));
	W = (int *)malloc(sizeof(int) * (N + 1));
	EE_arr[1] = (int **)malloc(sizeof(int*) * (N + 1));
	EE_arr[2] = (int **)malloc(sizeof(int*) * (N + 1));
	WS_arr = (int *)calloc(N + 1, sizeof(int));
	for (i = 1; i <= N; i++) {
		scanf("%d %d", &D[i], &W[i]);
		EE_arr[1][i] = (int *)calloc(N + 1, sizeof(int));
		EE_arr[2][i] = (int *)calloc(N + 1, sizeof(int));
	}

	printf("%d\n", min(EE(1, N, 1), EE(1, N, 2)));

	for (i = 1; i < N; i++) {
		free(EE_arr[1][i]);
		free(EE_arr[2][i]);
	}
	free(EE_arr[1]);
	free(EE_arr[2]);
	free(WS_arr);

	free(D);
	free(W);

	return 0;
}


int EE(int left, int right, int dir) {
	if (EE_arr[dir][left][right]) {
		return EE_arr[dir][left][right];
	}

	if (left == right) {
		if (left == M) {
			return 0;
		}
		EE_arr[dir][left][right] = 1000000000;
		return EE_arr[dir][left][right];
	}
	switch (dir) {
	case 1:
		EE_arr[dir][left][right] = min(EE(left + 1, right, 1) + (D[left + 1] - D[left]) * (WS(N) - (WS(right) - WS(left))), EE(left + 1, right, 2) + (D[right] - D[left]) * (WS(N) - (WS(right) - WS(left))));
		return EE_arr[dir][left][right];
	case 2:
		EE_arr[dir][left][right] = min(EE(left, right - 1, 1) + (D[right] - D[left]) * (WS(N) - (WS(right - 1) - WS(left - 1))), EE(left, right - 1, 2) + (D[right] - D[right - 1]) * (WS(N) - (WS(right - 1) - WS(left - 1))));
		return EE_arr[dir][left][right];
	}
	return -1;
}
int WS(int point) {
	int i;
	int count = 0;
	if (WS_arr[point]) {
		return WS_arr[point];
	}

	for (i = 1; i <= point; i++) {
		count += W[i];
	}
	WS_arr[point] = count;
	return WS_arr[point];
}