import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = new int[3];
		int sum = 0;
		for(int i = 0; i < 3; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			sum += nums[i];
		}
		if (nums[0] == 60 && nums[1] == 60 && nums[2] == 60) {
			System.out.println("Equilateral");
			return;
		}
		if(sum != 180) {
			System.out.println("Error");
			return;
		}
		if (nums[0] == nums[1] || nums[1] == nums[2] || nums[2] == nums[0]) {
			System.out.println("Isosceles");
			return;			
		}
		System.out.println("Scalene");
	}
}