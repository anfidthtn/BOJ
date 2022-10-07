import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		char name;
		Node lchild, rchild;

		public Node(char name) {
			this.name = name;
		}
	}
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node((char)(i + 'A'));
		}
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char n = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			if (l != '.') {
				nodes[n - 'A'].lchild = nodes[l - 'A'];
			}
			if (r != '.') {
				nodes[n - 'A'].rchild = nodes[r - 'A'];
			}
		}
		sb = new StringBuilder();
		preorder(nodes[0]);
		sb.append("\n");
		inorder(nodes[0]);
		sb.append("\n");
		postorder(nodes[0]);
		System.out.print(sb.toString());
	}
	public static void preorder(Node node) {
		sb.append(node.name);
		if (node.lchild != null) {
			preorder(node.lchild);
		}
		if (node.rchild != null) {
			preorder(node.rchild);
		}
	}
	public static void inorder(Node node) {
		if (node.lchild != null) {
			inorder(node.lchild);
		}
		sb.append(node.name);
		if (node.rchild != null) {
			inorder(node.rchild);
		}
	}
	public static void postorder(Node node) {
		if (node.lchild != null) {
			postorder(node.lchild);
		}
		if (node.rchild != null) {
			postorder(node.rchild);
		}
		sb.append(node.name);
	}
}