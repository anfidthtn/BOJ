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

		int[] minValues = new int[n + 1];
		Arrays.fill(minValues, Integer.MAX_VALUE);
		
		int nowMaxLen = 0;
		for(int i = 1; i <= n; i++) {
			int resultIdx = 1 + getLowerBound(minValues, nums[i], 0, nowMaxLen);
			nowMaxLen = Math.max(nowMaxLen, resultIdx);
			minValues[resultIdx] = Math.min(minValues[resultIdx], nums[i]);
		}
		System.out.print(n - nowMaxLen);
	}
	public static int getLowerBound(int[] minValues, int target, int left, int right) {
		if (right <= left) return right;
		int mid = (left + right + 1) >> 1;
		if (target <= minValues[mid]) {
			return getLowerBound(minValues, target, left, mid - 1);
		}
		else {
			return getLowerBound(minValues, target, mid, right);
		}
	}
}