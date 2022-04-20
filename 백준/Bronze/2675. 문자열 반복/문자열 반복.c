#include<stdio.h>
#include<string.h>

int main() {
	int input_line;
	int input_iterate;
	char word[21];
	int i, j, k;

	scanf("%d", &input_line);

	for (i = 0; i < input_line; i++) {
		scanf("%d", &input_iterate);
		getchar();			//fflush(stdin)
		gets(word);
		for (j = 0; word[j] != '\0'; j++) {
			for (k = 0; k < input_iterate; k++) {
				printf("%c", word[j]);
			}
		}
		printf("\n");
	}

	return 0;
}