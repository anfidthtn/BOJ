import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder("int a;\nint *ptr = &a;\n");
		if (N >= 2) {
			sb.append("int **ptr2 = &ptr;\n");
		}
		for (int i = 3; i <= N; i++) {
			addChar(i);
		}
		System.out.print(sb);
	}

	public static void addChar(int num) {
		sb.append("int ");
		for (int i = 0; i < num; i++) {
			sb.append("*");
		}
		sb.append("ptr").append(num).append(" = &ptr").append(num - 1).append(";\n");
	}
}