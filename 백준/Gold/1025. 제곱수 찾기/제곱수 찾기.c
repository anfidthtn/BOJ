#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>

int N, M;


int isValid(int x, int y) {
	return !(x < 0 || y < 0 || x >= N || y >= M) ? 1 : 0;
}

int isSquareNum(char* numS) {
	int num = atoi(numS);
	int sqrtNum = -1;

	sqrtNum = (int)sqrt(num);
	if ((int)pow(sqrtNum, 2) == num) return num;

	return -1;
}

int maxnum(int a, int b) {
	return a > b ? a : b;
}
int minnum(int a, int b) {
	return a < b ? a : b;
}

int main() {


	char** board;
	char numString[20];


	int i, j, multiple, distance;
	int dx, dy;
	int tempRow, tempCol;

	int maxSquareNum = -1;

	scanf("%d %d", &N, &M);

	board = (char**)malloc(sizeof(char*) * N);
	for (i = 0; i < N; i++) {
		board[i] = (char*)malloc(sizeof(char) * (M + 1));
		scanf("%s", board[i]);
	}

	for (i = 0; i < N; i++) {
		for (j = 0; j < M; j++) {
			numString[0] = board[i][j];
			numString[1] = '\0';
			maxSquareNum = maxnum(maxSquareNum, isSquareNum(numString));
			for (dx = -N + 1; dx < N; dx++) {
				for (dy = -M + 1; dy < M; dy++) {
					if (dx == 0 && dy == 0) continue;

					for (distance = 1; distance < maxnum(N, M); distance++) {
						numString[0] = board[i][j];
						numString[1] = '\0';
						for (multiple = 1; multiple < maxnum(N, M); multiple++) {
							tempRow = i + dx * distance * multiple;
							tempCol = j + dy * distance * multiple;
							if (isValid(tempRow, tempCol)) {
								numString[multiple] = board[tempRow][tempCol];
								numString[multiple + 1] = '\0';
								maxSquareNum = maxnum(maxSquareNum, isSquareNum(numString));
							}
						}
					}
				}
			}
		}
	}
	
	printf("%d", maxSquareNum);

	for (i = 0; i < N; i++) {
		free(board[i]);
	}
	free(board);

	return 0;
}