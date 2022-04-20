import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		int[] parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			int p = findParent(parent, g);
			if (p == 0) {
				System.out.print(i);
				return;
			}
			parent[p] = findParent(parent, p - 1);
		}
		System.out.print(P);
	}
	public static int findParent(int[] parent, int now) {
		if (parent[now] == now) return now;
		return parent[now] = findParent(parent, parent[now]);
	}
}