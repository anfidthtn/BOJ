import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
//			sb.append("#").append(tNum).append(" ");
			// 입력 정보를 StringTokenizer로 받는다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 문제에서 N범위 안의 입력으로 들어온다고 보장했으니, N값은 필요없다.
			st.nextToken();
			int X = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			for(int i = 0; i < K; i++) {
				// K번의 이동에 대한 정보를 받는다.
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				// X(현재 위치) 가 이동 대상이면 또 다른 이동 위치와 이동한다.
				if (X == A) X = B;
				else if (X == B) X = A;
			}
			// 최종 위치를 출력한다.
			sb.append(X).append("\n");
		}
		System.out.print(sb.toString());
	}

}