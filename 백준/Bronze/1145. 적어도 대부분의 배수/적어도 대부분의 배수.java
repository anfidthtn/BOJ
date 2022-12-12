import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int X = 1;
		while(true) {
			int count = 0;
			for(int num : nums) {
				if (X % num == 0) {
					count++;
				}
			}
			if (count >= 3) {
				break;
			}
			X++;
		}
		System.out.print(X);
	}
}