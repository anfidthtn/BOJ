import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int count = 0;
		int V = Integer.parseInt(br.readLine());
		for(int num : nums) {
			if (num == V) {
				count++;
			}
		}
		System.out.println(count);
	}
}