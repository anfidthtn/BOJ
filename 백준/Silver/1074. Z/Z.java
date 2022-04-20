import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 행과 열 조합 상관없이 행과 열 각각의 가치의 합계 구하면 됨 
 * 
 * 1행 1열은 1행가치 + 1열가치
 * 2행 5열은 2행가치 + 5열가치
 * ...
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 문제 조건에 R, C가 N따라서 범위 안으로 들어온다고 했어서 N값은 받을 필요가 없다.
		st.nextToken();
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		/**
		 * 문제를 관찰해보면 다음과 같은 사실을 알 수 있다.
		 * 열의 인덱스 C를 이진법으로 나타냈을 때
		 * 1의자리 * 1 + 2의자리 * 4 + 4의자리 * 16 + ...
		 * 행의 인덱스 R을 이진법으로 나타냈을 때
		 * 1의자리 * 2 + 2의자리 * 8 + 4의자리 * 32 + ...
		 * 이런식으로 한 다음 두 값을 합하면 해당 셀의 넘버가 나온다.
		 * 
		 * 예를 들어 1행 4열의 경우
		 * C = 100(2)
		 * R = 001(2) 이다.
		 * 그러면
		 * C의 value합 = 0 * 1 + 0 * 4 + 1 * 16
		 * R의 value합 = 1 * 2 (끝)
		 * 둘의 합계 = 18이다.
		 * 
		 * 이를 구현한 코드가 아래에 있는 코드다.
		 */
		
		// 해당 셀에 표시되는 숫자를 말한다.
		int cellNum = 0;
		// 해당 셀에 표시되는 숫자를 계산하기 위해 더해주는 가치이다.
		// 위의 주석들의 1 2 4 8 16 32에 해당하는 것이다.
		int value = 1;
		while (R > 0 || C > 0) {
			// 열 먼저 시작
			if (C > 0) {
				// 열의 (뒤에서) 1번째 비트를 가져와서 value와 곱해서 더해준다.
				cellNum += value * (C & 1);
				// 열의 인덱스를 1비트 오른쪽으로 당긴다.
				C >>= 1;
			}
			// 열 -> 행으로 넘어가며 가치가 2배가 되니까 2배로 올려준다.
			value <<= 1;
			// 다음으로 행
			if (R > 0) {
				// 행의 인덱스에서 (뒤에서 1번째) 비트를 가져와서 value와 곱해서 더해준다.
				cellNum += value * (R & 1);
				// 행의 인덱스를 1비트 오른쪽으로 당긴다.
				R >>= 1;
			}
			// 행 -> 열으로 넘어가며 가치가 2배가 되니까 2배로 올려준다.
			value <<= 1;
		}
		System.out.print(cellNum);
	}
}