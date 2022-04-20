#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>

#define INFINITE 1000000000
#define Min(a, b) (((a) < (b))? (a) : (b))

int** table;
int N, M;
int group[101][2] = { {0, 0}, };
int groupsort[101][2] = { {0, 0}, };
int groupnum = 0;

void floyd();
void makegroup();

int main() {
	int i, j;
	int input_A, input_B;
	int temp;

	scanf("%d %d", &N, &M);

	table = (int**)malloc(sizeof(int*) * N);

	for (i = 0; i < N; i++) {
		table[i] = (int*)malloc(sizeof(int) * N);
		for (j = 0; j < N; j++) {
			table[i][j] = INFINITE;
			if (i == j) {
				table[i][j] = 0;
			}
		}
	}

	for (i = 0; i < M; i++) {
		scanf("%d %d", &input_A, &input_B);
		table[input_A - 1][input_B - 1] = 1;
		table[input_B - 1][input_A - 1] = 1;
	}
	floyd(table, N);

	makegroup();


	for (i = 1; i <= groupnum; i++) {
		groupsort[i][1] = INFINITE;
	}
	for (j = N - 1; j >= 0; j--) {
		if (group[j][1] <= groupsort[group[j][0]][1]) {
			groupsort[group[j][0]][1] = group[j][1];
			groupsort[group[j][0]][0] = j;
		}
	}
	for (i = 1; i <= groupnum; i++) {
		for (j = i + 1; j <= groupnum; j++) {
			if (groupsort[i][0] > groupsort[j][0]) {
				temp = groupsort[i][0];
				groupsort[i][0] = groupsort[j][0];
				groupsort[j][0] = temp;
			}
		}
	}

	printf("%d\n", groupnum);

	for (i = 1; i <= groupnum; i++) {
		printf("%d\n", groupsort[i][0] + 1);
	}

	return 0;
}
void floyd() {
	int i, j, k;

	for (k = 0; k < N; k++) {
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (table[i][j] > table[i][k] + table[k][j]) {
					table[i][j] = table[i][k] + table[k][j];
				}
			}
		}
	}
}
void makegroup() {
	int i, j, k;
	groupnum = 0;

	for (i = 0; i < N; i++) {
		if (group[i][0] > 0) {
			continue;
		}
		groupnum++;
		for (j = i; j < N; j++) {
			if (table[i][j] != INFINITE) {
				group[j][0] = groupnum;
				for (k = 0; k < N; k++) {
					if (table[j][k] != INFINITE && group[j][1] < table[j][k])
						group[j][1] = table[j][k];
				}
			}
		}
	}
}