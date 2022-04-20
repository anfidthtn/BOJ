import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			if (x1 == x2 && y1 == y2) {
				//둘의 좌표가 같을 때
				if (r1 == r2)
					// 거리가 같으면 위치 개수 무한
					sb.append(-1).append("\n");
				else
					// 거리가 다르면 존재 불가
					sb.append(0).append("\n");
			}
			else {
				// 실수 연산의 오차 줄이기로, 그냥 전부 정수형 연산으로 계산
				int distanceSquare = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
				// 두 거리의 합 제곱
				int rSumSquare = (r1 + r2) * (r1 + r2);
				// 두 거리 차 제곱
				int rSubSquare = (r1 - r2) * (r1 - r2);
				
				if (distanceSquare > rSumSquare)
					// 두 점 사이 거리가 모자라면 있을 수 있는 위치가 없다.
					sb.append(0).append("\n");
				else if (distanceSquare == rSumSquare)
					// 외접이면 1곳 존재 가능
					sb.append(1).append("\n");
				else if (rSubSquare < distanceSquare && distanceSquare < rSumSquare)
					// 내접과 외접 사이에선 2곳 존재 가능
					sb.append(2).append("\n");
				else if (distanceSquare == rSubSquare)
					// 내접하면 1곳
					sb.append(1).append("\n");
				else
					// 포함되면 0곳
					sb.append(0).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}
