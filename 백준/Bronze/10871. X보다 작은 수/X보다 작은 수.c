#include<stdio.h>
#include<stdlib.h>

int main() {
	int n, x;
	int i;
	int temp;

	scanf("%d %d", &n, &x);

	for (i = 0;i < n;i++) {
		scanf("%d", &temp);
		if (temp < x) {
			printf("%d ", temp);
		}
	}

	return 0;
}