import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[][] s;
	
	static int minimum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			minimum = Integer.MAX_VALUE;
			// N과 시너지 정보를 입력받는다.
			N = Integer.parseInt(br.readLine());
			s = new int[N][];
			for(int i = 0; i < N; i++) {
				s[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			comb(0, 0, 0);
			sb.append(minimum).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static void comb(int isSelected, int depth, int count) {
		if (depth == N && count < N / 2) return;
		if (count == N / 2) {
			int[] firstFood = new int[N / 2]; 
			int[] secondFood = new int[N / 2];
			int fIdx = 0;
			int sIdx = 0;
			for(int i = 0; i < N; i++) {
				if ((isSelected & (1 << i)) != 0) {
					firstFood[fIdx++] = i;
				}
				else {
					secondFood[sIdx++] = i;
				}
			}
			int fSyn = 0;
			for(int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					fSyn += s[firstFood[i]][firstFood[j]] + s[firstFood[j]][firstFood[i]];
				}
			}
			int sSyn = 0;
			for(int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					sSyn += s[secondFood[i]][secondFood[j]] + s[secondFood[j]][secondFood[i]];
				}
			}
			minimum = Math.min(minimum, Math.abs(fSyn - sSyn));
			return;
		}
		
		// 해당 인덱스 선택 상태로 돌림
		comb(isSelected | (1 << depth), depth + 1, count + 1);
		// 미선택상태도 돌린다.
		comb(isSelected, depth + 1, count);
	}
}