import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int[] a = new int[N];
		int[] b = new int[N];
		int[] c = new int[N];
		int[] d = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
		}
		int[] A = new int[N * N];
		int[] B = new int[N * N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				A[idx] = a[i] + b[j];
				B[idx++] = c[i] + d[j];
			}
		}
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int aIdx = 0;
		int bIdx = N * N - 1;
		
		int tempSum;
		int aStraight;
		int bStraight;
		
		long count = 0;
		
		while(aIdx < N * N && bIdx >= 0) {
			tempSum = A[aIdx] + B[bIdx];
			if (tempSum == 0) {
				aStraight = 0;
				bStraight = 0;

				do{
					aIdx++;
					aStraight++;
				} while (aIdx < N * N && A[aIdx - 1] == A[aIdx]);
				do{
					bIdx--;
					bStraight++;
				} while (bIdx >= 0 && B[bIdx + 1] == B[bIdx]);
				
				count += (long) aStraight * bStraight;
			}
			else {
				if (tempSum < 0) {
					aIdx++;
				}
				else {
					bIdx--;
				}
			}
		}
		
		System.out.println(count);
		
	}
}