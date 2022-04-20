#include<stdio.h>
#include<stdlib.h>

int main() {
	int grade;
	
	scanf("%d", &grade);

	if (90 <= grade && grade <= 100) {
		printf("%c", 'A');
	}
	else if (80 <= grade) {
		printf("%c", 'B');
	}
	else if (70 <= grade) {
		printf("%c", 'C');
	}
	else if (60 <= grade) {
		printf("%c", 'D');
	}
	else{
		printf("%c", 'F');
	}
		

	return 0;
}