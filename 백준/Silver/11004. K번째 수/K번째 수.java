import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> nums = new ArrayList<>(N);
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		nums.sort(Integer::compareTo);
		System.out.println(nums.get(K - 1));
	}
}