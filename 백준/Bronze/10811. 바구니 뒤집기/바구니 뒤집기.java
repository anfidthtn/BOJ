import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int[] 바구니;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		바구니 = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			바구니[i] = i;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			swap(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i = 1; i <= N; i++) {
			System.out.print(바구니[i] + " " );
		}
	}
	public static void swap(int start, int end) {
		int temp;
		while(start < end) {
			temp = 바구니[start];
			바구니[start] = 바구니[end];
			바구니[end] = temp;
			start++;
			end--;
		}
	}
}