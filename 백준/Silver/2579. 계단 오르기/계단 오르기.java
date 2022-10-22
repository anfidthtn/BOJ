import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] maxScores;
	static int[] stairScore;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stairScore = new int[N + 10];
		maxScores = new int[N + 10][2];
		for(int i = N; i >= 1; i--) {
			stairScore[i] = Integer.parseInt(br.readLine());
		}
		for(int i = N; i >= 1; i--) {
			maxScores[i][0] = Math.max(maxScores[i][0], maxScores[i + 2][0] + stairScore[i]);
			maxScores[i][0] = Math.max(maxScores[i][0], maxScores[i + 2][1] + stairScore[i]);
			maxScores[i][1] = maxScores[i + 1][0] + stairScore[i];
		}
		System.out.print(Math.max(maxScores[1][0], maxScores[1][1]));
	}
}