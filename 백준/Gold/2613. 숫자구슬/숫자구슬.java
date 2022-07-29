import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] balls = new int[N];
		int sum = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
			sum += balls[i];
			max = Math.max(max, balls[i]);
		}
		int answer = sum;
		int l = max;
		int r = answer;
		while (l <= r) {
			int mid = (l + r) >> 1;
			int count = 1;
			int value = mid;
			int idx = 0;
			while(idx < N) {
				if (value < balls[idx]) {
					if(++count > M) {
						break;
					}
					value = mid - balls[idx];
				}
				else {
					value -= balls[idx]; 
				}
				idx++;
			}
			if (idx == N) {
				answer = mid;
				r = mid - 1;
			}
			else {
				l = mid + 1;
			}
		}
		System.out.println(answer);
		int[] sums = new int[M];
		Deque<Integer>[] deque = new LinkedList[M];
		for(int i = 0; i < M; i++) {
			sums[i] = balls[i];
			deque[i] = new LinkedList<>();
			deque[i].addFirst(balls[i]);
		}
		for(int i = M; i < N; i++) {
			sums[M - 1] += balls[i];
			deque[M - 1].addLast(balls[i]);
		}
		for(int i = M - 1; i > 0; i--) {
			while(sums[i] > answer) {
				sums[i] -= deque[i].peekFirst();
				sums[i - 1] += deque[i].peekFirst();
				deque[i - 1].addLast(deque[i].pollFirst());
			}
		}
		for(Deque<Integer> de : deque) {
			System.out.print(de.size() + " ");
		}
	}
}