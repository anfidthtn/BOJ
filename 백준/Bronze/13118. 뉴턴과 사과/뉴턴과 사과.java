import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] poss = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] apple = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int answer = 0;
		for(int i = 1; i <= poss.length; i++) {
			if (poss[i - 1] == apple[0]) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}
}