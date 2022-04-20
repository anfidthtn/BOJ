import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int[] abc = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (abc[0] == 0)
				return;
			if (abc[0] == abc[1] && abc[1] == abc[2]) {
				System.out.println("Equilateral");
				continue;
			}
			Arrays.sort(abc);
			if (abc[0] + abc[1] <= abc[2]) {
				System.out.println("Invalid");
				continue;
			}
			if (abc[0] == abc[1] || abc[1] == abc[2]) {
				System.out.println("Isosceles");
				continue;
			}
			System.out.println("Scalene");
		}
	}
}