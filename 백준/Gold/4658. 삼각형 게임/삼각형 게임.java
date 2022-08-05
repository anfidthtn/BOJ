import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Triangle {
		int[][] sides;

		public Triangle(int a, int b, int c) {
			this.sides = new int[][] {
				{a, b, c},
				{b, c, a},
				{c, a, b}
			};
		}
	}

	static Triangle[] triangles;
	static int maxScore;
	static int[] select;
	static boolean[] selected;
	static int[] shape;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			maxScore = 0;
			triangles = new Triangle[6];
			for (int i = 0; i < 6; i++) {
				st = new StringTokenizer(br.readLine());
				triangles[i] = new Triangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}
			select = new int[6];
			selected = new boolean[6];
			selected[0] = true;
			shape = new int[6];
			selectDfs(1);
			if (maxScore == 0) {
				sb.append("none");
			}
			else {				
				sb.append(maxScore);
			}
			sb.append("\n");		
			if (br.readLine().equals("$")) {
				break;
			}
		}
		System.out.print(sb.toString());
	}
	public static void selectDfs(int idx) {
		if (idx == 6) {
			shapeBF(0);
			return;
		}
		for(int i = 1; i < 6; i++) {
			if (selected[i]) {
				continue;
			}
			selected[i] = true;
			select[idx] = i;
			selectDfs(idx + 1);
			selected[i] = false;
		}
	}
	public static void shapeBF(int idx) {
		if (idx == 6) {
			if (check()) {
				maxScore = Math.max(maxScore, sideSum());
			}
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			shape[idx] = i;
			shapeBF(idx + 1);
		}
	}
	public static boolean check() {
		for(int i = 0; i < 6; i++) {
			if (triangles[select[i]].sides[shape[i]][0] != triangles[select[(i + 1) % 6]].sides[shape[(i + 1) % 6]][1]) {
				return false;
			}
		}
		return true;
	}
	public static int sideSum() {
		int sum = 0;
		for(int i = 0; i < 6; i++) {
			sum += triangles[select[i]].sides[shape[i]][2];
		}
		return sum;
	}
}