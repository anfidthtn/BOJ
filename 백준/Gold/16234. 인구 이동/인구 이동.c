#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>
#include<math.h>

int N, L, R;

typedef struct stackNode {
	int row, col;
	struct stackNode* next;
}stackNode;
typedef struct unionInfo {
	int unionSum;
	int count;
}unionInfo;

int** mainField, ** groupField;
int dir[2][4] = { {-1, 0, 1, 0}, {0, -1, 0, 1} };
unionInfo* unionInfoArr;

int isUnion(int a, int b);
void popNode(stackNode** head, int unionNumber);
void insertNode(stackNode** head, stackNode* node);
stackNode* newNode(int row, int col);
void unionApply();
int unionMatch();
void initialField(int** arr);
void printField(int** arr);
int main() {
	int i, j;
	int count = 0;
	scanf("%d %d %d", &N, &L, &R);

	mainField = (int**)malloc(sizeof(int*) * (N + 2));
	groupField = (int**)malloc(sizeof(int*) * (N + 2));
	for (i = 0; i < N + 2; i++) {
		mainField[i] = (int*)malloc(sizeof(int) * (N + 2));
		groupField[i] = (int*)malloc(sizeof(int) * (N + 2));
		for (j = 0; j < N + 2; j++) {
			if (i == 0 || j == 0 || i == N + 1 || j == N + 1) {
				mainField[i][j] = -1;
			}
			else {
				scanf("%d", &(mainField[i][j]));
			}
		}
	}
	while (unionMatch()) {
		unionApply();
		count++;
	}
	
	printf("%d", count);

	for (i = 0; i < N + 2; i++) {
		free(mainField[i]);
		free(groupField[i]);
	}
	free(mainField);
	free(groupField);
}
void printField(int** arr) {
	int i, j;
	printf("\n");
	for (i = 0; i < N + 2; i++) {
		for (j = 0; j < N + 2; j++) {
			printf("%3d ", arr[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}
void initialField(int** arr) {
	int i, j;
	for (i = 0; i < N + 2; i++) {
		for (j = 0; j < N + 2; j++) {
			if (i == 0 || j == 0 || i == N + 1 || j == N + 1) {
				arr[i][j] = -1;
			}
			else {
				arr[i][j] = 0;
			}
		}
	}
}
int unionMatch() {
	int i, j;
	int unionNumber = 0;
	stackNode* head = NULL;
	unionInfoArr = (unionInfo*)malloc(sizeof(unionInfo));

	initialField(groupField);

	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			if (groupField[i][j] == 0) {
				unionNumber++;
				unionInfoArr = (unionInfo*)realloc(unionInfoArr, sizeof(unionInfo) * unionNumber);
				unionInfoArr[unionNumber - 1].unionSum = 0;
				unionInfoArr[unionNumber - 1].count = 0;
				head = newNode(i, j);
				while (head) {
					unionInfoArr[unionNumber - 1].unionSum += mainField[head->row][head->col];
					unionInfoArr[unionNumber - 1].count ++;
					popNode(&head, unionNumber);
				}
			}
		}
	}
	if (unionNumber == N * N) {
		free(unionInfoArr);
		return 0;
	}
	else {
		return 1;
	}
}
void unionApply() {
	int i, j;
	
	if (unionInfoArr == NULL) {
		//error
		exit(111);
	}

	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			mainField[i][j] = unionInfoArr[groupField[i][j] - 1].unionSum / unionInfoArr[groupField[i][j] - 1].count;
		}
	}

	free(unionInfoArr);
}
stackNode* newNode(int row, int col) {
	stackNode* temp = (stackNode*)malloc(sizeof(stackNode));

	temp->row = row;
	temp->col = col;
	temp->next = NULL;

	return temp;
}
void insertNode(stackNode** head, stackNode* node) {
	stackNode* temp;
	temp = *head;
	while(temp->next){
		temp = temp->next;
	}
	temp->next = node;
}
void popNode(stackNode** head, int unionNumber) {
	int i;
	stackNode* temp;

	temp = *head;

	groupField[temp->row][temp->col] = unionNumber;

	for (i = 0; i < 4; i++) {
		if (groupField[temp->row + dir[0][i]][temp->col + dir[1][i]] == 0 && isUnion(mainField[temp->row][temp->col], mainField[temp->row + dir[0][i]][temp->col + dir[1][i]])) {
			insertNode(head, newNode(temp->row + dir[0][i], temp->col + dir[1][i]));
			groupField[temp->row + dir[0][i]][temp->col + dir[1][i]] = unionNumber;
		}
	}
	*head = (*head)->next;
	free(temp);
}
int isUnion(int a, int b) {
	int sub = abs(a - b);
	
	if (b < 0) {
		return 0;
	}
	else if (sub >= L && sub <= R) {
		return 1;
	}
	else {
		return 0;
	}
}