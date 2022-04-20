import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 몇 팀이 동아리에 가입하는지 카운팅하는 변수
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M1 = Integer.parseInt(st.nextToken());
			int M2 = Integer.parseInt(st.nextToken());
			int M3 = Integer.parseInt(st.nextToken());
			if (M1 + M2 + M3 < S || M1 < M || M2 < M || M3 < M) {
				// 합계를 만족하지 못하거나, 개인 최소 능력치가 모자랄 경우 넘어간다
				continue;
			}
			// 모든 조건을 만족하면 정보를 기록한다
			count++;
			sb.append(M1).append(" ").append(M2).append(" ").append(M3).append(" ");
		}
		// 최종적으로 정보를 출력해준다.
		System.out.println(count);
		System.out.print(sb.toString());
	}

}