import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static List<Integer> list;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line.equals("0")) {
				break;
			}
			if (sb.length() != 0) {
				sb.append("\n");
			}
			StringTokenizer st = new StringTokenizer(line);
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>(K);
			for (int i = 0; i < K; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			gogo(0, 0, 0);
		}
		System.out.print(sb.toString());
	}

	public static void gogo(int idx, int selected, int counts) {
		if (counts == 6) {
			for (int i = 0; i < K; i++) {
				if ((selected & (1 << i)) > 0) {
					sb.append(list.get(i)).append(" ");
				}
			}
			sb.append("\n");
			return;
		}
		if (idx == K) {
			return;
		}
		gogo(idx + 1, selected ^ (1 << idx), counts + 1);
		gogo(idx + 1, selected, counts);
	}
}