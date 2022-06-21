import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int color = 1;
		int[] colors = new int[N + 1];
		colors[1] = 1;
		for(int i = 2; i <= N; i++) {
			if (colors[i] == 0) {
				color++;
				for(int j = i; j <= N; j += i) {
					if (colors[j] == 0) {
						colors[j] = color;
					}
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(color));
		bw.write('\n');
		for(int i = 1; i <= N; i++) {
			bw.write(String.valueOf(colors[i]));
			bw.write(' ');
		}
		bw.flush();
	}
}