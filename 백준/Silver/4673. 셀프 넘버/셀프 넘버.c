#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>

int d(int n) {
	int sum = n;

	while (n) {
		sum += n % 10;
		n /= 10;
	}

	return sum;
}

int main() {
	int i;
	int temp;
	int isSelf[10001] = { 0 };
	for (i = 1; i <= 10000; i++) {
		temp = d(i);
		if (temp <= 10000) {
			isSelf[temp] = 1;
		}
	}
	for (i = 1; i <= 10000; i++) {
		if (!isSelf[i]) printf("%d\n", i);
	}

	return 0;
}