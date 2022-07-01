import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] nums = new int[N];
			int sum = 0;
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				sum += nums[i];
			}
			if (sum == 0) {
				sb.append("0\n");
				continue;
			}
			boolean found = false;
			
			int value = sum;
			List<Integer> primeList = new ArrayList<>();
			for(int p = 2; p * p <= sum; p++) {
				while(value % p == 0) {
					primeList.add(p);
					value /= p;
				}
			}
			if (value > 1) {
				primeList.add(value);
			}
			Set<Integer> factorSet = new TreeSet<>();
			getFactor(primeList, factorSet, 0, 1);
			List<Integer> factorList = new ArrayList<>(factorSet);
			factorList.sort(Integer::compareTo);
			
			for(int partValue : factorList) {
				boolean check = true;
				int partSum = 0;
				for(int i = 0; i < N; i++) {
					partSum += nums[i];
					if (partSum == partValue) {
						partSum = 0;
					}
					else if (partSum > partValue) {
						check = false;
						break;
					}
				}
				if (partSum != 0) {
					check = false;
				}
				if (check) {
					sb.append(N - sum / partValue).append("\n");
					found = true;
					break;
				}
			}
			if (!found) {
				sb.append(N - 1).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static void getFactor(List<Integer> primeList, Set<Integer> factorSet, int idx, int value) {
		if (idx == primeList.size()) {
			factorSet.add(value);
			return;
		}
		getFactor(primeList, factorSet, idx + 1, value);
		getFactor(primeList, factorSet, idx + 1, value * primeList.get(idx));
	}
}