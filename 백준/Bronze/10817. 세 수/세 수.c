#include<stdio.h>
#include<stdlib.h>

void compare(int *x, int *y);

int main() {
	int a, b, c;

	scanf("%d %d %d", &a, &b, &c);

	compare(&a, &b);
	compare(&a, &c);
	compare(&b, &c);

	printf("%d", b);

	return 0;
}
void compare(int *x, int *y) {
	int temp;

	if (*y < *x) {
		temp = *x;
		*x = *y;
		*y = temp;
	}
}