import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] Di = new int[N];
		
		int count = 0;
		while (count < N) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				Di[count++] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		boolean[] nums = new boolean[1_000_000];
		for(int digit = 1; digit <= 6; digit++) {
			for(int i = 0; i < N - digit + 1; i++) {
				int num = 0;
				for(int j = 0; j < digit; j++) {
					num *= 10;
					num += Di[i + j];
				}
				nums[num] = true;
			}
		}
		for(int i = 0; i < 1_000_000; i++) {
			if(!nums[i]) {
				System.out.println(i);
				return;
			}
		}
	}
}