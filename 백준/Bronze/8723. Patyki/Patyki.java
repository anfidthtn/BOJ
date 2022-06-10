import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] lines = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(lines);
		if (lines[0] == lines[1] && lines[1] == lines[2]) {
			System.out.println(2);
		}
		else if (lines[0] * lines[0] + lines[1] * lines[1] == lines[2] * lines[2]) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}
}