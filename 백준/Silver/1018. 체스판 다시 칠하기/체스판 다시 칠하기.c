#include<stdio.h>
#include<stdlib.h>

int compare_minimum(int x, int y);
int minimum_paint(int a, int b, char **arr);

char cmparr[8][8] = {
'B','W','B','W','B','W','B','W',
'W','B','W','B','W','B','W','B',
'B','W','B','W','B','W','B','W',
'W','B','W','B','W','B','W','B',
'B','W','B','W','B','W','B','W',
'W','B','W','B','W','B','W','B',
'B','W','B','W','B','W','B','W',
'W','B','W','B','W','B','W','B'
};

int main() {
	int m, n;
	int i, j;
	char **input_arr;

	int minimum = 32;

	scanf("%d %d", &m, &n);
	getchar(); //fflush(stdin)

	input_arr = (char **)malloc(m * sizeof(char*));
	for (i = 0; i < m; i++) {
		input_arr[i] = (char *)malloc(n * sizeof(char));
		for (j = 0; j < n; j++) {
			input_arr[i][j] = getchar();
		}
		getchar(); // '\n'빼내기
	}

	for (i = 0; i < m - 7; i++) {
		for (j = 0; j < n - 7; j++) {
			minimum = compare_minimum(minimum, minimum_paint(i, j, input_arr));
		}
	}
	printf("%d\n", minimum);

	for (i = 0; i < m; i++) {
		free(input_arr[i]);
	}
	free(input_arr);

	return 0;
}
int compare_minimum(int x, int y) {
	if (x < y) {
		return x;
	}
	return y;
}
int minimum_paint(int a, int b, char **arr) {
	int i, j;
	int count = 0;

	for (i = 0; i < 8; i++) {
		for (j = 0; j < 8; j++) {
			if (cmparr[i][j] != arr[a + i][b + j]) {
				count++;
			}
		}
	}

	return compare_minimum(count, 64 - count);
}