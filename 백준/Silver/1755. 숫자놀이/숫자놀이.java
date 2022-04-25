import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class NumberString implements Comparable<NumberString>{
		// 숫자와 그에 해당하는 단어를 저장
		String word = "";
		int number;
		
		// 단어의 사전순대로 비교
		@Override
		public int compareTo(NumberString o) {
			return word.compareTo(o.word);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// 각 숫자별 스펠링
		String[] words = {
				"zero ",
				"one ",
				"two ",
				"three ",
				"four ",
				"five ",
				"six ",
				"seven ",
				"eight ",
				"nine "
		};
		
		// 입력 케이스가 적어서 입력하며 문자열 순서대로 정렬하고, 뒤에 순서대로 빼기 위해 PQ사용
		PriorityQueue<NumberString> pq = new PriorityQueue<>();
		
		// M부터 N까지 문자열 만들어서 PQ에 넣음
		for (int i = M; i <= N; i++) {
			NumberString ns = new NumberString();
			// 수 정보 등록
			ns.number = i;
			// 해당 수를 문자열로
			String numString = Integer.toString(i);
			// 1자리면 1자리, 2자리면 2자리만큼
			for (int idx = 0 ; idx < numString.length(); idx++) {
				// 해당 숫자에 해당하는 단어를 찾아서 저장한다.
				// 뒤에 공백 ' '이 붙게 되는데, 어차피 공백이 사전순이 더 높아서 공백제거를 안 해도 된다.
				// 신경쓰인다면 이 for문 종료 후 .trim()으로 제거해줘도 된다.
				ns.word += words[numString.charAt(idx) - '0'];
			}
			// PQ에 더해준다.
			pq.add(ns);
		}
		
		// 출력부분
		// 10번마다 줄바꿈을 위해 체크
		int pCount = 0;
		// 전부 다 출력 할 때까지 반복
		while(!pq.isEmpty()) {
			// 빼서 수 출력
			System.out.print(pq.poll().number + " ");
			// 10개 될 때마다 줄바꿈
			if (++pCount % 10 == 0) {
				System.out.println();
			}
		}
	}
}