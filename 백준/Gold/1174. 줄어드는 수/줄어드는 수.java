import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static long[] values = new long[] { //
			1l, //
			10l, //
			100l, //
			1000l, //
			10000l, //
			100000l, //
			1000000l, //
			10000000l, //
			100000000l, //
			1000000000l, //
	};
	static List<Long> answerList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		answerList = new ArrayList<>();
		for(int i = 0; i < 10; i++) {			
			makeList(values[i], 0, 10);
		}
		try {
			System.out.println(answerList.get(N - 1));
		}
		catch (Exception e) {
			// 사실 이따구로 짜면 안 됨.
			System.out.println(-1);
		}
	}
	public static void makeList(long value, long num, int max) {
		if (value == 0) {
			answerList.add(num);
			return;
		}
		for(int i = 0; i < max; i++) {
			makeList(value / 10, num + value * i, i);
		}
	}
}