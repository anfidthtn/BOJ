import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class MinMax {
		int min, max;

		public MinMax() {
			this.min = 100001;
			this.max = -1;
		}
	}

	static class Node {
		Node parent, lchild, rchild;
	}

	static int N;
	static int nowNum;
	static MinMax[] minMax;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		minMax = new MinMax[N + 1];
		nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			minMax[i] = new MinMax();
			nodes[i] = new Node();
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (b > 0) {
				nodes[a].lchild = nodes[b];
				nodes[b].parent = nodes[a];
			}
			if (c > 0) {
				nodes[a].rchild = nodes[c];
				nodes[c].parent = nodes[a];
			}
		}
		nowNum = 0;
		Node root = nodes[1];
		while (root.parent != null) {
			root = root.parent;
		}

		search(root, 1);
		int ansDep = 0;
		int ansWid = 0;
		for (int i = N; i >= 1; i--) {
			int diff = minMax[i].max - minMax[i].min + 1;
			if (diff >= ansWid) {
				ansWid = diff;
				ansDep = i;
			}
		}
		System.out.print(ansDep + " " + ansWid);
	}

	public static void search(Node now, int depth) {
		if (now == null) {
			return;
		}
		search(now.lchild, depth + 1);
		nowNum++;
		minMax[depth].min = Math.min(minMax[depth].min, nowNum);
		minMax[depth].max = Math.max(minMax[depth].max, nowNum);
		search(now.rchild, depth + 1);
	}
}