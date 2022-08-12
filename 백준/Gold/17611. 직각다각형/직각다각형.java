import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		// 마지막에 첫 점으로 돌아오게 하기위해 N + 1개 사용
		int[][] inputs = new int[N + 1][];
		// x[0] y[1] 좌표별 해당 좌표에서 선분 몇 개 겹치는지 (단, 종료지점에는 빼줌)
		int[][] line = new int[2][2010101];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 문제 조건 음수땜에 양수로 만들어줌
			inputs[i] = new int[] {Integer.parseInt(st.nextToken()) * 2 + 1000001, Integer.parseInt(st.nextToken()) * 2 + 1000001};
		}
		// 마지막에 첫 점으로 돌아오게 함
		inputs[N] = inputs[0];
		int min = 0;
		int max = 0;
		int target = 0;
		for(int i = 1; i <= N; i++) {
			if (inputs[i - 1][0] != inputs[i][0]) {
				target = 0;
			}
			else {
				target = 1;
			}
			// 입력값 보면서 선분 시작점에 더하고 끝점에 뺌
			min = Math.min(inputs[i - 1][target], inputs[i][target]);
			max = Math.max(inputs[i - 1][target], inputs[i][target]);
			line[target][min]++;
			line[target][max]--;
			// 좌표축 바꿈
			target ^= 1;
		}
		// 누적시킴
		for(int i = 1; i <= 2000100; i++) {
			line[0][i] += line[0][i - 1];
			line[1][i] += line[1][i - 1];
		}
		// 좌표 다 돌면서 최대값 구함
		int answer = 0;
		for(int i = 2; i <= 2000100; i+= 2) {
			if (answer < line[0][i]) {
				answer = line[0][i];
			}
			if (answer < line[1][i]) {
				answer = line[1][i];
			}
		}
		System.out.println(answer);
	}
}