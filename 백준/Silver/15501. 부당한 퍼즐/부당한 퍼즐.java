import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int[] origin;
	static int[] target;
	static int idx = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		origin = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		target = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < N; i++) {
			if (target[i] == origin[0]) {
				idx = i;
				break;
			}
		}
		if (forward() || backward()) {
			System.out.println("good puzzle");
		} else {
			System.out.println("bad puzzle");
		}
	}

	public static boolean forward() {
		for (int i = 1; i < N; i++) {
			if (origin[i] != target[(i + idx) % N]) {
				return false;
			}
		}
		return true;
	}

	public static boolean backward() {
		for (int i = 1; i < N; i++) {
			if (origin[i] != target[(idx - i + N) % N]) {
				return false;
			}
		}
		return true;
	}
}