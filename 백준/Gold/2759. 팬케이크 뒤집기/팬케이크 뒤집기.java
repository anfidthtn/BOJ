import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] cakes = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				cakes[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Integer> Ks = new ArrayList<>();
			for(int size = N; size >= 1; size--) {
				for(int idx = 1; idx <= size; idx++) {
					if (cakes[idx] == size) {
						if (idx == size) {
							break;
						}
						if (idx != 1) {
							Ks.add(idx);
							flip(cakes, idx);
						}
						if (size != 1) {
							Ks.add(size);
							flip(cakes, size);
						}
						break;
					}
				}
			}
			sb.append(Ks.size()).append(" ");
			for(int K : Ks) {
				sb.append(K).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	public static void flip(int[] cakes, int point) {
		for(int i = 1; i <= point / 2; i++) {
			int temp = cakes[i];
			cakes[i] = cakes[point - i + 1];
			cakes[point - i + 1] = temp;
		}
	}
}