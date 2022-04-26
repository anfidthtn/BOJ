import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] group = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			group[i] = i;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(order) {
			case 0:
				merge(group, a, b);
				break;
			case 1:
				check(sb, group, a, b);
				break;
			}
		}
		System.out.println(sb.toString());
	}
	public static int findParent(int[] group, int num) {
		if (group[num] == num) {
			return num;
		}
		return group[num] = findParent(group, group[num]);
	}
	public static void merge(int[] group, int a, int b) {
		group[findParent(group, a)] = findParent(group, b);
	}
	public static void check(StringBuilder sb, int[] group, int a, int b) {
		if (findParent(group, a) == findParent(group, b)) {
			sb.append("YES\n");
		}
		else {
			sb.append("NO\n");			
		}
	}
}