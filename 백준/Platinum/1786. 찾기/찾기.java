import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		String pattern = br.readLine();
		
		if (target.length() < pattern.length()) {
			System.out.print(0);
			return;
		}
		
		List<Integer> result = KMP(target, pattern);
		
		StringBuilder sb = new StringBuilder();
		for(int idx : result) {
			sb.append(idx + 1).append(' ');
		}
		System.out.println(result.size());
		System.out.print(sb.toString());
	}
	public static List<Integer> KMP(String target, String pattern){
		// result : 시작지점을 저장할 리스트
		List<Integer> result = new ArrayList<>();
		// Pi : 접두사 접미사 일치하는 길이 배열
		int[] Pi = getPi(pattern);
		
		// 패턴 탐색 인덱스
		int pIdx = 0;
		
		// target의 첫 문자부터 끝 문자까지 탐색한다.
		for (int i = 0; i < target.length(); i++) {
			while (pIdx > 0 && target.charAt(i) != pattern.charAt(pIdx)) {
				// 패턴과 일치하지 않는 것이 나오면
				// getPi에서처럼 패턴이 일치하거나 처음으로 돌아갈 때까지 패턴 탐색 지점을 앞으로 보낸다.
				pIdx = Pi[pIdx - 1];
			}
			if (target.length() - i < pattern.length() - pIdx) {
				// 패턴 길이를 다 채워도 남은 타겟의 길이가 모자라면 끝내도 된다.
				return result;
			}
			
			if (target.charAt(i) == pattern.charAt(pIdx)) {
				// 문자열과 패턴 탐색지점이 일치하면 계속 탐색을 이어나가거나 완료처리를 한다.
				if (pIdx == pattern.length() - 1) {
					// 패턴 끝까지 일치한 경우 패턴 시작 인덱스를 저장한다.
					result.add(i - pattern.length() + 1);
					// 패턴 탐색 지점을 접미사 일치 위치로 이동시킨다.
					pIdx = Pi[pIdx];
				}
				else {
					// 끝까지 가지 않았다면 패턴 탐색 지점을 다음 지점으로 이동시킨다.
					pIdx++;
				}
			}
		}
		return result;
	}
	
	// get Pattern Index라는 뜻이다.
	public static int[] getPi(String pattern) {
		// 패턴 길이만큼 Pi배열이 필요하니 패턴 길이만큼으로 만들어 줌
		int[] Pi = new int[pattern.length()];
		// 접두사 탐색 인덱스
		int pIdx = 0;
		// i : 접미사 탐색 인덱스
		for (int i = 1; i < pattern.length(); i++) {
			while(pIdx > 0 && pattern.charAt(i) != pattern.charAt(pIdx)) {
				/**
				 * 접두사 탐색 인덱스가 0보다 큰 상황에서 일치하지 않는 패턴이 나오면
				 * 일치하는 패턴이 나올 때까지 접두사 시작지점을 앞으로 당긴다.
				 * 패턴이 aaabaabaaaab 라고 가정하면
				 * aa
				 * pIdx = 0, Pi[1] = 0 -> pIdx = 1, Pi[1] = 1 (a - a)
				 * aaa
				 * pIdx = 1, Pi[2] = 0 -> pIdx = 2, Pi[2] = 2 (aa - aa)
				 * aaab
				 * pIdx = 2, Pi[3] = 0
				 * --(같지 않다)-> pIdx = Pi[1](==1)
				 * --(같지 않다)-> pIdx = Pi[0](==0), Pi[3] = 0 (일치 x)
				 * aaaba
				 * pIdx = 0, Pi[4] = 0 -> pIdx = 1, Pi[4] = 1 (a - a)
				 * aaabaa
				 * pIdx = 1, Pi[5] = 0 -> pIdx = 2, Pi[5] = 2 (aa - aa)
				 * aaabaab
				 * pIdx = 2, Pi[6] = 0
				 * --(같지 않다)-> pIdx = Pi[1](==1)
				 * --(같지 않다)-> pIdx = Pi[0](==0), Pi[6] = 0 (일치 x)
				 * 이런식으로 진행된다.
				 */
				pIdx = Pi[pIdx - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(pIdx)) {
				pIdx++;
				Pi[i] = pIdx;
			}
		}
		return Pi;
	}
}