import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		long cost = 0;
		
		/**
		 * 뒤의 주석들어서 1개씩 묶어 산다, 2개씩 묶어 산다 같은 용어는 편의상 그렇게 쓴 것이고
		 * 정확히는 3개 묶음의 7 5 3 가격 중 각각 7원에 산 개수, 5원에 산 개수, 3원에 산 개수를 뜻하는 것이다.
		 * 라고 문제를 이해했는데 예제 돌리고나서 결과 달라서 다시보니
		 * 묶음 가격이 3원 5원 7원으로 3 2 2원이었네..
		 * 다시 짜야지
		 */
		
		// 각 지점에서 1개씩 묶어 산 개수 (여기에 3원을 곱하면 구매가격이다.)
		int[] set1 = new int[N];
		// 각 지점에서 2개씩 묶어 산 개수 (여기에 2원을 곱하면 구매가격이다.)
		int[] set2 = new int[N];
		
		// 첫 자리는 무조건 3원에 사야한다.
		set1[0] = Integer.parseInt(st.nextToken());
		
		// 두 번째 자리는 첫 번째 자리에서 3원에 산 만큼은 2원에 살 수 있고, 나머지는 3원에 사야한다.
		int amount = Integer.parseInt(st.nextToken());
		set2[1] = Math.min(set1[0], amount);
		amount -= set2[1];
		set1[1] = amount;
		
		// i >= 2(3번째 이상)번째 자리부터는 i - 2번째에서 3원, i - 1번째에서 2원 중 적게 산 만큼 2원에 살 수 있고
		// i - 1번째에서 3원에 산 만큼 2원에 살 수 있다.
		// 나머지는 3원에 사야한다.
		for (int i = 2; i < N; i++) {
			amount = Integer.parseInt(st.nextToken());
			// 2원에 3개짜리 살 수 있는만큼 먼저 삼
			set2[i] = Math.min(Math.min(set1[i - 2], set2[i - 1]), amount);
			amount -= set2[i];
			
			// 2원에 2개짜리 살 수 있는만큼 먼저 삼
			set2[i] += Math.min(set1[i - 1], amount);
			amount -= Math.min(set1[i - 1], amount);
			
			// 3원에 1개짜리 살 수 있는만큼 나머지 삼
			set1[i] = amount;
		}
		
		// 하 씨 long..
		for (int i = 0; i < N; i++) {
			cost += 3 * set1[i];
			cost += 2 * set2[i];
		}
		
		System.out.println(cost);
	}
}