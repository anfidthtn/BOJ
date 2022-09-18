import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main {
	static class Node{
		Node parent;
		String name;
		public Node(String name) {
			this.name = name;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Node> map = new TreeMap<>();
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String childS = st.nextToken();
			String parentS = st.nextToken();
			Node child = map.getOrDefault(childS, new Node(childS));
			Node parent = map.getOrDefault(parentS, new Node(parentS));
			map.put(childS, child);
			map.put(parentS, parent);
			child.parent = parent;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		if (check(map.get(a), b) || check(map.get(b), a)) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}
	public static boolean check(Node now, String other) {
		if (now == null) {
			return false;
		}
		if (other.equals(now.name)) {
			return true;
		}
		return check(now.parent, other);
	}
}