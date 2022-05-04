import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		int KJM = Integer.parseInt(st.nextToken()) - 1;
		int IHS = Integer.parseInt(st.nextToken()) - 1;
		int result = KJM^IHS;
		for(int i = 16; i >= 0; i--) {
			if((result & (1 << i)) != 0) {
				System.out.println(i + 1);
				return;
			}
		}
	}
}