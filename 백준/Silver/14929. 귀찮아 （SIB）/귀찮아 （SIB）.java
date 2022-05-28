import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long result = 0;
		int sum = 0;
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			result += sum * num;
			sum += num;
		}
		System.out.println(result);
	}
}