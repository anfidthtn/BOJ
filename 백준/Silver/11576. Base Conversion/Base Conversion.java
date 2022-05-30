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
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		int result = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			result *= A;
			result += Integer.parseInt(st.nextToken());
		}
//		System.out.println(result);
		List<Integer> res = new ArrayList<>();
		while (result > 0) {
			res.add(result % B);
			result /= B;
		}
		if (res.size() == 0) {
			System.out.print(0);
		}
		else {
			for(int i = res.size() - 1; i >= 0; i--) {
				System.out.print(res.get(i) + " ");
			}
		}
	}
}