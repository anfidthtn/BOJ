#include<stdio.h>

#define E_MAX 15
#define S_MAX 28
#define M_MAX 19

int return_E(int x);
int return_S(int x);
int return_M(int x);

int main() {
	int e, s, m;
	int count = 0;

	scanf("%d %d %d", &e, &s, &m);

	while (1) {
		count++;
		if (return_E(count) == e && return_S(count) == s && return_M(count) == m) {
			printf("%d", count);
			break;
		}
	}

	return 0;
}
int return_E(int x) {
	while (x > E_MAX) {
		x -= E_MAX;
	}
	return x;
}
int return_S(int x) {
	while (x > S_MAX) {
		x -= S_MAX;
	}
	return x;
}
int return_M(int x) {
	while (x > M_MAX) {
		x -= M_MAX;
	}
	return x;
}