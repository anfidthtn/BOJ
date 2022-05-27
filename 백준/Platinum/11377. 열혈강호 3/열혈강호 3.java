import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] link;
	static int[] selectEmpNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		link = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			link[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				link[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		selectEmpNum = new int[M + 1];
		int count = 0;
		for(int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[M + 1];
			if(matching(i, visited)) {
				count++;
			}
		}
		for(int i = 1; i <= N && K > 0; i++) {
			boolean[] visited = new boolean[M + 1];
			if(matching(i, visited)) {
				K--;
				count++;
			}
		}
		System.out.print(count);
	}
	public static boolean matching(int now, boolean[] visited) {
		for(int next : link[now]) {
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			if (selectEmpNum[next] == 0 || matching(selectEmpNum[next], visited)) {
				selectEmpNum[next] = now;
				return true;
			}
		}
		return false;
	}
}