#include<stdio.h>
#include<string.h>

int main() {
	char word[101];
	char short_word[101];
	int i, j;
	gets(word);
	short_word[0] = word[0];
	for (i = 1, j = 1; i < strlen(word);i++) {
		if (word[i] != '-') {
			continue;
		}
		short_word[j] = word[i + 1];
		j++;
	}
	short_word[j] = '\0';
	printf("%s", short_word);
	return 0;
}