import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Stub {
		int value;
		int start;
		int height;
		int len;

		public Stub(int value, int start, int height, int len) {
			this.value = value;
			this.start = start;
			this.height = height;
			this.len = len;
		}

		public boolean jump(Stub other, int K) {
			// 이 객체에서 other 객체로 점프할 수 있는지 여부
			if (other.height < this.height) {
				return false;
			}
			int thisEnd = this.start + this.len;
			int otherEnd = other.start + other.len;
			if (this.start <= other.start) {
				if (thisEnd <= other.start) {
					if ((other.start - thisEnd) * (other.start - thisEnd)
							+ (this.height - other.height) * (this.height - other.height) <= K * K) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				if (otherEnd <= this.start) {
					if ((this.start - otherEnd) * (this.start - otherEnd)
							+ (this.height - other.height) * (this.height - other.height) <= K * K) {
						return true;
					} else {
						return false;
					}
				}
			}
			if ((this.height - other.height) * (this.height - other.height) <= K * K) {
				return true;
			} else {
				return false;
			}
		}
	}

	static class SccStub {
		Set<Integer> list;
		int value;

		public SccStub() {
			list = new HashSet<>();
			value = 0;
		}
	}

	static Stub[] stubs;
	static List<Integer>[] graph;
	static int sccGroupNum;
	static int visitNum;
	static int[] visit;
	static int[] sccIdx;
	static Stack<Integer> stack;

	static int startIdx;

	static List<SccStub> sccGraph;
	static int[] maxValue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		stubs = new Stub[N + 1];
		visitNum = 0;
		visit = new int[N + 1];
		sccIdx = new int[N + 1];
		graph = new ArrayList[N + 1];
		sccGraph = new ArrayList<>();
		sccGroupNum = 0;
		stack = new Stack<>();

		stubs[0] = new Stub(0, 0, 0, 11000);
		graph[0] = new ArrayList<>();
		visit[0] = -1;
		sccIdx[0] = -1;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			stubs[i] = new Stub(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			graph[i] = new ArrayList<>();
			visit[i] = -1;
			sccIdx[i] = -1;
		}

		for (int i = 0; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (stubs[i].jump(stubs[j], K)) {
					graph[i].add(j);
				}
				if (stubs[j].jump(stubs[i], K)) {
					graph[j].add(i);
				}
			}
		}

//		for (int i = 0; i <= N; i++) {
//			for (int next : graph[i]) {
//				System.out.print(next + " ");
//			}
//			System.out.println();
//		}

		for (int i = 0; i <= N; i++) {
			if (visit[i] == -1) {
				scc(i);
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int next : graph[i]) {
				if (sccIdx[i] == sccIdx[next]) {
					continue;
				}
				sccGraph.get(sccIdx[i]).list.add(sccIdx[next]);
			}
		}

		maxValue = new int[sccGroupNum];
		for (int i = 0; i < sccGroupNum; i++) {
			maxValue[i] = -1;
		}
		int result = 0;
//		System.out.println(startIdx);
		search(startIdx, 0);
		for (int i = 0; i < sccGroupNum; i++) {
			if (result < maxValue[i]) {
				result = maxValue[i];
			}
		}
		System.out.println(result);
	}

	public static void search(int now, int value) {
		if (maxValue[now] >= value + sccGraph.get(now).value) {
			return;
		}
		maxValue[now] = value + sccGraph.get(now).value;
		for (int next : sccGraph.get(now).list) {
			search(next, maxValue[now]);
		}
	}

	public static int scc(int now) {
		visit[now] = visitNum++;
		stack.push(now);

		int result = visit[now];
		for (int next : graph[now]) {
			if (visit[next] == -1) {
				result = Math.min(result, scc(next));
			} else if (sccIdx[next] == -1) {
				result = Math.min(result, visit[next]);
			}
		}

		if (result == visit[now]) {
			sccGraph.add(new SccStub());
			while (true) {
				int top = stack.pop();
				sccIdx[top] = sccGroupNum;
				sccGraph.get(sccGroupNum).value += stubs[top].value;
				if (top == 0) {
					startIdx = sccGroupNum;
				}
				if (top == now) {
					break;
				}
			}
			sccGroupNum++;
		}

		return result;
	}
}