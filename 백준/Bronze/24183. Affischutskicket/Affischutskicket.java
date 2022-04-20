import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] price = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.print(
				2 * 229 * 324 * price[0] / 1000000.0 +
				2 * 297 * 420 * price[1] / 1000000.0 +
				210 * 297 * price[2] / 1000000.0
				);
	}
}