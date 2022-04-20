import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		/**
		 * 각 좌표별 자른 지점을 정렬하여 구간간 거리를 탐색
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 가로로 자른 지점
		ArrayList<Integer> wList = new ArrayList<>();
		// 세로로 자른 지점
		ArrayList<Integer> hList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		// 가장자리는 자른 지점으로 취급
		wList.add(0);
		wList.add(width);
		hList.add(0);
		hList.add(height);
		
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			// 가로냐 세로냐에 따라 잘린 지점 넣음
			if (Integer.parseInt(st.nextToken()) == 1) {
				wList.add(Integer.parseInt(st.nextToken()));
			}
			else {
				hList.add(Integer.parseInt(st.nextToken()));
			}
		}
		// 잘린 지점을 정렬
		wList.sort((a, b) -> a < b ? -1 : 1);
		hList.sort((a, b) -> a < b ? -1 : 1);

		// 잘린 구간 길이가 가장 긴 것을 뽑아냄
		int wMaxDiff = 0;
		for (int i = 0; i < wList.size() - 1; i++) {
			wMaxDiff = Math.max(wMaxDiff, wList.get(i + 1) - wList.get(i));
		}
		int hMaxDiff = 0;
		for (int i = 0; i < hList.size() - 1; i++) {
			hMaxDiff = Math.max(hMaxDiff, hList.get(i + 1) - hList.get(i));
		}
		// 곱함
		System.out.print(wMaxDiff * hMaxDiff);
	}
}