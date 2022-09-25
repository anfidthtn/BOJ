import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = -1;
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] counts = new int[51];
		while(st.hasMoreTokens()) {
			counts[Integer.parseInt(st.nextToken())]++;
		}
		for(int i = 50; i >= 0; i--) {
			if (counts[i] == i) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}
}