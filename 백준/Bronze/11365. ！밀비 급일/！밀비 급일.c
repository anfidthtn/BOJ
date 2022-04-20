#include<stdio.h>
#include<string.h>

int main() {
	char word[501];
	int i;
	while (strcmp(gets(word), "END")) {
		for (i = strlen(word) - 1; i >= 0; i--) {
			printf("%c", word[i]);
		}
		printf("\n");
	}
	return 0;
}