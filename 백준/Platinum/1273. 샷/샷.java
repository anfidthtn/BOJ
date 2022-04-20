import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		/**
		 * 각각
		 * 검정색 개수
		 * 회색 개수
		 * 흰색 개수
		 * 최대 높이(합계의 최대값)
		 */
		int[] black = new int[N];
		int[] gray = new int[N];
		int[] white = new int[N];
		int maxH = 0;
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			black[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			gray[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			white[i] = Integer.parseInt(st.nextToken());
			maxH = Math.max(maxH, black[i] + gray[i] + white[i]);
		}
		
		/**
		 * 해당 배열의 인덱스를 기점으로, 저장된 만큼 점수가 변한다.
		 * 예를 들어
		 * 1 2
		 * 0 1
		 * 0 1
		 * 이렇게 들어오면 해당 배열에는
		 * 0 2 -1 1 3 -5 이렇게 들어가게 되는데
		 * 이 말은
		 * 0 -> 1번째 높이로 바뀌면 2점이 올라가고 (아무것도 없다(0)가 검정색(1) 2개가 생김(0 -> 1 : +1 (*2)))
		 * 1 -> 2번째 높이로 바뀌면 1점이 떨어지고 (1번째 열의 검정색(1)이 아무것도 없는 것(0)으로 바뀜(1 -> 0 : -1))
		 * 2 -> 3번째 높이로 바뀌면 1점이 올라가고 (2번째 열의 검정색(1)이 회색(2)으로 바뀜(1 -> 2 : +1))
		 * 3 -> 4번째 높이로 바뀌면 3점이 올라가고 (2번째 열의 회색(2)이 흰색(5)으로 바뀜(2 -> 5 : +3))
		 * 4 -> 5번째 높이로 바뀌면 5점이 떨어짐 (2번째 열이 흰색(5) 에서 아무것도 없는 것(0)으로 바뀜(5 -> 0 : -5))
		 * 
		 * 이를 이용해 누적합을 계산하면
		 * 0 2 1 2 5 0 으로 2, 1, 2, 5라는 높이별 점수정보를 도출해낼 수 있다.
		 */
		int[] point = new int[maxH + 2];
		// 처음에는 1짜리 가치 N개를 갖고있다고 생각하고 시작한다.
		point[1] = N;
		for(int i = 0; i < N; i++) {
			// black[i]번째까지 1점짜리 black이 있고 black[i] + 1번째에 회색으로 바뀌며 가치가 1 더 올라간다.
			point[black[i] + 1] += 1;
			// (앞의 지점 이후부터) black[i] + gray[i]번째까지 2점짜리 gray가 있고 그것 + 1번째에 흰색으로 바뀌며 가치가 3 더 올라간다.
			point[black[i] + gray[i] + 1] += 3;
			// (앞의 지점 이후부터) black[i] + gray[i] + white[i]번째까지 5점짜리 white가 있고 그것 + 1번째에 없는 것으로 바뀌며 가치가 5만큼 떨어진다.
			point[black[i] + gray[i] + white[i] + 1] -= 5;
		}
		
		// 이 for문이 끝나면 point[1] ~ point[maxH]까지 높이별 포인트가 등록된다.
		for(int i = 2; i <= maxH; i++) {
			// i == 1은 할 필요 없고 2부터 point배열을 재활용해서 누적합으로 높이별 포인트를 구한다.
			point[i] += point[i - 1];
		}
		
		// 보통 세그먼트 트리 개수 정적선언할 때 *4정도로 선언하니 *4로 선언
		int[] segTree = new int[maxH * 4];
		initTree(segTree, 1, 1, maxH);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(st.hasMoreTokens()) {
			int target = Integer.parseInt(st.nextToken());
			if (target > segTree[1]) sb.append(0).append('\n');
			else sb.append(point[getIdx(segTree, 1, 1, maxH, target)]).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void initTree(int[] segTree, int nodeNum, int left, int right) {
		if (left == right) {
			segTree[nodeNum] = 1;
			return;
		}
		segTree[nodeNum] = right - left + 1;
		initTree(segTree, nodeNum * 2, left, (left + right) >> 1);
		initTree(segTree, nodeNum * 2 + 1, ((left + right) >> 1) + 1, right);
	}
	public static int getIdx(int[] segTree, int nodeNum, int left, int right, int target) {
		// 어차피 1개씩 빠질 예정이니 방문할 때마다 빼준다.
		segTree[nodeNum]--;
		// 인덱스가 결정되면 반환한다.
		if (left == right) return left;
		if (segTree[nodeNum * 2] < target) {
			// 왼쪽 자식보다 남은 것이 크다면 남은 것에서 왼쪽 자식에 저장된 개수를 빼고 오른쪽 자식으로 탐색한다.
			return getIdx(segTree, nodeNum * 2 + 1, ((left + right) >> 1) + 1, right, target - segTree[nodeNum * 2]);
		}
		else {
			// 아니라면 왼쪽 자식으로 탐색을 들어간다.
			return getIdx(segTree, nodeNum * 2, left, (left + right) >> 1, target);
		}
	}
}