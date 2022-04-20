#include<stdio.h>
#include<stdlib.h>

#define ROW 12
#define COL 6

char map[ROW][COL];
int check[ROW][COL];

int able_check();

void check_refresh();

void block_distroy(int number);

void map_refresh();


int point_check(int row, int col, int number, char color, int count);

int main() {
	int i, j;

	int count = 0;

	for (i = 0; i < ROW; i++) {
		for (j = 0; j < COL; j++) {
			map[i][j] = getc(stdin);
		}
		getc(stdin);
	}

	while (able_check() > 0) {
		count++;
		map_refresh();
	}

	printf("%d", count);

	return 0;
}

int able_check() {
	int i, j;
	int number = 1;

	int isdistroyed = 0;

	check_refresh();

	for (i = 0; i < ROW; i++) {
		for (j = 0; j < COL; j++) {
			number++;
			if (point_check(i, j, number, map[i][j], 0) >= 4) {
				block_distroy(number);
				isdistroyed = 1;
			}
		}
	}

	return isdistroyed;
}

void check_refresh() {
	int i, j;

	for (i = 0; i < ROW; i++) {
		for (j = 0; j < COL; j++) {
			check[i][j] = 0;
		}
	}
}

void block_distroy(int number) {
	int i, j;

	for (i = 0; i < ROW; i++) {
		for (j = 0; j < COL; j++) {
			if (check[i][j] == number) {
				map[i][j] = '.';
			}
		}
	}
}

void map_refresh() {
	int i, j;

	int len;

	for (j = 0; j < COL; j++) {
		len = 0;
		for (i = ROW - 1; i >= 0; i--){
			if (map[i][j] == '.'){
				len++;
			}
			else {
				map[i + len][j] = map[i][j];
				if (len) {
					map[i][j] = '.';
				}
			}
		}
	}

}

int point_check(int row, int col, int number, char color, int count) {
	if (color == '.') {
		return count;
	}
	if (map[row][col] == color && check[row][col] == 0) {
		check[row][col] = number;
		count++;
		if (row > 0) {
			count = point_check(row - 1, col, number, color, count);
		}
		if (col > 0) {
			count = point_check(row, col - 1, number, color, count);
		}
		if (row < ROW - 1) {
			count = point_check(row + 1, col, number, color, count);
		}
		if (col < COL - 1) {
			count = point_check(row, col + 1, number, color, count);
		}
	}

	return count;
}