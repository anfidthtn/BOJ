#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>

long long WRATE(long long total, long long wins) {
	return 100 * wins / total;
}

int main() {
	long long total, wins;
	long long min = 1, max = 100000000000;
	long long cur = (min + max) / 2;
	long long winningrate = 0;

	scanf("%lld %lld", &total, &wins);

	winningrate = WRATE(total, wins);

	if (winningrate >= 99) {
		printf("-1");
		return 0;
	}

	while (1) {
		if (WRATE(total + cur, wins + cur) > winningrate) {
			if (min == max) {
				printf("%lld", cur);
				break;
			}
			else {
				max = cur;
			}
		}
		else {
			min = cur + 1;
		}
		cur = (min + max) / 2;
	}

	return 0;
}