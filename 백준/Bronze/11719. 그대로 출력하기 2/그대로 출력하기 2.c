#include<stdio.h>
#include<stdlib.h>

int main() {
	char one;

	while ((one = getchar()) != EOF) {
		putchar(one);
	}

	return 0;
}