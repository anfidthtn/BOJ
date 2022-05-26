import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	static List<Integer> fib;
	static int[] mex;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		mex = new int[3_000_001];
		fib = new ArrayList<>();
		fib.add(1);
		fib.add(2);
		while (fib.get(fib.size() - 1) <= 3_000_000) {
			fib.add(fib.get(fib.size() - 2) + fib.get(fib.size() - 1));
		}
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(nums);

		int result = 0;
		int mexIdx = 0;
		for (int i = 0; i < N; i++) {
			while (mexIdx <= nums[i]) {
				boolean[] checks = new boolean[20];
				for (int fibNum : fib) {
					if (mexIdx - fibNum < 0) {
						break;
					}
					checks[mex[mexIdx - fibNum]] = true;
				}
				for(int cIdx = 0; cIdx < checks.length; cIdx++) {
					if (!checks[cIdx]) {
						mex[mexIdx] = cIdx;
						break;
					}
				}
				mexIdx++;
			}
			result ^= mex[nums[i]];
		}
		if (result > 0) {
			System.out.println("koosaga");
		}
		else {
			System.out.println("cubelover");
		}
	}
}