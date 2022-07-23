import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> results = new ArrayList<>();
		int G = Integer.parseInt(br.readLine());
		int max = 1;
		for (int i = 2; i * i - max * max <= G; i++) {
			max++;
		}

		int left = 1;
		int right = 2;
		while (left <= max && right <= max) {
			if (left >= right) {
				right++;
			}
			int temp = right * right - left * left;
			if (temp == G) {
				results.add(right);
				right++;
			}
			else if (temp < G) {
				right++;
			}
			else {
				left++;
			}
		}
		if (results.size() == 0) {
			System.out.println(-1);
		}
		else {
			for(int result : results) {
				System.out.println(result);
			}
		}
	}
}