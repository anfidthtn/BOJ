import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] nums = new int[N + 1][M + 1];
		int maxArea = 0;
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				nums[i][j] = str.charAt(j - 1) - '0';
				if (nums[i][j] == 0) {
					maxArea = 1;
				}
				nums[i][j] += nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1];
			}
		}
		if (maxArea == 0) {
			System.out.println(maxArea);
			return;
		}

		for (int drRow = N; drRow >= 1; drRow--) {
			for (int drCol = M; drCol >= 1; drCol--) {
				for (int ulRow = drRow; ulRow >= 1; ulRow--) {
					// maxArea > (drRow - ulRow + 1) * (drCol - ulCol + 1) 인 것만 보면 된다.
					int ulCol = 0;
					int left = 1;
					int right = drCol;
					while(left <= right) {
						int mid = (left + right) >> 1;
						if (maxArea < (drRow - ulRow + 1) * (drCol - mid + 1)) {
							ulCol = mid;
							left = mid + 1;
						}
						else {
							right = mid - 1;
						}
					}
					for (; ulCol >= 1; ulCol--) {
						if (nums[drRow][drCol] - nums[drRow][ulCol - 1] - nums[ulRow - 1][drCol] + nums[ulRow - 1][ulCol - 1] == 0) {
							maxArea = (drRow - ulRow + 1) * (drCol - ulCol + 1);
						}
						else {
							break;
						}
					}
				}
			}
		}
		System.out.println(maxArea);
	}
}