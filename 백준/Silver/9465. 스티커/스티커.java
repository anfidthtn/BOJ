import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 바로 앞 정보만 저장하면 돼서 인덱스 2개만 씀
		int before = 0;
		int now = 1;
		// 즉석처리용으로 토크나이저 2개 동시사용
		StringTokenizer[] sts = new StringTokenizer[2];
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			sts[0] = new StringTokenizer(br.readLine());
			sts[1] = new StringTokenizer(br.readLine());
			// [전에꺼냐 지금꺼냐] [0 : 첫 번째 스티커줄이냐, 1 : 두 번째 스티커 줄이냐, 2 : 아무 것도 선택 안했냐
			int[][] res = new int[2][3];
			for (int i = 0; i < N; i++, before ^= 1, now ^= 1) {
				// 해당 줄을 선택안하는 건, 이전 줄 선택 상태 모든 경우 중 최고치를 고르는 것
				res[now][2] = Math.max(Math.max(res[before][0], res[before][1]), res[before][2]);
				// 첫 번째 줄을 선택하려면 이전 줄 선택을 안 한 케이스나 2번째 줄을 선택한 케이스에다가 이번 줄의 1번째를 선택한 것.
				res[now][0] = Math.max(res[before][2], res[before][1]) + Integer.parseInt(sts[0].nextToken());
				// 두 번째 줄을 선택하려면 이전 줄 선택을 안 한 케이스나 1번째 줄을 선택한 케이스에다가 이번 줄의 2번째를 선택한 것.
				res[now][1] = Math.max(res[before][2], res[before][0]) + Integer.parseInt(sts[1].nextToken());
			}
			// 마지막에 남은 것 (for문 끝나면서 before로 결과 넘어감) 3가지 경우 중 최선을 고른다.
			sb.append(Math.max(Math.max(res[before][0], res[before][1]), res[before][2])).append("\n");
		}
		System.out.print(sb.toString());
	}

}