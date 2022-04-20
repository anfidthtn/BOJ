#define _CRT_SECURE_NO_WARNINGS
#define MAX_BIT 25
#define INT_BIT 32

#include<stdio.h>

int isvisit[(1 << MAX_BIT) / INT_BIT] = { 0, };

int main() {
	int temp;
	int set, bit;
	while (scanf("%d", &temp) != EOF) {
		set = temp / INT_BIT;
		bit = temp % INT_BIT;

		if ((isvisit[set] & (1 << bit)) == 0) {
			isvisit[set] += (1 << bit);
			printf("%d ", temp);
		}
	}

	return 0;
}