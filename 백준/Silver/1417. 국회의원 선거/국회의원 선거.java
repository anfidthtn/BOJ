import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] votes = new int[N + 1];
		int idx = -1;
		votes[1] = Integer.parseInt(br.readLine());
		int max = votes[1];
		for(int i = 2; i <= N; i++) {
			votes[i] = Integer.parseInt(br.readLine());
			if (votes[i] >= max) {
				max = votes[i];
				idx = i;
			}
		}
		int count = 0;
		while(idx > 0) {
			count++;
			votes[idx]--;
			votes[1]++;
			idx = -1;
			max = votes[1];
			for(int i = 2; i <= N; i++) {
				if(votes[i] >= max) {
					max = votes[i];
					idx = i;
				}
			}
		}
		System.out.println(count);
	}
}