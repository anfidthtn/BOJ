#include<stdio.h>
#include<string.h>

int main() {
	char word[100];
	char i;
	int index;
	int j;

	gets(word);
	for (i = 'a';i <= 'z'; i++) {
		index = -1;
		for (j = 0;j < strlen(word);j++) {
			if (word[j] == i) {
				index = j;
				break;
			}
		}
		printf("%d ", index);
	}

	return 0;
}