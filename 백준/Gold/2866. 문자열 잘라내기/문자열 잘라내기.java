import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] temp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int R = temp[0];
		int C = temp[1];
		
		// 최초 입력 문자열을 (내가 생각하기 편하게) 입력받으면서 밑에서부터 채워나간다.
		String[] str = new String[R];
		for (int i = 0; i < R; i++) {
			// 첫 줄이 가장 밑으로, 두번째 줄이 그 위로, 그런식으로 쌓이게 하여 카운트를 올릴 때 제거되는 줄을 가장 밑줄으로 만듦
			str[R - i - 1] = br.readLine().trim();
		}
		
		/**
		 * 인덱스 어레이의 경우 해당 열을 대표하는 것이다.
		 */
		ArrayList<Integer> indexArr = new ArrayList<>();
		// 열 개수만큼 인덱스를 확보한다.
		for (int i = 0; i < C; i++) indexArr.add(i);
		
		// 인덱스 어레이는 원본 문자열의 순서대로 인덱스를 정렬해야해서 정렬함수를 재정의한다.
		indexArr.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 두 열의 첫 행부터 끝 행까지 비교를 한다.
				for (int i = 0; i < R; i++) {
					// 우선순위가 나오면 우선순위대로 반환
					if (str[i].charAt(o1) < str[i].charAt(o2)) return -1;
					if (str[i].charAt(o1) > str[i].charAt(o2)) return 1;
				}
				// (그럴리야 없지만) 끝까지 같으면 앞에께 더 작다고 반환
				return -1;
			}
		});
		// 이 시점에 indexArr는 원본 문자열의 열의 우선순위에 따라 정렬이 된 상태이다.
		
		
		// maxDepth는 중복이 어디서부터 발생하지 않는지를 return 받기 때문에 R - maxDepth까지 제거하면 중복되는 문자열이 나온다.
		int maxDepth = checkDiffDepth(str, 0, indexArr, 0, C - 1);
		System.out.println(R - maxDepth);
	}
	
	// 이전행까지 모두 같았을 때, 같은 지점이 어디까지 나오는지 확인한다.
	public static int checkDiffDepth(String[] str, int row, ArrayList<Integer> indexArr, int left, int right) {
		int maxDepth = 1;
		// 연속으로 같은 문자가 몇 개 나왔는지 체크
		int straightCount = 1;
		// 해당 행의 좌측 + 1(가장 좌측은 이전과 비교할 수 없으니 안 봐도 됨)부터 우측까지 본다.
		for(int i = left + 1; i <= right; i++) {
			if (str[row].charAt(indexArr.get(i - 1)) != str[row].charAt(indexArr.get(i))) {
				// 해당 행의 문자열의 . i - 1번째 순위 열문자열의 열의 문자가 i번째와 같지 않다면
				if (straightCount > 1) {
					// 같은 구간이 있었는지 확인해서 같은 구간이 있었다면 해당 구간을 탐색시킨다.
					maxDepth = Math.max(maxDepth, 1 + checkDiffDepth(str, row + 1, indexArr, i - straightCount, i - 1));
					/**
					 * 이번 행이 a, b, b, b, c 이렇게 있었다면
					 * c까지 읽었을 때 straightCount = 3, i == 4였을 것이다.
					 * 그러니까 b가 있던 1 ~ 3까지를 돌려야하니, 4 - 3 ~ 4 - 1까지 순회하는 것이다.
					 */ 
				}
				// 연속구간 개수를 초기화한다.
				straightCount = 1;
			}
			else {
				// 같다면 연속구간을 추가한다.
				straightCount++;
			}
		}
		if (straightCount > 1) {
			// right가 연속구간에 있었을 경우도 처리해줘야한다.
			maxDepth = Math.max(maxDepth, 1 + checkDiffDepth(str, row + 1, indexArr, right - straightCount + 1, right));
			/**
			 * 이번 행이 a, a, b, b, b, b 였을 경우 a, a는 첫 b가 발견되었을 때 상단의 for문에서 돌아갔을 것이다.
			 * 위의 for문에서 마지막 b를 발견했지만 다른 값을 발견하지 못하여 처리하지 못했다.
			 * 이 때 5(right) - 4(straightCount) + 1 ~ 5(right) 까지 2 ~ 5범위로 탐색을 시킨다는 것이다.
			 */
		}
		return maxDepth;
	}
}