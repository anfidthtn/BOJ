import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] dish = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		Map<Integer, Integer> line = new HashMap<>();
		// 쿠폰에 해당하는 거 하나는 먹을 수 있다.
		int kind = 1;
		for(int i = 1; i <= k; i++) {
			// 쿠폰번호 제외하고 전부 넣는다.
			if (dish[i] != c) {
				kind += insertDish(line, dish[i]);
			}
		}
		int maxKind = kind;
		for(int i = k + 1; i <= N; i++) {
			if (dish[i] != c) {
				kind += insertDish(line, dish[i]);
			}
			if (dish[i - k] != c) {
				kind += deleteDish(line, dish[i - k]);
			}
			if (kind > maxKind) {
				maxKind = kind;
			}
		}
		for(int i = 1; i <= k; i++) {
			if (dish[i] != c) {
				kind += insertDish(line, dish[i]);
			}
			if (dish[N - k + i] != c) {
				kind += deleteDish(line, dish[N - k + i]);
			}
			if (kind > maxKind) {
				maxKind = kind;
			}
		}
		System.out.println(maxKind);
	}
	public static int insertDish(Map<Integer, Integer> line, int dish) {
		if (line.containsKey(dish)) {
			line.put(dish, line.get(dish) + 1);
			return 0;
		}
		else {
			line.put(dish, 1);
			return 1;
		}
	}
	public static int deleteDish(Map<Integer, Integer> line, int dish) {
		if (line.get(dish) > 1) {
			line.put(dish, line.get(dish) - 1);
			return 0;
		}
		else {
			line.remove((Integer) dish);
			return -1;
		}
	}
}