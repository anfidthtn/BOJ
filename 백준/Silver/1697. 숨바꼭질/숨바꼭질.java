import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if (N == K) {
			System.out.println(0);
			return;
		}
		int time = 0;
		boolean[] isVisited = new boolean[100001];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		isVisited[N] = true;
		while(!queue.isEmpty()) {
			time++;
			Queue<Integer> temp = new LinkedList<>();
			while(!queue.isEmpty()) {
				int now = queue.poll();
				for (int nextPoint : new int[] {now - 1, now + 1, now * 2}) {
					if (nextPoint < 0 || nextPoint > 100000) continue;
					if (isVisited[nextPoint]) continue;
					isVisited[nextPoint] = true;
					if (nextPoint == K) {
						System.out.print(time);
						return;
					}
					temp.add(nextPoint);
				}
			}
			queue = temp;
		}
	}
}