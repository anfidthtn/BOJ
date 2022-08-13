import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<ArrayList<Integer>> books = new ArrayList<>();
		books.add(null);
		for(int i = 1; i <= 10; i++) {
			books.add(new ArrayList<>());
		}
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			books.get(Integer.parseInt(st.nextToken())).add(price);
		}
		Integer[][] sells = new Integer[11][];
		for(int i = 1; i <= 10; i++) {
			books.get(i).sort((a, b) -> b - a);
			books.get(i).add(0, 0);
			sells[i] = books.get(i).toArray(new Integer[books.get(i).size()]);
			for(int j = 1; j < sells[i].length; j++) {
				sells[i][j] += sells[i][j - 1];
			}
			for(int j = 2; j < sells[i].length; j++) {
				sells[i][j] += j * (j - 1);
			}
		}
		int[][] maxSell = new int[11][K + 1];
		for(int i = 0; i <= 10; i++) {
			for(int j = 1; j <= K; j++) {
				maxSell[i][j] = Integer.MIN_VALUE;
			}
		}
		for(int i = 1; i <= 10; i++) {
			for (int j = 0; j <= K; j++) {
				if (maxSell[i - 1][j] < 0) {
					break;
				}
				for (int unit = 0; unit < sells[i].length && j + unit <= K; unit++) {
					maxSell[i][j + unit] = Math.max(maxSell[i][j + unit], maxSell[i - 1][j] + sells[i][unit]);
				}
			}
		}
		System.out.print(maxSell[10][K]);
	}
}