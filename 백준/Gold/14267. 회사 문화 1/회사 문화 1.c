#include<stdio.h>
#include<stdlib.h>

typedef struct ListNode {
	int nodeNum;
	struct ListNode* next;
}ListNode;

typedef struct Node {
	int value;
	ListNode* head, * rear;
}Node;

void addNode(Node* node, int childIdx) {
	ListNode* temp = malloc(sizeof(ListNode));
	temp->nodeNum = childIdx;
	temp->next = NULL;
	if (!node->rear) {
		node->head = temp;
		node->rear = temp;
	}
	else {
		node->rear->next = temp;
		node->rear = temp;
	}
}
void dfs(Node* node, int value);

Node nodes[100010];

int n, m, answer;

int main() {
	scanf("%d %d", &n, &m);
	int num, a, b;
	for (int i = 1; i <= n; i++) {
		nodes[i] = (Node){ 0, NULL, NULL };
	}
	for (int i = 1; i <= n; i++) {
		scanf("%d", &num);
		if (num == -1) {
			continue;
		}
		addNode(&nodes[num], i);
	}
	for (int i = 0; i < m; i++) {
		scanf("%d %d", &a, &b);
		nodes[a].value += b;
	}
	answer = 0;
	dfs(&nodes[1], 0);
	for (int i = 1; i <= n; i++) {
		printf("%d ", nodes[i].value);
	}

	return 0;
}
void dfs(Node* node, int value) {
	node->value += value;
	ListNode* next = node->head;
	while (next) {
		dfs(&nodes[next->nodeNum], node->value);
		next = next->next;
	}
}