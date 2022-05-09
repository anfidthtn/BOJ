import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	static class Node{
		List<Node> link;
		StringBuilder sb;
		@Override
		public String toString() {
			if (sb == null) {
				if (link.size() == 0) {
					return "01";
				}
				else {
					sb = new StringBuilder();
					sb.append("0");
					link.sort(new Comparator<Node>() {
						@Override
						public int compare(Node o1, Node o2) {
							return o1.toString().compareTo(o2.toString());
						}
					});
					for(Node next : link) {
						sb.append(next.toString());
					}
					sb.append("1");
					return sb.toString();
				}
			}
			else {
				return sb.toString();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			String a = br.readLine();
			int[] idxA = new int[] { 0 };
			Node rootA = new Node();
			dfs(rootA, a, idxA);
			String b = br.readLine();
			int[] idxB = new int[] { 0 };
			Node rootB = new Node();
			dfs(rootB, b, idxB);
			if (rootA.toString().compareTo(rootB.toString()) == 0) {
				sb.append("1\n");
			}
			else {
				sb.append("0\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static void dfs(Node node, String str, int[] idx) {
		node.link = new ArrayList<>();
		while(idx[0] < str.length() && str.charAt(idx[0]++) == '0') {
			Node next = new Node();
			node.link.add(next);
			dfs(next, str, idx);
		}
	}
}