import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] counts = new int[10];
		for(int i = 0; i < str.length(); i++) {
			counts[str.charAt(i) - '0']++;
		}
		if (counts[0] == 0) {
			System.out.println(-1);
			return;
		}
		int sum = 0;
		for(int i = 1; i< 10; i++) {
			sum += i * counts[i];
		}
		if (sum % 3 != 0) {
			System.out.println(-1);
			return;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 9; i >= 0; i--) {
			for(int c = 0; c < counts[i]; c++) {
				bw.write('0' + i);
			}
		}
		bw.flush();
	}
}