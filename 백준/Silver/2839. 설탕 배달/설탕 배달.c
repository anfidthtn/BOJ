#include<stdio.h>
#include<stdlib.h>

int return_count(int a, int b, int amount);
int main() {
	int amount;

	scanf("%d", &amount);

	printf("%d", return_count(3, 5, amount));

	return 0;
}
int return_count(int a, int b, int amount){
	int count = 0, sum = 0, save_count, save_sum;

	while (amount > sum) {
		sum += b;
		count++;
		if (amount == sum) {
			return count;
		}
	}
	do{
		sum -= b;
		count--;
		save_sum = sum;
		save_count = count;
		while (amount > sum) {
			sum += a;
			count++;
			if (amount == sum) {
				return count;
			}
		}
		sum = save_sum;
		count = save_count;
	} while (sum > 0);
	return -1;
}