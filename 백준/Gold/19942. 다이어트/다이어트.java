import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int minCost;
	static int N;
	static int[][] datas;
	static int[] limits;
	static boolean[] selected;
	static boolean[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		limits = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		datas = new int[N][];
		for (int i = 0; i < N; i++) {
			datas[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		selected = new boolean[N];
		answer = null;
		minCost = 1 << 30;
		check(0, new int[5]);

		if (answer == null) {
			System.out.println(-1);
		} else {
			System.out.println(minCost);
			for(int i = 0; i < N; i++) {
				if(answer[i]) {
					System.out.print((i + 1) + " ");
				}
			}
		}
	}

	public static void check(int idx, int[] data) {
		if (data[4] < minCost) {
			boolean ok = true;
			for (int i = 0; i < 4; i++) {
				if (limits[i] > data[i]) {
					ok = false;
					break;
				}
			}
			if (ok) {
				minCost = data[4];
				answer = Arrays.copyOf(selected, N);
			}
		} else {
			return;
		}
		if (idx == N) {
			return;
		}
		selected[idx] = true;
		for (int i = 0; i < 5; i++) {
			data[i] += datas[idx][i];
		}
		check(idx + 1, data);
		selected[idx] = false;
		for (int i = 0; i < 5; i++) {
			data[i] -= datas[idx][i];
		}
		check(idx + 1, data);
	}
}