import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		int[] maxLen = new int[n + 1];
		int[] minValue = new int[n + 1];
		Arrays.fill(minValue, Integer.MAX_VALUE);
		int[] resultIdx = new int[n + 1];

		int nowMaxLen = 0;
		for (int i = 1; i <= n; i++) {
			int insertIdx = 1 + getBinarySearchLowerBoundIdx(nums, minValue, nums[i], 0, nowMaxLen);
			nowMaxLen = Math.max(nowMaxLen, insertIdx);
			minValue[insertIdx] = Math.min(minValue[insertIdx], nums[i]);
			maxLen[i] = insertIdx;
		}
		
		int targetLen = nowMaxLen;
		for (int i = n; i >= 1; i--) {
			if (maxLen[i] == targetLen) {
				resultIdx[targetLen--] = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(nowMaxLen).append('\n');
		for(int i = 1; i <= nowMaxLen; i++) {
			sb.append(nums[resultIdx[i]]).append(' ');
		}
		System.out.print(sb);

	}

	public static int getBinarySearchLowerBoundIdx(int[] nums, int[] minValues, int target, int left, int right) {
		if (right <= left) {
			return right;
		}
		int mid = (left + right + 1) >> 1;
		if (minValues[mid] < target) {
			return getBinarySearchLowerBoundIdx(nums, minValues, target, mid, right);
		} else {
			return getBinarySearchLowerBoundIdx(nums, minValues, target, left, mid - 1);
		}
	}
}