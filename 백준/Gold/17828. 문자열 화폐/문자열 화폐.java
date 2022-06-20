import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		if (X < N || N * 26 < X) {
			System.out.println('!');
			return;
		}
		X -= N;
		int Z = X / 25;
		X -= Z * 25;
		if (X == 0) {
			for(int i = 1; i <= N - Z; i++) {
				bw.write('A');
			}			
		}
		else {
			for(int i = 1; i < N - Z; i++) {
				bw.write('A');
			}
			bw.write('A' + X);
		}
		for(int i = N - Z + 1; i <= N; i++) {
			bw.write('Z');
		}
		bw.flush();
	}
}