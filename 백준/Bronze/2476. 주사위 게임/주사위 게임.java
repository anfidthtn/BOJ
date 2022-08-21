import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int[] dices = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(dices);
			if (dices[0] == dices[1] || dices[1] == dices[2]) {
				if (dices[0] == dices[1] && dices[1] == dices[2]) {
					max = Math.max(10000 + dices[0] * 1000, max);
				}
				else if (dices[0] == dices[1]) {
					max = Math.max(1000 + dices[0] * 100, max);					
				}
				else {
					max = Math.max(1000 + dices[2] * 100, max);					
				}
			}
			else {
				max = Math.max(dices[2] * 100, max);
			}
		}
		System.out.println(max);
	}
}