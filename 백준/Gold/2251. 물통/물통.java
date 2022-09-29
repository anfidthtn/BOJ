import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static boolean[][][] visited;
	static int A, B, C;
	static Set<Integer> answers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[A + 1][B + 1][C + 1];
		answers = new TreeSet<>();
		answers.add(C);
		check(0, 0, C);
		List<Integer> answerList = new ArrayList<>(answers);
		answerList.sort(Integer::compareTo);
		for (int a : answerList) {
			System.out.print(a + " ");
		}
	}

	public static void check(int a, int b, int c) {
		if (visited[a][b][c]) {
			return;
		}
		if (a == 0) {
			answers.add(c);
		}
		visited[a][b][c] = true;
		check(a + Math.min(A - a, b), b - Math.min(A - a, b), c);
		check(a, b + Math.min(B - b, c), c - Math.min(B - b, c));
		check(a - Math.min(C - c, a), b, c + Math.min(C - c, a));
		check(a, b - Math.min(C - c, b), c + Math.min(C - c, b));
		check(a - Math.min(B - b, a), b + Math.min(B - b, a), c);
		check(a + Math.min(A - a, c), b, c - Math.min(A - a, c));
	}
}