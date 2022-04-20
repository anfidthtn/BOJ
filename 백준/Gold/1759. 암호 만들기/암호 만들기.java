import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int L = Integer.parseInt(st.nextToken());
		st.nextToken();

		List<Character> alpha = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			alpha.add(st.nextToken().charAt(0));
		}
		alpha.sort((a, b) -> a < b ? -1 : 1);

		StringBuilder sb = new StringBuilder();
		make(sb, alpha, new int[2], 0, 0, 0, new char[L], L);
		System.out.print(sb.toString());
	}

	public static void make(StringBuilder sb, List<Character> alpha, int[] typeCount, int count, int depth, int isUsed,
			char[] passWord, int L) {
		if (count == L) {
			if (typeCount[0] >= 2 && typeCount[1] >= 1) {
				for (int i = 0; i < L; i++) {
					sb.append(passWord[i]);
				}
				sb.append("\n");
			}
			return;
		}
		if (depth == alpha.size()) {
			return;
		}
		switch(alpha.get(depth)) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			typeCount[1]++;
			break;
		default:
			typeCount[0]++;
			break;
		}
		passWord[count] = alpha.get(depth);
		make(sb, alpha, typeCount, count + 1, depth + 1, isUsed | (1 << depth), passWord, L);
		switch(alpha.get(depth)) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			typeCount[1]--;
			break;
		default:
			typeCount[0]--;
			break;
		}
		make(sb, alpha, typeCount, count, depth + 1, isUsed, passWord, L);
	}
}