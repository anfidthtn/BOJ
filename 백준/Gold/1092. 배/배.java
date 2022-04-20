import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] crane = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int m = Integer.parseInt(br.readLine());
		int[] weights = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(crane);
		Arrays.sort(weights);
		int nowCraneNum = n - 1;
		int nowBoxNum = m - 1;
		// 크레인이 감당 가능한 최대무게보다 더 무거운 박스가 있다면 불가능이다.
		if (crane[nowCraneNum] < weights[nowBoxNum]) {
			System.out.print(-1);
			return;
		}
		// 경과 시간을 기록한다.
		int time = 0;
		// 남은 박스가 있으면 계속 시간을 증가시키며 돌린다.
		while(nowBoxNum >= 0) {
			// 현재 남은 박스 중, 가장 무거운 박스를 어느 크레인부터 옮기지 못하는지 본다.
			while (nowCraneNum >= 0 && crane[nowCraneNum] >= weights[nowBoxNum]) {
				nowCraneNum--;
				// 남은 박스의 최고 무게가 줄어들며 새로 가능해진 크레인에 대해 이미 시간이 지난만큼 이 크레인이 옮겼을 박스 개수만큼 박스를 제거한다. 
				nowBoxNum -= time;
				// 만약 이 과정에서 박스 개수가 0개가 되면 끝난다.
				if (nowBoxNum < 0) {
					System.out.print(time);
					return;
				}
			}
			// 전체 크레인이 전부 다 사용가능한 상태가 되면
			if (nowCraneNum < 0) {
				// 남은 박스 개수를 전체 크레인 개수로 나누면 된다.
				time += Math.ceil((double) (nowBoxNum + 1) / n);
				System.out.print(time);
				return;
			}
			// 앞의 과정을 다 거치고 여기까지 오면 시간을 증가시키고
			time++;
			// 현재 가장 무거운 박스를 제거할 수 있는 크레인 개수만큼 무거운 박스부터 제거한다.
			nowBoxNum -= (n - nowCraneNum - 1);
		}
		System.out.print(time);
	}
}