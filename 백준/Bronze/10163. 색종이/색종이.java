import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 받는 정보를 역순으로 계산하기 위해 스택에 넣었다 빼낸다.
		Stack<Integer[]> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Integer[] data = new Integer[4];
			for (int j = 0; j < 4; j++) {
				data[j] = Integer.parseInt(st.nextToken());
			}
			stack.push(data);
		}
		
		// 색종이가 해당 위치에 있는지 없는지
		boolean[][] isColored = new boolean[1001][1001];
		// n개의 색종이에 대해 저장할 변수
		int[] count = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			Integer[] data = stack.pop();
			for (int dx = 0; dx < data[2]; dx++) {
				for (int dy = 0; dy < data[3]; dy++) {
					if (isColored[data[0] + dx][data[1] + dy] == false) {
						count[i]++;
						isColored[data[0] + dx][data[1] + dy] = true;
					}
				}
			}
		}
		for (int c : count) {
			System.out.println(c);
		}
	}
}