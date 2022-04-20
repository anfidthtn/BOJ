#include<stdio.h>

int main() {
	int input;
	int score;
	int max = 0;
	float new_average = 0;
	int i;

	scanf("%d", &input);

	for (i = 0;i < input;i++) {
		scanf("%d", &score);
		if (max < score) {
			max = score;
		}
		new_average += score;
	}
	new_average = new_average * 100 / max / input;

	printf("%.2f", new_average);

	return 0;
}