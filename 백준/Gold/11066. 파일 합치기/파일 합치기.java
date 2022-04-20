import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tNum = 0; tNum < t; tNum++) {
			int k = Integer.parseInt(br.readLine());
			int[] Ks = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (k == 1) {
				System.out.print(Ks[0]);
				continue;
			}
			// 인덱스 i부터 인덱스 j까지 합할 때의 비용.
			// 즉, 1행 3열이면 2번째(인덱스1) 부터 4번째(인덱스 3)까지 합하는 비용이다.
			int[][] cost = new int[k][k];
			
			// cost처럼 파일 크기
			int[][] fileVolume = new int[k][k];
			for (int i = 0; i < k; i++) {
				fileVolume[i][i] = Ks[i];
			}
			
			for (int size = 2; size <= k; size++) {
				// 합친 것의 개수를 2부터 k개까지 증가시키면서 값의 최소값을 갱신해나간다.
				for(int i = 0; i < k - size + 1; i++) {
					// k개를 연속하는 size개로 묶으면 k - size + 1 개가 나온다.
					// i번째부터 size개를 합치려면 i부터 i + size - 1까지의 합계를 구하면 된다.
					// 일단 최대값을 넣어둔다.
					fileVolume[i][i + size - 1] = Integer.MAX_VALUE;
					cost[i][i + size - 1] = Integer.MAX_VALUE;
					for (int d = 1; d < size; d++) {
						fileVolume[i][i + size - 1] = Math.min(fileVolume[i][i + size - 1], fileVolume[i][i + d - 1] + fileVolume[i + d][i + size - 1]);
						cost[i][i + size - 1] = Math.min(cost[i][i + size - 1], fileVolume[i][i + d - 1] + fileVolume[i + d][i + size - 1] + cost[i][i + d - 1] + cost[i + d][i + size - 1]);
					}
				}
			}
			System.out.println(cost[0][k - 1]);
		}
	}
}