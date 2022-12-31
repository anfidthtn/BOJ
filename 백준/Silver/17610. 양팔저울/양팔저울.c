#include<stdio.h>

int k;
int check[200000 * 13 + 1];
int weights[13];

void choice(int idx, int left, int right);

int main() {
	scanf("%d", &k);
	int sum = 0;
	for (int i = 0; i < k; i++) {
		scanf("%d", &weights[i]);
		sum += weights[i];
	}
	choice(0, 0, 0);
	int count = 0;
	for (int i = 1; i <= sum; i++) {
		if (!check[i]) {
			count++;
		}
	}
	printf("%d", count);
}
void choice(int idx, int left, int right) {
	if (idx == k) {
		if (left > right) {
			check[left - right] = 1;
		}
		else {
			check[right - left] = 1;
		}
		return;
	}
	choice(idx + 1, left, right);
	choice(idx + 1, left + weights[idx], right);
	choice(idx + 1, left, right + weights[idx]);
}