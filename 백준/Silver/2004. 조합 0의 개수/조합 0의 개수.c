#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>
#include<math.h>

#define MAX 2000000000

long long check(long long num, long long n);
long long Min(long long a, long long b);
int main() {
	long long n, m;
	long long two, five;
	scanf("%lld %lld", &n, &m);

	two = check(n, 2) - check(n - m, 2) - check(m, 2);
	five = check(n, 5) - check(n - m, 5) - check(m, 5);
	printf("%lld", Min(two, five));

	return 0;
}

long long check(long long num, long long n) {
	long long sum = 0;
	while (num) {
		sum += num /= n;
	}
	return sum;
}
long long Min(long long a, long long b) {
	return a > b ? b : a;
}