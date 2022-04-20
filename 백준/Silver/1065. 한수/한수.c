#include<stdio.h>

int configure(int x);

int main() {
	int input;
	int i;
	int count = 0;

	scanf("%d", &input);

	for (i = 1; i <= input; i++) {
		if (configure(i)) {
			count++;
		}
	}
	printf("%d", count);
	

	return 0;
}

int configure(int x) {
	int sub;
	sub = (x / 10) % 10 - x % 10; // 10의 자리 - 1의 자리
	while (x >= 10) {
		if (sub != (x / 10) % 10 - x % 10) {
			return 0;
		}
		x /= 10;
	}
	return 1;
}