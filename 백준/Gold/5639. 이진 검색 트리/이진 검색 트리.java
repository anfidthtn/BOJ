import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Node {
		int data;
		Node left, right, parents;

		public Node(int data) {
			this.data = data;
		}
	}

	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		Node root = new Node(Integer.parseInt(br.readLine()));
		while ((line = br.readLine()) != null) {
			set(root, Integer.parseInt(line));
		}
		sb = new StringBuilder();
		search(root);
		System.out.print(sb);
	}

	public static void set(Node now, int data) {
		if (data < now.data) {
			if (now.left == null) {
				now.left = new Node(data);
			} else {
				set(now.left, data);
			}
		} else {
			if (now.right == null) {
				now.right = new Node(data);
			} else {
				set(now.right, data);
			}
		}
	}

	public static void search(Node now) {
		if (now == null) {
			return;
		}
		search(now.left);
		search(now.right);
		sb.append(now.data).append("\n");
	}
}