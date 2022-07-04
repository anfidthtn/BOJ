import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int answer = -1;
		for(int sheep = 1; sheep < N; sheep++) {
			if (sheep * A + (N - sheep) * B == W) {
				if (answer >= 0) {
					System.out.println(-1);
					return;
				}
				answer = sheep;
			}
		}
		if (answer == -1) {
			System.out.println(-1);
			return;
		}
		else {
			System.out.println(answer + " " + (N - answer));
		}
	}
}