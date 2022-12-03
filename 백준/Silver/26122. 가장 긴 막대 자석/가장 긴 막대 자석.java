import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int before = 0;
		int beforeRecord = 0;
		int nowRecord = 0;
		int answer = 0;
		for (int i = 0; i < K; i++) {
			int c = br.read();
			if (c != before) {
				before = c;
				answer = Math.max(answer, Math.min(nowRecord, beforeRecord));
				beforeRecord = nowRecord;
				nowRecord = 1;
			} else {
				nowRecord++;
			}
		}
		answer = Math.max(answer, Math.min(nowRecord, beforeRecord));
		System.out.println(answer * 2);
	}
}