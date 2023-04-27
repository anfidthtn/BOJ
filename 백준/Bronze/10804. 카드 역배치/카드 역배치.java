import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] 바구니;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		바구니 = new int[21];
		for(int i = 1; i <= 20; i++) {
			바구니[i] = i;
		}
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			swap(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i = 1; i <= 20; i++) {
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