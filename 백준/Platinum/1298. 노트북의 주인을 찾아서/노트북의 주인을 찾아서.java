import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> link = new ArrayList<>();
		link.add(null);
		for(int i = 1; i <= N; i++) {
			link.add(new LinkedList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			link.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
		}
		int[] selected = new int[N + 1];
		
		int count = 0;
		
		for(int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			if (match(link, selected, visited, i)) {
				count++;
			}
		}
		System.out.println(count);
	}
	public static boolean match(List<List<Integer>> link, int[] selected, boolean[] visited, int nowStNum) {
		for(int notebook : link.get(nowStNum)) {
			if(visited[notebook]) continue;
			visited[notebook] = true;
			if (selected[notebook] == 0 || match(link, selected, visited, selected[notebook])) {
				selected[notebook] = nowStNum;
				return true;
			}
		}
		return false;
	}
}