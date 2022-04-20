#include<stdio.h>
#include<stdlib.h>

int main() {
	int input;
	int i, j;

	scanf("%d", &input);

	for (i = 0;i < input;i++) {
		for (j = 0;j < i + 1;j++) {
			printf("*");
		}
		printf("\n");
	}
	return 0;
}