import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] nums = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int T = Integer.parseInt(br.readLine());
		int ans = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < R - 1; i++) {
			for (int j = 1; j < C - 1; j++) {
				list.clear();
				for (int a = -1; a <= 1; a++) {
					for (int b = -1; b <= 1; b++) {
						list.add(nums[i + a][j + b]);
					}
				}
				list.sort(Integer::compareTo);
				if (list.get(4) >= T) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}