import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] commonCount = new int[1001];
		for(int num = 2; num <= 1000; num++) {
			for(int i = 2; i <= num; i++) {
				if (getGCD(num, i) != 1) {
					commonCount[num]++;
				}
			}
		}
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			int count = N * N + 2;
			
			for(int x = 2; x <= N; x++) {
				count -= commonCount[x] * 2 - 1;
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int getGCD(int a, int b) {
		while(b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}