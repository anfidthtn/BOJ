import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *P2493_gold5_탑
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		/**
		 * stack에는 [높이, 인덱스] 쌍을 저장
		 * 새로 들어온 녀석이 top에 있는 높이보다 낮으면 top의 인덱스 출력하고, top을 증가시키고 stack에 현재 높이, 위치 저장
		 * 새로 들어온 녀석이 top에 있는 높이보다 높으면 top을 줄임 (top이 -1이 되거나 높은 녀석이 나올 때까지)
		 * 이 때, -1이 되면 0을 출력하고 top을 증가(0으로)시키고 현재 높이, 위치 저장
		 */
		Stack<Integer[]> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			int height = Integer.parseInt(st.nextToken());
			// 스택이 다 비거나, 더 높은 높이가 나올 때까지 top을 줄임
			while (!stack.isEmpty() && stack.lastElement()[0] < height) stack.pop();
			if (stack.isEmpty()) {
				// 수신지점이 없으니 0
				sb.append("0 ");
			}
			else {
				// 수신지점이 있으니 수신지점 출력 (인덱스 + 1)
				sb.append(stack.lastElement()[1] + 1).append(" ");
			}
			stack.push(new Integer[] {height, i});
		}
		System.out.print(sb.toString());
	}
}
