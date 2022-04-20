import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N은 받을 필요가 없어서 패스
		st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		
		// 최대 온도 합계를 저장할 변수 
		int maxTempSum = Integer.MIN_VALUE;
		// 현재 온도 합계를 저장할 변수
		int nowTempSum = 0;
		
		// 그동안 들어온 온도를 저장하는 큐
		Queue<Integer> queue = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			// 새로 들어올 온도를 받는다.
			int newTemp = Integer.parseInt(st.nextToken());
			
			// 온도를 큐에 넣고
			queue.offer(newTemp);
			// 큐에 들어갔으니 합계를 추가해준다.
			nowTempSum += newTemp;
			if (queue.size() > K) {
				// 큐에 들어가며 사이즈가 K를 넘어가면 빼줘서 K개를 맞춘다.
				// 맞추면서 큐에서 빠진 값을 합계에서 빼준다.
				nowTempSum -= queue.poll();
			}
			if (queue.size() == K) {
				// K개의 합계가 구해졌을 때 최대 온도 합계를 갱신할 수 있다면 갱신해준다.
				maxTempSum = Math.max(maxTempSum, nowTempSum);
			}
		}
		System.out.print(maxTempSum);
	}
}