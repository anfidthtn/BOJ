import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, ArrayList<Integer>> storePoints = new HashMap<>();
		// 4구역으로 나누어 위치를 저장할 준비를 한다.
		for (int i = 1; i <= 4; i++) {
			storePoints.put(i, new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			// 구역에 따라 위치를 저장한다.
			st = new StringTokenizer(br.readLine());
			storePoints.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		// 동근이의 구역과 거리를 받는다.
		int zone = Integer.parseInt(st.nextToken());
		int dist = Integer.parseInt(st.nextToken());
		
		int sumMinDistance = 0;
		switch(zone) {
		/**
		 * 각 방향별로 드럽게 짜야겠다.
		 */
		case 1:
			// 북쪽에 있는 경우
			for (int north : storePoints.get(1)) {
				//북쪽에 있는 녀석은 단순 최저거리합
				sumMinDistance += Math.abs(dist - north);
			}
			for (int south : storePoints.get(2)) {
				// 남쪽에 있는 녀석은
				// 현재 지점과 오른쪽 거리 합  + 걔 오른쪽 거리합
				// 현재 지점과 왼쪽 거리 합 + 걔 왼쪽 거리 합 중 낮은 값
				// + 세로길이
				sumMinDistance += Math.min(2 * width - (dist + south), dist + south) + height;
			}
			for (int west : storePoints.get(3)) {
				// 서쪽에 있는 녀석은 현재 지점과 왼쪽 거리 합 + 걔 거리
				sumMinDistance += dist + west;
			}
			for (int east : storePoints.get(4)) {
				// 동쪽에 있는 녀석은 현재 지점과 오른쪽 거리 합 + 걔 거리
				sumMinDistance += (width - dist) + east;
			}
			break;
		case 2:
			// 남쪽에 있는 경우
			for (int north : storePoints.get(1)) {
				// 북쪽에 있는 녀석은
				// 현재 지점과 오른쪽 거리 합  + 걔 오른쪽 거리합
				// 현재 지점과 왼쪽 거리 합 + 걔 왼쪽 거리 합 중 낮은 값
				// + 세로길이
				sumMinDistance += Math.min(2 * width - (dist + north), dist + north) + height;
			}
			for (int south : storePoints.get(2)) {
				// 남쪽에 있는 녀석은 단순 최저거리합
				sumMinDistance += Math.abs(dist - south);
			}
			for (int west : storePoints.get(3)) {
				// 서쪽에 있는 녀석은 현재 지점과 왼쪽 거리 합 + 남쪽으로부터의 걔 거리
				sumMinDistance += dist + (height - west);
			}
			for (int east : storePoints.get(4)) {
				// 동쪽에 있는 녀석은 현재 지점과 오른쪽 거리 합 + 남쪽으로부터의 걔 거리
				sumMinDistance += (width - dist) + (height - east);
			}
			break;
		case 3:
			// 서쪽에 있는 경우
			for (int north : storePoints.get(1)) {
				// 북쪽에 있는 녀석은 현재 지점에서 북쪽까지 거리 + 걔 거리
				sumMinDistance += dist + north;
			}
			for (int south : storePoints.get(2)) {
				// 남쪽에 있는 녀석은 현재 지점에서 남쪽까지 거리 + 걔 거리
				sumMinDistance += (height - dist) + south;
			}
			for (int west : storePoints.get(3)) {
				// 서쪽에 있는 녀석은 단순 최저거리합
				sumMinDistance += Math.abs(dist - west);
			}
			for (int east : storePoints.get(4)) {
				// 동쪽에 있는 녀석은
				// 현재 지점과 북쪽 거리 합  + 걔 북쪽 거리합
				// 현재 지점과 남쪽 거리 합 + 걔 남쪽 거리 합 중 낮은 값
				// + 가로길이
				sumMinDistance += Math.min(2 * width - (dist + east), dist + east) + width;
			}
			break;
		case 4:
			// 동쪽에 있는 경우
			for (int north : storePoints.get(1)) {
				// 북쪽에 있는 녀석은 현재 지점에서 북쪽까지 거리 + 걔의 오른쪽 거리
				sumMinDistance += dist + (width - north);
			}
			for (int south : storePoints.get(2)) {
				// 남쪽에 있는 녀석은 현재 지점에서 남쪽까지 거리 + 걔의 오른쪽 거리
				sumMinDistance += (height - dist) + (width - south);
			}
			for (int west : storePoints.get(3)) {
				// 서쪽에 있는 녀석은
				// 현재 지점과 북쪽 거리 합  + 걔 북쪽 거리합
				// 현재 지점과 남쪽 거리 합 + 걔 남쪽 거리 합 중 낮은 값
				// + 가로길이
				sumMinDistance += Math.min(2 * width - (dist + west), dist + west) + width;
			}
			for (int east : storePoints.get(4)) {
				// 남쪽에 있는 녀석은 단순 최저거리합
				sumMinDistance += Math.abs(dist - east);
			}
			break;
		}
		System.out.print(sumMinDistance);
	}
}