import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] s1 = new int[4];
		int[] s2 = new int[2];
		int total = 0;
		for(int i = 0; i < 4; i++) {
			s1[i] = Integer.parseInt(br.readLine());
			total += s1[i];
		}
		for(int i = 0; i < 2; i++) {
			s2[i] = Integer.parseInt(br.readLine());
			total += s2[i];
		}
		Arrays.sort(s1);
		Arrays.sort(s2);
		total -= s1[0] + s2[0];
		System.out.println(total);
	}
}