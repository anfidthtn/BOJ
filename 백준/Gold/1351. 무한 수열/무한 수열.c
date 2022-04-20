#define _CRT_SECURE_NO_WARNINGS

#define MAXSIZE 512

#include<stdio.h>
#include<stdlib.h>

long long save[MAXSIZE][2] = { 0, };

long long findElement(long long num);

//recur'save' :D
long long recursave(long long num, long long P, long long Q);

int main() {
	long long N, P, Q;

	scanf("%lld %lld %lld", &N, &P, &Q);

	printf("%lld", recursave(N, P, Q));

	return 0;
}
long long recursave(long long num, long long P, long long Q) {
	long long index = 0;
	if (num == 0) {
		return 1;
	}
	index = findElement(num);
	if (!index) {
		printf("ARROVERFLOW\n");
		exit(-1);
	}
	if (index > 0) {
		return index;
	}
	if (index < 0) {
		return save[(-1) * index][1] = recursave(num / P, P, Q) + recursave(num / Q, P, Q);
	}
}
long long findElement(long long num) {
	int i;
	for (i = 1; i < MAXSIZE; i++) {
		if (save[i][0] == 0) {
			save[i][0] = num;
			return -1 * i; // non-calculated, return : (-1) * arr index
		}
		if (save[i][0] == num) {
			if (save[i][1] > 0) {
				return save[i][1];
			}
			else {
				return -1 * i; // non-calculated, return : (-1) * arr index
			}
		}
	}
	return -0; // overflow
}