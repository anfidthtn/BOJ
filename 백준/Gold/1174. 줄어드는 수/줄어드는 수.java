import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Long> answerList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		answerList = new ArrayList<>();
		makeList(1, 0, 0);
		answerList.sort(Long::compareTo);
		try {
			System.out.println(answerList.get(N - 1));
		}
		catch (Exception e) {
			// 사실 이따구로 짜면 안 됨.
			System.out.println(-1);
		}
	}
	public static void makeList(long value, long num, int digit) {
		for(int i = digit; i < 10; i++) {
			answerList.add(num + value * i);
			makeList(value * 10, num + value * i, i + 1);
		}
	}
}