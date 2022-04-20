#include<stdio.h>
#include<stdlib.h>

int main() {
	int input;
	int i;
	int a, b;

	scanf("%d", &input);

	for (i = 0;i < input;i++) {
		scanf("%d %d", &a, &b);
		printf("%d\n", a + b);
	}
	return 0;
}