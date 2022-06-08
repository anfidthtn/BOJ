import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append("Case #").append(tNum).append(": ");
			Map<String, Integer> idx = new HashMap<>();
			int S = Integer.parseInt(br.readLine());
			for (int i = 0; i < S; i++) {
				idx.put(br.readLine(), i);
			}
			int Q = Integer.parseInt(br.readLine());
			int able = S;
			boolean[] ableList = new boolean[S];
			int count = 0;
			for (int i = 0; i < Q; i++) {
				int nowIdx = idx.get(br.readLine());
				if (!ableList[nowIdx]) {
					ableList[nowIdx] = true;
					able--;
				}
				if (able == 0) {
					ableList = new boolean[S];
					able = S - 1;
					count++;
					ableList[nowIdx] = true;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}