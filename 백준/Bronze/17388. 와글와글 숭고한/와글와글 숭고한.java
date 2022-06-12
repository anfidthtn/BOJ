import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int sum = 0;
		int min = 100;
		for (int num : nums) {
			sum += num;
			min = Math.min(min, num);
		}
		if (sum >= 100) {
			System.out.println("OK");
			return;
		}
		for(int i = 0; i < 3; i++) {
			if (nums[i] == min) {
				switch(i) {
				case 0:
					System.out.println("Soongsil");
					return;
				case 1:
					System.out.println("Korea");
					return;
				case 2:
					System.out.println("Hanyang");
					return;
				}
			}
		}
		
	}
}