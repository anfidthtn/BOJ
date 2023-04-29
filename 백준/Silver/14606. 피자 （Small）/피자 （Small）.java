import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Main {
	static Map<Integer, Long> ansMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ansMap = new TreeMap<>();
		long ans = 0;
		Queue<Integer> queue = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		System.out.print(getAns(N));
	}

	public static long getAns(int x) {
		if (x == 1) {
			return 0;
		}
		if (ansMap.containsKey(x)) {
			return ansMap.get(x);
		}
		long temp = 0;
		if ((x & 1) == 1) {
			temp = getAns(x >> 1) + getAns((x >> 1) + 1) + (long) (x >> 1) * ((x >> 1) + 1);
		} else {
			temp = getAns(x >> 1) + getAns(x >> 1) + (long) (x >> 1) * (x >> 1);
		}
		ansMap.put(x, temp);
		return ansMap.get(x);
	}
}