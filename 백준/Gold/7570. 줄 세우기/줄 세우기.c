#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>

#define MAXSIZE 1000001

int arr[MAXSIZE] = { 0, };

int main() {
	int count;
	int i;
	int data;
	int max = 0;

	scanf("%d", &count);
	for (i = 0; i < count; i++) {
		scanf("%d", &data);
		arr[data] = arr[data - 1] + 1;
		if (arr[data] > max) max = arr[data];
	}
	printf("%d", count - max);
}