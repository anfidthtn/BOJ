import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int maximum = 1;
		int up = 0;
		int down = 0;
		
		// prev : 만약 증감상태가 바뀌면 없애야할 같은 수 구간
		// lastEquals : 현재 수가 연속으로 얼마나 같았는지.
		// 같은 수가 나올 때마다 둘 다 더해준다.
		// 증감이 바뀌면 lastEquals가 prev가 된다. 
		int prevEquals = 1;
		int lastEquals = 1;
		int before = Integer.parseInt(st.nextToken());
		while (st.hasMoreTokens()) {
			int now = Integer.parseInt(st.nextToken());
			if (before == now) {
				// 같은 경우엔 up, down유지하고 같다는 것만 늘림
				prevEquals++;
				lastEquals++;
			}
			else {
				// 같지 않은 경우
				if (before < now) {
					// 증가를 한 경우
					if (down > 0) {
						// 같거나 감소하던 중인 경우, 감소수치를 없애고 증가로 돌림
						down = 0;
						prevEquals = lastEquals;
					}
					up++;
				}
				else {
					// 감소를 한 경우
					if (up > 0) {
						up = 0;
						prevEquals = lastEquals;
					}
					down++;
				}
				lastEquals = 1;
			}
			maximum = Math.max(maximum, prevEquals + up);
			maximum = Math.max(maximum, prevEquals + down);
			// 새로운 수로 넘겨준다.
			before = now;
		}
		System.out.print(maximum);
	}
}