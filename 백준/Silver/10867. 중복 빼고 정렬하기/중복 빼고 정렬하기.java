import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] nums = new boolean[2001];
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			nums[Integer.parseInt(st.nextToken()) + 1000] = true;
		}
		for(int i = 0; i <= 2000; i++) {
			if (nums[i]) {
				System.out.print((i - 1000) + " ");
			}
		}
	}
}