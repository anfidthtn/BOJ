import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * L과 N 조건이 1000 이하라서 가장 쉬운 풀이는
 * 그냥 1번부터 N번까지 자신이 원하는 곳에
 * 다른사람이 가져갔다면 못 가져가고
 * 가져가지 않았다면 가져가고 가져갔다고 표시하는 식으로 해서 카운팅을 한 다음
 * 수 비교는 대충 해갖고 최대값 뽑아내면 된다.
 * 
 * L과 N이 큰 수일때는
 * (이미 가져간 조각의) 시작점과 끝지점 쌍을 정렬해두고
 * 이분탐색으로 새로운 사람이 원하는 구간을 찾아낸다음
 * 구간이 합쳐지면 합치는 식으로 하면 될 거 같긴 한데
 * 효율적인 구현 방법이 생각 안 나서 못하겠다. 이건 어려울 것 같다.
 * 그래서 그냥 쉬운 풀이로 구현
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		// 변수 이름만 봐도 대충 감 올 것 같다.
		boolean[] isTaken = new boolean[L + 1];
		int maxWantPerson = 0;
		int maxWant = 0;
		int maxTakePerson = 0;
		int maxTake = 0;
		
		for (int i = 1; i <= N; i++) {
			int[] wantIdx = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// 원하는 길이의 최고치가 갱신되면 정보 갱신
			if (wantIdx[1] - wantIdx[0] + 1 > maxWant) {
				maxWant = wantIdx[1] - wantIdx[0] + 1;
				maxWantPerson = i;
			}
			
			// 이번 사람이 가져가는 조각 수
			int take = 0;
			for(int j = wantIdx[0]; j <= wantIdx[1]; j++) {
				if (!isTaken[j]) {
					// 가져가지 않은 조각이면 가져가고 가져갔다는 표시
					take++;
					isTaken[j] = true;
				}
			}
			
			// 가져간 길이도 원하는 길이처럼 최고치 갱신되면 정보 갱신 
			if (take > maxTake) {
				maxTake = take;
				maxTakePerson = i;
			}
		}
		// 출력
		System.out.println(maxWantPerson);
		System.out.println(maxTakePerson);
	}
}