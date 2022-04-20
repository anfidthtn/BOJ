import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int min = Integer.MAX_VALUE;
		int minCount = 0;

		// 밑에서 위로 올라오는 석순
		ArrayList<Integer> up = new ArrayList<>();
		// 위에서 밑으로 내려오는 종유석
		ArrayList<Integer> down = new ArrayList<>();

		for (int i = 0; i < N / 2; i++) {
			up.add(Integer.parseInt(br.readLine()));
			down.add(Integer.parseInt(br.readLine()));
		}
		// 오름차순
		up.sort(Integer::compareTo);
		// 내림차순
		// 아니 이거 왜 down.sort((a, b) -> a <= b ? 1 : -1); 안먹히냐
		Collections.sort(down, Collections.reverseOrder());

		int u = 0;
		int d = 0;
		for (int i = 1; i <= H; i++) {
			// 현재 비행중인 높이를 갈 때 몇 개를 그냥 패스할 수 있는지 체크한다.
			while (up.size() - u > 0 && up.get(u) < i)
				u++;
			// 현재 비행중인 높이를 갈 때 몇 개와 만나는지 체크한다.
			while (down.size() - d > 0 && H - down.get(d) < i)
				d++;
			int collepse = up.size() - u + d;
			if (min > collepse) {
				min = collepse;
				minCount = 1;
			} else if (min == collepse)
				minCount++;
		}
		System.out.print(min + " " + minCount);
	}
}