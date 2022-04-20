#include<stdio.h>
#include<stdlib.h>

#define TRUE 1
#define FALSE 0

int comp3max(int a, int b, int c);

int main() {
	int row, col;
	int i, j;
	char **map;
	int **data;
	int able = FALSE;
	int up, down;

	scanf("%d %d", &row, &col);
	
	map = (char **)malloc(row * sizeof(char *));
	data = (int **)malloc(row * sizeof(int *));

	for (i = 0; i < row; i++) {
		map[i] = (char *)malloc((col + 1) * sizeof(char));
		data[i] = (int *)calloc(col, sizeof(int));
		scanf("%s", map[i]);
		for (j = 0; j < col; j++) {
			if (map[i][j] == 'O') {
				data[i][j] = 1;
			}
		}
	}

	for (j = col - 2; j >= 0 && able == FALSE; j--) {
		for (i = 0; i < row && able == FALSE; i++) {
			if (map[i][j] != '#') {

				if (i > 0) up = data[i - 1][j + 1];
				else up = 0;
				if (i < row - 1) down = data[i + 1][j + 1];
				else down = 0;

				if((data[i][j] = comp3max(up, data[i][j + 1], down)) == 0 && map[i][j] == 'O') data[i][j] = 1;

				if (map[i][j] == 'C' && data[i][j] > 0) {
					data[i][j]++;
				}
				if (map[i][j] == 'R') {
					printf("%d", data[i][j] - 1);
					able = TRUE;
				}
			}
		}
	}

	if (able == FALSE) {
		printf("-1");
	}

	for (i = 0; i < row; i++) {
		free(map[i]);
		free(data[i]);
	}
	free(map);
	free(data);


	return 0;
}

int comp3max(int a, int b, int c) {
	if (b > a) a = b;
	if (c > a) a = c;
	return a;
}