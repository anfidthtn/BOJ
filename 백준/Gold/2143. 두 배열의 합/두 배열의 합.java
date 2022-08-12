import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Map<Integer, Integer> As = new TreeMap<>();
		Map<Integer, Integer> Bs = new TreeMap<>();
		int T = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int[] numsA = new int[A + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= A; i++) {
			numsA[i] = numsA[i - 1] + Integer.parseInt(st.nextToken());
			for(int j = 0; j < i; j++) {
				if (!As.containsKey(numsA[i] - numsA[j])) {
					As.put(numsA[i] - numsA[j], 1);
				}
				else{
					As.put(numsA[i] - numsA[j], As.get(numsA[i] - numsA[j]) + 1);
				}
			}
		}
		int B = Integer.parseInt(br.readLine());
		int[] numsB = new int[B + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= B; i++) {
			numsB[i] = numsB[i - 1] + Integer.parseInt(st.nextToken());
			for(int j = 0; j < i; j++) {
				if (!Bs.containsKey(numsB[i] - numsB[j])) {
					Bs.put(numsB[i] - numsB[j], 1);
				}
				else{
					Bs.put(numsB[i] - numsB[j], Bs.get(numsB[i] - numsB[j]) + 1);
				}
			}
		}
		List<Integer> Alist = new ArrayList<>(As.keySet());
		List<Integer> Blist = new ArrayList<>(Bs.keySet());
		Alist.sort(Integer::compareTo);
		Blist.sort(Integer::compareTo);
		int left = 0;
		int right = Blist.size() - 1;
		long answer = 0;
		while(left < Alist.size() && right >= 0) {
			int temp = Alist.get(left) + Blist.get(right);
			if (temp == T) {
				answer += (long) As.get(Alist.get(left)) * Bs.get(Blist.get(right));
				left++;
				right--;
			}
			else if (temp > T) {
				right--;
			}
			else {
				left++;
			}
		}
		System.out.println(answer);
	}
}