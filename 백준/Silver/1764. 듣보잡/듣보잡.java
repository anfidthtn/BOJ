import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Node implements Comparable<Node>{
		long a;
		long b;
		public Node(long a, long b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Node o) {
			if (this.a - o.a < 0) {
				return -1;
			}
			else if (this.a - o.a > 0) {
				return 1;
			}
			else if (this.b - o.b < 0) {
				return -1;
			}
			else if (this.b - o.b > 0) {
				return 1;
			}
			return 0;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<Node> hear = new TreeSet<>();
		for(int i = 0; i < N; i++) {
			hear.add(toNode(br.readLine()));
		}
		List<Node> results = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			Node target = toNode(br.readLine());
			if (hear.contains(target)) {
				results.add(target);
			}
		}
		results.sort(Node::compareTo);
		System.out.println(results.size());
		StringBuilder sb = new StringBuilder();
		for(Node result : results) {
			sb.append(nodeToString(result)).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static Node toNode(String str) {
		long a = 0;
		long b = 0;
		for(int i = 0; i < 20; i++) {
			if (i < 10) {
				a <<= 5;
				if (i < str.length()) {
					a += str.charAt(i) - 'a' + 1;
				}
			}
			else {
				b <<= 5;
				if (i < str.length()) {
					b += str.charAt(i) - 'a' + 1;
				}
			}
		}
		return new Node(a, b);
	}
	public static String nodeToString(Node node) {
		StringBuilder sb = new StringBuilder();
		boolean finish = false;
		for(int i = 9; i >= 0 && !finish; i--) {
			if((node.a & (long) 31 << (i * 5)) == 0) {
				finish = true;
				break;
			}
			sb.append((char) (((node.a & (long) 31 << (i * 5)) >> (i * 5)) + 'a' - 1));
		}
		for(int i = 9; i >= 0 && !finish; i--) {
			if((node.b & (long) 31 << (i * 5)) == 0) {
				finish = true;
				break;
			}
			sb.append((char) (((node.b & (long) 31 << (i * 5)) >> (i * 5)) + 'a' - 1));
		}
		return sb.toString();
	}
}