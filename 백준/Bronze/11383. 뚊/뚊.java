import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] A = new char[N][];
		char[][] B = new char[N][];
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			B[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j * 2]) {
					printtttt(false);
					return;
				}
				if (A[i][j] != B[i][j * 2 + 1]) {
					printtttt(false);
					return;
				}
			}
		}
		printtttt(true);
	}

	public static void printtttt(Boolean a) {
		if (a) {
			System.out.print("Eyfa");
		}
		else {
			System.out.print("Not Eyfa");
		}
	}
}