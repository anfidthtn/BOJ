import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	// 귀찮으니 다 static선언
	public static int n;
	public static int[][] info;
	public static int mul = 1;
	public static int sum = 0;
	public static int minSub = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		info = new int[n][];
		for(int i = 0; i < n; i++) {
			info[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		comb(0, 0);
		
		System.out.print(minSub);
	}
	public static void comb(int count, int depth) {
		if (depth == n) {
			if (count == 0) return;
			minSub = Math.min(minSub, Math.abs(mul - sum));
			return;
		}
		// 이번 재료를 선택한걸로 치려고
		// 곱해주고 더해주고
		mul *= info[depth][0];
		sum += info[depth][1];
		// 선택한 상태로 진행
		comb(count + 1, depth + 1);
		// 돌려놓고
		mul /= info[depth][0];
		sum -= info[depth][1];
		// 선택하지 않은 상태로 진행
		comb(count, depth + 1);
	}
}