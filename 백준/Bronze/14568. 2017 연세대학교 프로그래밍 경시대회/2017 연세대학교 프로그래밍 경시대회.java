import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		for(int nam = 1; nam < N - 1; nam++) {
			for(int young = nam + 2; young < N - 1; young++) {
				int teck = N - nam - young;
				if (teck <= 0) {
					break;
				}
				if (teck % 2 == 1) {
					continue;
				}
				count++;
			}
		}
		System.out.println(count);
	}
}