import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] nums;
	static int[] opers;

	static int max;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		opers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		recur(1, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	public static void recur(int idx, int before) {
		if (idx == N) {
			max = Math.max(max, before);
			min = Math.min(min, before);
			return;
		}
		for(int i = 0; i < 4; i++) {
			if (opers[i] > 0) {
				opers[i]--;
				recur(idx + 1, calc(before, nums[idx], i));
				opers[i]++;
			}
		}
	}

	public static int calc(int a, int b, int oper) {
		switch (oper) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			return a < 0 ? -((-a) / b) : a / b;
		}
		return -1;
	}
}