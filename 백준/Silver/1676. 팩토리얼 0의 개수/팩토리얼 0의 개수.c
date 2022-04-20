#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>


int check(int num, int n);
int main() {
	int n;
	scanf("%d", &n);

	printf("%d", check(n, 5));

	return 0;
}

int check(int num, int n) {
	int sum = 0;
	while (num) {
		sum += num /= n;
	}
	return sum;
}