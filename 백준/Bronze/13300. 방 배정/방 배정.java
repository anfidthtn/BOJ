import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// [성별][학년]으로 저장할 곳(인덱스때문에 7개)
		int[][] count = new int[2][7];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// [성별][학년] 을 받았으니 거기 인원수 +1씩 해준다.
			count[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]++;
		}
		
		int need = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 1; j <= 6; j++) {
				// 성별, 학년별로 소수점 올림연산으로 필요한 방 개수를 구한다.
				need += Math.ceil((double)count[i][j] / K);
			}
		}
		System.out.print(need);
	}
}