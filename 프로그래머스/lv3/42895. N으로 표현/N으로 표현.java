import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Solution {
	List<Integer>[] list;
	int[] visited;

	public int solution(int N, int number) {
		int answer = 0;
		visited = new int[1000000];
		list = new ArrayList[8];
		int num = N;
		for (int i = 1; i <= 7; i++) {
			list[i] = new ArrayList<>();
			if (i != 7) {
				visited[num] = i;
				list[i].add(num);
			}
			num *= 10;
			num += N;
		}
		for (int i = 1; i <= 7; i++) {
			for (int j = 1; j <= i && i + j <= 8; j++) {
				for (int a : list[i]) {
					for (int b : list[j]) {
						check(a + b, i + j);
						check(a - b, i + j);
						check(a * b, i + j);
						if (b != 0)
							check(a / b, i + j);
						if (a != 0)
							check(b / a, i + j);
						check(b - a, i + j);
						if (visited[number] > 0) {
							return visited[number];
						}
					}
				}
			}
		}
		return -1;
	}

	public void check(int num, int idx) {
		if (num < 0 || 1000000 < num) {
			return;
		}
		if (visited[num] == 0) {
			visited[num] = idx;
			if (idx < 7)
				list[idx].add(num);
		}
	}
}