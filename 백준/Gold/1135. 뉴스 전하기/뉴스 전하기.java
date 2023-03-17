import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] childs;
	static List<Integer>[] needs;

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		childs = new ArrayList[N + 1];
		needs = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			childs[i] = new ArrayList<>();
			needs[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2; i <= N; i++) {
			childs[Integer.parseInt(st.nextToken()) + 1].add(i);
		}
		System.out.print(dfs(1));
	}

	public static int dfs(int now) {
		for (int next : childs[now]) {
			needs[now].add(dfs(next));
		}
		needs[now].sort((a, b) -> b - a);
		int ret = 0;
		for (int i = 0; i < needs[now].size(); i++) {
			ret = Math.max(ret, i + 1 + needs[now].get(i));
		}
		return ret;
	}
}