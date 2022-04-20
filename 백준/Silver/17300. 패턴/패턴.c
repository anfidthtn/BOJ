#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>

int pattern[9] = { 0, };
int visited[9] = { 0, };

int combination[16][3] = { {1, 3, 2}, {1, 7, 4}, {1, 9, 5}, {2, 8, 5}, {3, 1, 2}, {3, 7, 5}, {3, 9, 6}, {4, 6, 5}, {6, 4, 5}, {7, 1, 4}, {7, 3, 5}, {7, 9, 8}, {8, 2, 5}, {9, 1, 5}, {9, 3, 6}, {9, 7, 8} };

int check(int index);
int main() {
	int L;
	int i;
	int isOK = -1;

	scanf("%d", &L);

	for (i = 0; i < L; i++) {
		scanf("%d", &(pattern[i]));
		isOK = 1;
		if (visited[pattern[i]]++ > 0) {
			isOK = -1;
			break;
		}
		if (check(i) == -1) {
			isOK = -1;
			break;
		}
	}
	if (isOK == 1) {
		printf("YES");
	}
	if (isOK == -1) {
		printf("NO");
	}
}
int check(int index) {
	int a = pattern[index - 1];
	int b = pattern[index];

	int i;

	for (i = 0; i < 16; i++) {
		if (a == combination[i][0] && b == combination[i][1]) {
			if (visited[combination[i][2]] == 0) {
				return -1;
			}
		}
	}
	return 1;
}