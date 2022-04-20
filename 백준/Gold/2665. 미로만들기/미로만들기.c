#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<stdlib.h>

#define MAXSIZE 100

#define CODE 0 // 0 : final // 1 : test

typedef struct point {
	int i;
	int j;
	int count;
	struct point *next;
}point;

char **maze;
int **D;
char **P;
int n;

point *head_node = NULL;

point *NewNode(int i, int j, int count);
void InsertNode(point **head, point *node);
void DeleteNode(point **node);
void SearchPoint(int i, int j);

#if(CODE == 1)
void PrintNode(point *node);
#endif

int main() {
	int i, j;

	scanf("%d", &n);

	maze = (char **)malloc(sizeof(char *) * (n + 2));
	D = (int **)malloc(sizeof(int *) * (n + 2));
	P = (char **)malloc(sizeof(char *) * (n + 2));
	for (i = 0; i < n + 2; i++) {
		maze[i] = malloc(sizeof(char) * (n + 2));
		D[i] = (int *)calloc(n + 2, sizeof(int));
		P[i] = (char *)calloc(n + 2, sizeof(char));
		if (i != 0 && i != n + 1) {
			scanf("%s", maze[i] + 1);
			maze[i][0] = '-';
			maze[i][n + 1] = '-';
			D[i][0] = -2;
			D[i][n + 1] = -2;
			P[i][0] = '-';
			P[i][n + 1] = '-';
		}
		else {
			for (j = 0; j < n + 2; j++) {
				maze[i][j] = '-';
				D[i][j] = -2;
				P[i][j] = '-';
			}
		}
	}

	SearchPoint(1, 1);
	while (head_node != NULL) {
#if(CODE == 1)
#endif
		if (head_node->i == n && head_node->j == n) {
			printf("%d\n", head_node->count);
			while (head_node != NULL) {
				DeleteNode(&head_node);
			}
			break;
		}
#if(CODE == 1)
		for (i = 0; i < n + 2; i++) {
			for (j = 0; j < n + 2; j++) {
				printf("%3c", maze[i][j]);
			}
			printf("\n");
		}
		printf("\n");
		for (i = 0; i < n + 2; i++) {
			for (j = 0; j < n + 2; j++) {
				printf("%3d", D[i][j]);
			}
			printf("\n");
		}
		printf("\n");
		for (i = 0; i < n + 2; i++) {
			for (j = 0; j < n + 2; j++) {
				printf("%3c", P[i][j]);
			}
			printf("\n");
		}
		PrintNode(head_node);
#endif
		SearchPoint(head_node->i, head_node->j);
	}

	//free start
	for (i = 0; i < n + 2; i++) {
		free(maze[i]);
		free(D[i]);
		free(P[i]);
	}
	free(maze);
	free(D);
	free(P);
	//free end

	return 0;
}
point *NewNode(int i, int j, int count) {
	point *temp = NULL;

	temp = (point *)malloc(sizeof(point));

	if (temp == NULL) {
	}
	else {
		temp->i = i;
		temp->j = j;
		temp->count = count;
		temp->next = NULL;
	}

	return temp;
}
void InsertNode(point **head, point *node) {
	point *temp, *temp2;

	if (*head == NULL) {
		*head = node;
		return;
	}

	temp = *head;
	temp2 = NULL;
	while (temp != NULL) {
		if (temp->count >= node->count) {
			if (temp == *head) {
				node->next = *head;
				*head = node;
			}
			else {
				temp2->next = node;
				node->next = temp;
			}
			break;
		}
		temp2 = temp;
		temp = temp->next;
	}
	if (temp == NULL) {
		temp2->next = node;
	}
}
void DeleteNode(point **node) {
	point *temp;

	if (*node == NULL) {
		return;
	}

	temp = *node;
	*node = (*node)->next;
	free(temp);
}

void SearchPoint(int i, int j) {
	DeleteNode(&head_node);
	if (D[i][j] < 0) {
		return;
	}
	if (D[i + 1][j] >= 0) {
		if (D[i + 1][j] == 0) {
			if (maze[i + 1][j] == '0') {
				D[i + 1][j] = D[i][j] + 1;
			}
			else {
				D[i + 1][j] = D[i][j];
			}
			P[i + 1][j] = 'U';
		}
		InsertNode(&head_node, NewNode(i + 1, j, D[i + 1][j]));
	}
	if (D[i][j + 1] >= 0) {
		if (D[i][j + 1] == 0) {
			if (maze[i][j + 1] == '0') {
				D[i][j + 1] = D[i][j] + 1;
			}
			else {
				D[i][j + 1] = D[i][j];
			}
			P[i][j + 1] = 'L';
		}
		InsertNode(&head_node, NewNode(i, j + 1, D[i][j + 1]));
	}
	if (D[i - 1][j] >= 0) {
		if (D[i - 1][j] == 0) {
			if (maze[i - 1][j] == '0') {
				D[i - 1][j] = D[i][j] + 1;
			}
			else {
				D[i - 1][j] = D[i][j];
			}
			P[i - 1][j] = 'D';
		}
		InsertNode(&head_node, NewNode(i - 1, j, D[i - 1][j]));
	}
	if (D[i][j - 1] >= 0) {
		if (D[i][j - 1] == 0) {
			if (maze[i][j - 1] == '0') {
				D[i][j - 1] = D[i][j] + 1;
			}
			else {
				D[i][j - 1] = D[i][j];
			}
			P[i][j - 1] = 'R';
		}
		InsertNode(&head_node, NewNode(i, j - 1, D[i][j - 1]));
	}
	D[i][j] = -1;
}
#if(CODE == 1)
void PrintNode(point *node) {
	int i = 0;
	while (node) {
		printf("%2d %2d %2d / ", node->i, node->j, node->count);
		if ((++i) % 5 == 0) {
			printf("\n");
		}
		node = node->next;
	}
}
#endif