import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			// 서류 순위(인덱스)별 인터뷰 순위(값)
			int[] interview = new int[N + 1];
			// 인터뷰 순위(인덱스)별 서류 순위(값)
			int[] document = new int[N + 1];
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int docu = Integer.parseInt(st.nextToken());
				int inter = Integer.parseInt(st.nextToken());
				interview[docu] = inter;
				document[inter] = docu;
			}
			int count = 0;
			int maxInter = N + 1;
			int maxDocu = N + 1;
			for(int i = 1; i <= N; i++) {
				if (document[i] < maxDocu) {
					count++;
					maxDocu = document[i];
				}
				if (interview[i] < maxInter) {
					count++;
					maxInter = interview[i];
				}
			}
			count /= 2;
			
			sb.append(count).append('\n');
		}
		System.out.print(sb.toString());
	}
}