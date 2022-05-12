class Solution {
	public long solution(String expression) {
		long answer = 0;
		String[] numsString = expression.split("-|\\*|\\+");
		int numCount = numsString.length;
		char[] oper = new char[numCount - 1];

		for (int i = 0, idx = 0; i < expression.length(); i++) {
			switch (expression.charAt(i)) {
			case '+':
			case '-':
			case '*':
				oper[idx++] = expression.charAt(i);
			}
		}
		char[][] orders = {
				{'+', '-', '*'},
				{'-', '+', '*'},
				{'+', '*', '-'},
				{'-', '*', '+'},
				{'*', '+', '-'},
				{'*', '-', '+'},
		};
		for(char[] order : orders) {
			answer = Math.max(answer, Math.abs(getResult(numsString, oper, numCount, order)));
		}
		return answer;
	}

	public long getResult(String[] numsString, char[] oper, int numCount, char[] order) {
		long[] nums = new long[numCount];
		int[] parent = new int[numCount];
		for (int i = 0; i < numCount; i++) {
			nums[i] = Long.parseLong(numsString[i]);
			parent[i] = i;
		}
		for(char now : order) {
			for(int i = 0; i < numCount - 1; i++) {
				if (oper[i] == now) {
					int pa = getParent(parent, i);
					int pb = getParent(parent, i + 1);
					long a = nums[pa];
					long b = nums[pb];
					switch (oper[i]) {
					case '+':
						nums[pa] = a + b;
						break;
					case '-':
						nums[pa] = a - b;
						break;
					case '*':
						nums[pa] = a * b;
						break;
					}
					parent[pb] = pa;
				}
			}
		}
		return nums[0];
	}
	public int getParent(int[] parent, int now) {
		if (parent[now] == now) {
			return now;
		}
		return parent[now] = getParent(parent, parent[now]);
	}
}