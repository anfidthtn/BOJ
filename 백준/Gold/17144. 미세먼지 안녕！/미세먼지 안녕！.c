#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>

#define DEBUG 0

int R, C, T;

int M = 0; // Machine

int** F[2]; // F[0] : Field / F[1] : temp Field

#if(DEBUG >= 1)
void printARR(int** arr);
#endif
void initializeARR(int** arr);
void copyARR(int** dest, int** source);
void Spread();
void Cleaning();
void printSum(int **arr);

int main() {

	int i, j;

	scanf("%d %d %d", &R, &C, &T);

	F[0] = (int**)calloc(R + 2, sizeof(int*));
	F[1] = (int**)calloc(R + 2, sizeof(int*));

	for (i = 0; i < R + 2; i++) {
		F[0][i] = (int*)calloc(C + 2, sizeof(int));
		F[1][i] = (int*)calloc(C + 2, sizeof(int));
		for (j = 0; j < C + 2; j++) {
			if (i == 0 || j == 0 || i == R + 1 || j == C + 1) {
				F[0][i][j] = -2;
				F[1][i][j] = -2;
			}
			else {
				scanf("%d", &(F[0][i][j]));
				if (M == 0 && F[0][i][j] == -1) {
					M = i;
				}
			}
		}
	}
	for (i = 0; i < T; i++) {
		Spread();
		Cleaning();
	}

	printSum(F[0]);

#if(DEBUG == 1)
	printARR(F[0]);
	printf("%d\n", M);
#endif
	for (i = 0; i < R + 2; i++) {
		free(F[0][i]);
		free(F[1][i]);
	}
	free(F[0]);
	free(F[1]);

	return 0;
}
#if(DEBUG >= 1)
void printARR(int** arr) {
	int i, j;

	printf("\n");

	for (i = 0; i < R + 2; i++) {
		for (j = 0; j < C + 2; j++) {
			printf("%3d ", arr[i][j]);
		}
		printf("\n");
	}
}
#endif
void initializeARR(int** arr) {
	int i, j;
	for (i = 1; i < R + 1; i++) {
		for (j = 1; j < C + 1; j++) {
			arr[i][j] = 0;
		}
	}
}
void copyARR(int** dest, int** source) {
	int i, j;
	for (i = 1; i < R + 1; i++) {
		for (j = 1; j < C + 1; j++) {
			dest[i][j] = source[i][j];
		}
	}
}
void Spread() {
	int dirCount = 0;
	int frag = 0;

	int i, j;

	initializeARR(F[1]);

	for (i = 1; i < R + 1; i++) {
		for (j = 1; j < C + 1; j++) {
			if (F[0][i][j] < 5) {
				F[1][i][j] += F[0][i][j];
				continue;
			}
			else {
				dirCount = 0;
				if (F[0][i - 1][j] >= 0) {
					dirCount++;
					F[1][i - 1][j] += F[0][i][j] / 5;
				}
				if (F[0][i][j - 1] >= 0) {
					dirCount++;
					F[1][i][j - 1] += F[0][i][j] / 5;
				}
				if (F[0][i + 1][j] >= 0) {
					dirCount++;
					F[1][i + 1][j] += F[0][i][j] / 5;
				}
				if (F[0][i][j + 1] >= 0) {
					dirCount++;
					F[1][i][j + 1] += F[0][i][j] / 5;
				}
				F[1][i][j] += (F[0][i][j] - F[0][i][j] / 5 * dirCount);
			}
#if(DEBUG == 2)
			printARR(F[1]);
#endif
		}
	}

	copyARR(F[0], F[1]);
}
void Cleaning() {
	int i, j;

	// top - leftsize
	for (i = M - 1; i > 1; i--) {
		F[0][i][1] = F[0][i - 1][1];
	}
	// top - upside
	for (j = 1; j < C; j++) {
		F[0][1][j] = F[0][1][j + 1];
	}
	// top - rightside
	for (i = 1; i < M; i++) {
		F[0][i][C] = F[0][i + 1][C];
	}
	// top - downside
	for (j = C; j > 2; j--) {
		F[0][M][j] = F[0][M][j - 1];
	}
	// top - freshAir
	F[0][M][2] = 0;

	// bottom - leftside
	for (i = M + 2; i < R; i++) {
		F[0][i][1] = F[0][i + 1][1];
	}
	// bottom - downside
	for (j = 1; j < C; j++) {
		F[0][R][j] = F[0][R][j + 1];
	}
	// bottom - rightside
	for (i = R; i > M + 1; i--) {
		F[0][i][C] = F[0][i - 1][C];
	}
	// bottom - upside
	for (j = C; j > 2; j--) {
		F[0][M + 1][j] = F[0][M + 1][j - 1];
	}
	// bottom - freshAir
	F[0][M + 1][2] = 0;
}
void printSum(int** arr) {
	int i, j;
	int sum = 2; // +2 because of Machine sum is -2 (= -1 * 2)
	for (i = 1; i <= R; i++) {
		for (j = 1; j <= C; j++) {
			sum += arr[i][j];
		}
	}
	printf("%d", sum);
}