import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	// 비트 개수 많아봐야 30개니까
	// 각 숫자 많아봐야 1번 방문한다 치면
	// 10만개 숫자랑 인접한 거 보는데에 많아봐야 30*10만 = 3천만이니까 여유
	static int N, K;
	// <code, codeNum>
	static Map<Integer, Integer> map;
	static int[] codeNums;
	// codeNum이 어디에서 왔는지
	static int[] parent;
	// 답 빼내기위한 스택
	static int[] answerStack;
	// top
	static int top;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		map = new TreeMap<>();
		codeNums = new int[N + 1];
		// N개의 이진코드 정수로 변환 후 어.. 삽입
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			int num = 0;
			for (int j = 0; j < K; j++) {
				num <<= 1;
				num += str.charAt(j) - '0';
			}
			codeNums[i] = num;
			map.put(num, i);
		}

		Queue<Integer> queue = new LinkedList<>();
		parent[1] = -1;
		queue.add(1);
		while (!queue.isEmpty()) {
			int nowIdx = queue.poll();
			int now = codeNums[nowIdx];
			for (int d = 0; d < K; d++) {
				int next = now ^ (1 << d);
				if (!map.containsKey(next)) {
					continue;
				}
				int nextIdx = map.get(next);
				if (parent[nextIdx] != 0) {
					continue;
				}
				parent[nextIdx] = nowIdx;
				queue.add(nextIdx);
			}
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		answerStack = new int[100001];
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(br.readLine());
			if (parent[target] == 0) {
				sb.append("-1\n");
			} else {
				top = -1;
				while (target != -1) {
					answerStack[++top] = target;
					target = parent[target];
				}
				while (top >= 0) {
					sb.append(answerStack[top--]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}

}