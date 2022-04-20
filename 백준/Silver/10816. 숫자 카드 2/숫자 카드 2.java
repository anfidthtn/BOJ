import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * O(1)시간에 get, put할 수 있는 HashMap 사용하면 이분탐색 안해도 될 거 같은데? 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Map<Integer, Integer> countMap = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			// 입력숫자
			int num = Integer.parseInt(st.nextToken());
			// 카운트 1개 올려줌
			int count = 1;
			if (countMap.containsKey(num)) {
				// 키가 이미 존재했다면 그 키의 기존 값만큼 추가
				count += countMap.get(num);
			}
			// 키의 값업데이트
			countMap.put(num, count);
		}
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 1개 이상 갖고있으면
			if (countMap.containsKey(num)) {
				// 갖고있는 것 출력
				sb.append(countMap.get(num)).append(' ');
			}
			else {
				// 아니면 0 출력
				sb.append("0 ");
			}
		}
		System.out.print(sb.toString());
	}
}