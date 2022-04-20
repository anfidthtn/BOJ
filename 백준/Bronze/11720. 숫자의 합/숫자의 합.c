#include<stdio.h>
#include<stdlib.h>

int main() {
	int input;
	int i;
	char string_arr[101];
	int sum = 0;

	scanf("%d", &input);
	
	getchar();

	for (i = 0;i < input;i++) {
		sum += getchar() - '0';
	}

	printf("%d\n", sum);

	return 0;
}