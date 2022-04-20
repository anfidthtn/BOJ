import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] sideLen = new int[N + 1];
		StringTokenizer st;
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			sideLen[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		sideLen[N - 1] = Integer.parseInt(st.nextToken());
		sideLen[N] = Integer.parseInt(st.nextToken());
		
		// 행 번째 행렬부터 열 번째 행렬까지 곱할 때 최소비용
		// ex) minCost[4][7] : 행렬4 * 행렬5 * 행렬6 * 행렬7 을 할 때 필요한 최소비용
		// 즉, 최종적으로 minCost[1][N]을 구해내면 그 값이 문제에서 원하는 최소비용이다.
		int[][] minCost = new int[N + 1][N + 1];
		
		// 2 ~ N개까지 합쳐가며 최소값 구함
		for(int size = 2; size <= N; size++) {
			// 시작점 i는 (1) ~ (N - size + 1)
			// 도착점 j는 (i + size - 1)
			for(int i = 1; i <= N - size + 1; i++) {
				minCost[i][i + size - 1] = Integer.MAX_VALUE;
				/**
				 * size 3짜리 1 ~ 3을 합친다고 예를 들면
				 * 1 * 2~3 / 1~2 * 3의 두 경우가 있다.
				 * size 5짜리 4 ~ 8을 합친다고 예를 들면
				 * 4 * 5~8 / 4~5 * 6~8 / 4~6 * 7~8 / 4~7 * 8의 네 경우가 있다.
				 * 이런식으로 경우마다 비용을 구해 최소값을 산정한다.
				 * 근데 변수 이름 뭘로하지.. 딱히 생각나는 게 없다.
				 */
				for(int name = 1; name < size; name++) {
					/**
					 * 앞의 행렬곱을 만드는 데 필요한 비용
					 * +
					 * 뒤의 행렬곱을 만드는데 필요한 비용
					 * +
					 * 그것들을 곱해서 전체 행렬곱을 만드는 데 필요한 비용
					 * 이렇게 합해서 원래의 최소값과 비교한다.
					 * 4~5 * 6~8의 경우
					 * 4~5 최소비용, 6~8 최소비용, 4~5 * 6~8을 하는 비용을 합한다.
					 */
					minCost[i][i + size - 1] = Math.min(minCost[i][i + size - 1],
							minCost[i][i + name - 1] +
							minCost[i + name][i + size - 1] +
							sideLen[i - 1] * sideLen[i + name - 1] * sideLen[i + size - 1]
							);
				}
			}
		}
		System.out.print(minCost[1][N]);
	}
}