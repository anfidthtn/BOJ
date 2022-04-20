import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// N은 필요없는거같음.
		st.nextToken();
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// card를 배열로 받고
		int[] cards = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 정렬한다.
		Arrays.sort(cards);
		// card에 대해 사용했는지 여부를 확인한다.
		boolean[] isUsed = new boolean[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int idx = binarySearch(cards, Integer.parseInt(st.nextToken()), 0, M - 1);
			while(isUsed[idx]) idx++;
			isUsed[idx] = true;
			sb.append(cards[idx]).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static int binarySearch(int[] cards, int target, int left, int right) {
		if (right <= left) {
			return left;
		}
		int mid = (left + right) >> 1;
		if (target < cards[mid]) {
			return binarySearch(cards, target, left, mid);
		}
		else {
			return binarySearch(cards, target, mid + 1, right);
		}
	}
}