import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int[] mex;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		mex = new int[N + 1];
		mex[0] = 0;
		mex[1] = 0;
		for(int i = 2; i <= N; i++) {
			mex[i] = -1;
		}
		if(getMex(N) > 0) {
			System.out.println(1);
		}
		else {
			System.out.println(2);
		}
	}
	public static int getMex(int num) {
		if (mex[num] >= 0) {
			return mex[num];
		}
		Set<Integer> number = new HashSet<>(); 
		for(int i = 0; i < num / 2; i++) {
			number.add(getMex(i) ^ getMex(num - i - 2));
		}
		mex[num] = -1;
		while(number.contains(++mex[num]));
		return mex[num];
	}
}