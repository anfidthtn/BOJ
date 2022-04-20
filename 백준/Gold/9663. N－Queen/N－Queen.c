#include<stdio.h>
#include<stdlib.h>

int count = 0;
int input = 1;

int check(int *arr, int row);

int main() {
	int arr[100] = {0};

	do {
		scanf("%d", &input);
	} while (input < 1);

	check(arr, 0);

	printf("%d\n", count);

	return 0;
}

int check(int *arr, int row) {
	int i, j;
	int isvalid = 1;

	if (row == input) {
		count++;
		return 1;
	}

	for (i = 0; i < input; i++) {
		isvalid = 1;
		for (j = 0; j < row; j++) {
			if (arr[j] == i || abs(arr[j] - i) == row - j) {
				isvalid = 0;
				break;
			}
		}
		if (!isvalid) {
			continue;
		}
		arr[row] = i;
		check(arr, row + 1);
	}
	return 0;
}