#include<stdio.h>
#include<string.h>

int main() {
	char word[101];
	int count = 0;
	char word_list[8][4] = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	int i, j;
	gets(word);

	for (i = 0; i < strlen(word);) {
		for (j = 0; j < 8; j++) {
			if (!strncmp(word + i, word_list + j, strlen(word_list[j]))) {
				count++;
				i += strlen(word_list[j]);
				break;
			}
		}
		if (j != 8) {
			continue;
		}
		count++;
		i++;
	}
	printf("%d", count);

	return 0;
}