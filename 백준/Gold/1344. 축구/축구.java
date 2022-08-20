import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[] fact;

	public static void main(String[] args) throws IOException {
		fact = new long[19];
		fact[0] = 1;
		for(int i = 1; i <= 18; i++) {
			fact[i] = fact[i - 1] * i;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		double ApSum = getPsum(A);
		double BpSum = getPsum(B);
		System.out.println(1 - (1 - ApSum) * (1 - BpSum));
	}

	public static double getPsum(int percent) {
		double p = percent / 100.0;
		double pSum = 0;
		for (int goal : new int[] { 2, 3, 5, 7, 11, 13, 17 }) {
			pSum += Math.pow(p, goal) * Math.pow(1 - p, 18 - goal) * getCombP(goal);
		}
		return pSum;
	}
	public static double getCombP(int n) {
		return fact[18] / fact[18 - n] / fact[n];
	}
}