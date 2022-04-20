import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		long cost = 0;
		if (B <= C) {
			// 1개씩 사는 것이 더 유리하거나 같으면 그냥 전체 수량만큼 사면 된다.
			while (st.hasMoreTokens()) cost += Integer.parseInt(st.nextToken());
			System.out.print(cost * B);
			return;
		}
		// 묶어살 수 있다면 묶어 사는 것이 유리한 경우
		
		// 각 지점에서 묶어사지 못한 개수 (여기에 B원을 곱하면 구매가격이다.)
		int[] single = new int[N];
		// 각 지점에서 묶어서 산 개수 (여기에 C원을 곱하면 구매가격이다.)
		int[] multi = new int[N];
		
		// 첫 자리는 무조건 B원에 사야한다.
		single[0] = Integer.parseInt(st.nextToken());
		
		// 두 번째 자리는 첫 번째 자리에서 B원에 산 만큼은 C원에 살 수 있고, 나머지는 B원에 사야한다.
		int amount = Integer.parseInt(st.nextToken());
		multi[1] = Math.min(single[0], amount);
		amount -= multi[1];
		single[1] = amount;
		
		// i >= 2(3번째 이상)번째 자리부터는 i - 2번째에서 B원, i - 1번째에서 C원 중 적게 산 만큼 C원에 살 수 있고(B + 2C)
		// i - 1번째에서 B원에 산 만큼 C원에 살 수 있다(B + C).
		// 나머지는 B원에 사야한다.
		for (int i = 2; i < N; i++) {
			amount = Integer.parseInt(st.nextToken());
			int set3 = Math.min(single[i - 2], multi[i - 1]);
			set3 = Math.min(set3, amount);
			multi[i] += set3;
			amount -= set3;
			int set2 = Math.min(single[i - 1], amount);
			multi[i] += set2;
			amount -= set2;
			single[i] += amount;
		}
		
		for (int i = 0; i < N; i++) {
			cost += (long) single[i] * B;
			cost += (long) multi[i] * C;
		}
		
		System.out.println(cost);
	}
}