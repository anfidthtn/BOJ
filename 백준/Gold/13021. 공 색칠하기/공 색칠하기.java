import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] Ms = new int[N + 1];
		Set<Integer> set = new HashSet<>();
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			for(int idx = L; idx <= R; idx++) {
				Ms[idx] = i;
			}
		}
		for(int i = 1; i <= N; i++) {
			if (Ms[i] > 0) {
				set.add(Ms[i]);
			}
		}
		System.out.println(pow(2, set.size()));
	}
	public static long pow(long num, int power) {
		long result = 1;
		while (power > 0) {
			if ((power & 1) == 1) {
				result *= num;
			}
			num *= num;
			power >>= 1;
		}
		return result;
	}
}