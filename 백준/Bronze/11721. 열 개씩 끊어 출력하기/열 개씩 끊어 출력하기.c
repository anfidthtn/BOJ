#include<stdio.h>
#include<stdlib.h>

int main() {
	char string_arr[102];
	int i;
	
	gets(string_arr);

	for (i = 0;string_arr[i] != '\0'; i++) {
		printf("%c", string_arr[i]);
		if (i % 10 == 9) {
			printf("\n");
		}
	}

	return 0;
}