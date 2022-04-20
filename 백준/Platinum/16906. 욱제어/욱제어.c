#include<stdio.h>
#include<stdlib.h>

int test_bit[1000] = { 0 };


int bit_up(int index);
int make_word(int len, char *space); // 단어 만들기
int qcompare(const void * first, const void * second); // 내림차순

int main() {
	int i, j;
	int isfailed = 0;
	
	int input;
	int *input_arr, *sorted_arr;
	char **dictionary;

	scanf("%d", &input);

	input_arr = (int *)malloc(input * sizeof(int)); // 길이를 순서대로 넣을 배열
	sorted_arr = (int *)malloc(input * sizeof(int)); // 소팅해서 둘 배열
	dictionary = (char **)malloc(input * sizeof(char *)); // 단어들을 담을 배열

	for (i = 0; i < input; i++) {
		scanf("%d", &(input_arr[i]));
		sorted_arr[i] = input_arr[i];
	}
	qsort(sorted_arr, input, sizeof(int), qcompare);

	dictionary[0] = (char *)malloc((sorted_arr[0] + 1) * sizeof(char)); // 가장 긴 단어는 전부 0으로 채우기 위해서 따로 뺌
	make_word(sorted_arr[0] + 1, dictionary[0]); // 000000...  채워버리기
	dictionary[0][sorted_arr[0]] = '\0';

	for (i = 1; i < input; i++) { // 가장 긴 단어 제외하고는 밑에서부터 1씩 채워올림
		dictionary[i] = (char *)malloc((sorted_arr[i] + 1) * sizeof(char));
		dictionary[i][sorted_arr[i]] = '\0';
		if (make_word(sorted_arr[i], dictionary[i])) { // bit overflow되면 1을 반환해서 실패로 간주
			isfailed = 1;
			// break를 안한 이유는 free코드 따로 짜기 귀찮아서..
		}
	}

	if (isfailed) { // 비트 오버나면 실패
		printf("-1");
	}
	else {
		printf("1\n");
		for (i = 0; i < input; i++) {
			for (j = 0; j < input; j++) {
				if (input_arr[i] == sorted_arr[j]) { // sorted_arr가 visit arr역할
					printf("%s\n", dictionary[j]);
					sorted_arr[j] = 0;
					break;
				}
			}
		}
	}

	for (i = 0; i < input; i++) {
		free(dictionary[i]);
	}
	free(dictionary);
	free(sorted_arr);
	free(input_arr);

	return 0;
}

int bit_up(int index) { 
	if (test_bit[index] == 0) {
		test_bit[index] = 1;
		return 0;
	}
	else{
		if (index == 0) { // bit overflow == fail
			return 1;
		}
		else{
			test_bit[index] = 0;
			bit_up(index - 1);
			return 0;
		}
	}
}

int make_word(int len, char *space) {
	int i;

	if (bit_up(len - 1)) { // bit overflow == fail
		return 1;
	}
	else {
		for (i = 0; i < len; i++) {
			space[i] = '0' + test_bit[i];
		}
		return 0;
	}
}

int qcompare(const void * first, const void * second) {
	if (*(int*)first < *(int *)second) {
		return 1;
	}
	else if (*(int *)first < *(int *)second) {
		return -1;
	}
	else {
		return 0;
	}
}