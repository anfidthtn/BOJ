import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	// 2^28이 대충 2억 언저리라서 0 ~ 2억 언저리 범위에 있는 12가지 경우의 수를 미리 구해둔다.
	static int[] targets = { //
			202021, //
			20202021, //
			202002021, //
			202012021, //
			202022021, //
			202032021, //
			202042021, //
			202052021, //
			202062021, //
			202072021, //
			202082021, //
			202092021 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			Map<Integer, Integer> map = new TreeMap<>();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 탐색 편의상 좌우로 패딩을 둔다.
			int[] nums = new int[N + 2];
			// 패딩값은 최저값 아래, 최고값 위로 한다.
			nums[0] = -1 * (1 << 29);
			nums[N + 1] = 1 << 29;
			for (int i = 1; i <= N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			// 입력배열을 정렬한다. i < j 조건만 있고 num[i] < num[j] 같은 조건은 없기 때문에 순서가 바뀌어도 쌍의 수만 제대로
			// 세어주면 된다.
			// 즉, 정렬해도 결과는 같다.
			Arrays.sort(nums);
			long ans = 0;
			// 미리 구한 12개에 대해 각각 몇 개 나오나 보고 ans에 더해준다.
			for (int target : targets) {
				// 좌, 우측 인덱스부터 시작
				int leftIdx = 1, rightIdx = N;
				// target이 모두 홀수이므로 l < r 인 동안 본다.
				while (leftIdx < rightIdx) {
					if (nums[leftIdx] + nums[rightIdx] > target) {
						// l + r > t 이면 r을 줄여야한다.
						rightIdx--;
					} else if (nums[leftIdx] + nums[rightIdx] < target) {
						// l + r < t 이면 r을 늘려야한다.
						leftIdx++;
					} else {
						// l + r == t 이면 연속으로 몇 개인지 세어서 곱해줘야한다.
						int leftStraight = 1;
						int rightStraight = 1;
						// 다음 원소랑 같다면 연속구간을 늘리고 인덱스를 늘린다. (이 때문에 패딩 사용)
						while (nums[leftIdx] == nums[leftIdx + 1]) {
							leftStraight++;
							leftIdx++;
						}
						// 이전 원소랑 같다면 연속구간을 늘리고 인덱스를 줄인다. (이 때문에 패딩 사용)
						while (nums[rightIdx] == nums[rightIdx - 1]) {
							rightStraight++;
							rightIdx--;
						}
						// 결과를 더해준다.
						ans += (long) leftStraight * rightStraight;
						// 인덱스를 넘긴다.
						leftIdx++;
						rightIdx--;
					}
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
}