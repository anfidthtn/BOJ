import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int count = 0;
		// 각 높이별 현재 화살 개수
		int[] arrows = new int[1_000_001];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (arrows[num] > 0) {
				// 쏴야할 높이의 화살이 있다면 해당 높이의 1발을 뺀다.
				arrows[num]--;
			} else {
				// 쏴야할 높이의 화살이 없다면 새로 쏜다.
				count++;
			}
			// (기존 것이 맞거나 새로 쏘고 맞아서) 높이가 감소한 화살을 추가한다.
			arrows[num - 1]++;
		}
		System.out.println(count);
	}
}