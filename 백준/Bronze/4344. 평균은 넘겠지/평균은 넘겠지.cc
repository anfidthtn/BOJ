#include<stdio.h>

int main() {
	int input_line;
	int input_st_num;
	int st_arr[1000];
	int i, j;
	float average;
	int count;
	float rate;

	scanf("%d", &input_line);

	for (i = 0; i < input_line; i++) {
		average = 0;
		count = 0;
		scanf("%d", &input_st_num);
		for (j = 0; j < input_st_num; j++) {
			scanf("%d", &st_arr[j]);
			average += st_arr[j];
		}
		average /= input_st_num;
		for (j = 0; j < input_st_num; j++) {
			if (average < st_arr[j]) {
				count++;
			}
		}
		rate = 100.0 * count / input_st_num;
		printf("%.3f%%\n", rate);
	}

	return 0;
}