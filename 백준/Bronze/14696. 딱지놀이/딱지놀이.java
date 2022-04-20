import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		turn : for(int i = 0; i < N; i++) {
			int[] A = new int[4];
			int[] B = new int[4];
			
			// A에 대해서 가지고있는 거 숫자 올려줌
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while (st.hasMoreTokens()) {
				A[-1 + Integer.parseInt(st.nextToken())]++;
			}
			
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while (st.hasMoreTokens()) {
				B[-1 + Integer.parseInt(st.nextToken())]++;
			}
			for (int shape = 3; shape >= 0; shape--) {
				// 모양의 우선순위대로 간다.
				if (A[shape] > B[shape]) {
					// A가 이기면 A
					System.out.println("A");
					continue turn;
				}
				if (B[shape] > A[shape]) {
					// B가 이기면 B
					System.out.println("B");
					continue turn;
				}
			}
			// 둘 다 못 이기면 D
			System.out.println("D");
		}
	}
}