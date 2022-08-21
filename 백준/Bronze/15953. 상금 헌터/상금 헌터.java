import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ac = 0;
			int a = Integer.parseInt(st.nextToken());
			if (a > 0) {
				if (a <= 21) {
					ac += 100000;
				}
				if (a <= 15) {
					ac += 200000;
				}
				if (a <= 10) {
					ac += 200000;
				}
				if (a <= 6) {
					ac += 1500000;
				}
				if (a <= 3) {
					ac += 1000000;
				}
				if (a <= 1) {
					ac += 2000000;
				}
			}
			int b = Integer.parseInt(st.nextToken());
			int bc = 0;
			if (b > 0) {
				if (b <= 31) {
					bc += 320000;
				}
				if (b <= 15) {
					bc += 320000;
				}
				if (b <= 7) {
					bc += 640000;
				}
				if (b <= 3) {
					bc += 1280000;
				}
				if (b <= 1) {
					bc += 2560000;
				}
			}
			sb.append(ac + bc).append("\n");
		}
		System.out.print(sb.toString());
	}
}