#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>

int main() {
	int next;
	int total = 0;
	int i;
	
	for (i = 0; i < 10; i++) {
		scanf("%d", &next);
		if (total + next - 100 > 100 - total) {
			printf("%d", total);
			return 0;
		}
		total += next;
	}
	printf("%d", total);

	return 0;
}