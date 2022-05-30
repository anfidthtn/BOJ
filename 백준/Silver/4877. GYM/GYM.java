import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] infos = new int[10][10];
		double[][] prob = new double[10][10];
		prob[0][0] = 1.0;
		int[] totals = new int[10];
		int line = 0;
		while (st.hasMoreTokens()) {
			infos[0][line] = Integer.parseInt(st.nextToken());
			totals[0] += infos[0][line];
			line++;
		}
		for (int i = 1; i < line; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < line; j++) {
				infos[i][j] = Integer.parseInt(st.nextToken());
				totals[i] += infos[i][j];
			}
		}
		for (int step = 1; step < 10; step++) {
			for (int i = 0; i < line; i++) {
				for (int j = 0; j < line; j++) {
					prob[step][i] += prob[step - 1][j] * infos[j][i] / totals[j];
				}
			}
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < line; j++) {
				System.out.print(prob[i][j] + " ");
			}
			System.out.println();
		}
	}
}