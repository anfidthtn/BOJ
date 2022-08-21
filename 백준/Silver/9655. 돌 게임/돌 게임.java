import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] result = new boolean[1001];
		result[1] = true;
		result[3] = true;
		for(int i = 4; i <= N; i++) {
			for(int j = 1; j <= 3; j += 2) {
				if (!result[i - j]) {
					result[i] = true;
					break;
				}
			}
		}
		if(result[N]) {
			System.out.println("SK");
		}
		else {			
			System.out.println("CY");
		}
	}
}