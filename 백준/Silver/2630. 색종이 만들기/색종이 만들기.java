import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String[][] info;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// N 맥시멈이 64이기때문에 64비트 long형에 저장가능
		info = new String[N][];
		for(int i = 0; i < N; i++) {
			info[i] = br.readLine().split(" ");
		}
		StringBuilder sb = new StringBuilder();
		makeString(sb, 0, 0, N);
		int one = 0;
		int zero = 0;
		for(int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '0') zero++;
			if (sb.charAt(i) == '1') one++;
		}
		System.out.println(zero);
		System.out.println(one);
	}
	public static void makeString(StringBuilder sb, int row, int col, int N) {
		if (N == 1) {
			// 단위까지 내려오면 해당 비트에 따라서 1과 0을  sb에 담아준다.
			// 비트 미는거 long으로 안해서 한 번 틀렸다.. 후.. long..
			if (info[row][col].equals("1")) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			return;
		}
		// 단위(1)가 아니면 괄호를 연다.
		sb.append('(');
		// N / 2로 가로세로 잘랐을 때 2사분면 위치를 글자로 만든다.
		makeString(sb, row, col, N / 2);
		// 1사분면 위치를 글자로 만든다.
		makeString(sb, row, col + N / 2, N / 2);
		// 3사분면 위치를 글자로 만든다.
		makeString(sb, row + N / 2, col, N / 2);
		// 4사분면 위치를 글자로 만든다.
		makeString(sb, row + N / 2, col + N / 2, N / 2);
		// 묶음이 끝났으니 괄호를 닫는다.
		sb.append(')');
		// 괄호를 닫았을 때 (0000)이 있으면 0으로 바꿀 수 있다.
		if (sb.substring(sb.length() - 6).equals("(0000)")) {
			sb.delete(sb.length() - 6, sb.length());
			sb.append(0);
		}
		// (1111)이 있으면 1로 바꿀 수 있다.
		else if (sb.substring(sb.length() - 6).equals("(1111)")) {
			sb.delete(sb.length() - 6, sb.length());
			sb.append(1);
		}
	}
}