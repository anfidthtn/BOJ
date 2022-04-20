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
		
		int[] house = new int[M + 1];
		List<List<Integer>> want = new ArrayList<>();
		want.add(null);
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			List<Integer> temp = new LinkedList<>();
			while(st.hasMoreTokens()) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			want.add(temp);
		}
		int count = 0;
		for(int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[M + 1];
			if (check(count, M, want, house, visited, i)) {
				count++;
			}
		}
		System.out.println(count);
	}
	public static boolean check(int N, int M, List<List<Integer>> want, int[] house, boolean[] visited, int cowNum) {
		for(int houseNum : want.get(cowNum)) {
			if (visited[houseNum])
				continue;
			visited[houseNum] = true;
			if (house[houseNum] == 0 || check(N, M, want, house, visited, house[houseNum])) {
				house[houseNum] = cowNum;
				return true;
			}
		}
		return false;
	}
}