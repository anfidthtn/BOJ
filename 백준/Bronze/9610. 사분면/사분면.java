import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[5];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x == 0 || y == 0) {
				count[0]++;
			}
			else {
				if (x > 0) {
					if (y > 0) {
						count[1]++;
					}
					else {
						count[4]++;
					}
				}
				else {
					if(y > 0) {
						count[2]++;
					}
					else {
						count[3]++;
					}
				}
			}
		}
		for(int i = 1; i <= 4; i++) {
			System.out.println("Q" + i + ": " + count[i]);
		}
		System.out.println("AXIS: " + count[0]);
	}
}