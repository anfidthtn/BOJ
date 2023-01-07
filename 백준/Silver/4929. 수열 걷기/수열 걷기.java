import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (A.length == 1) {
				break;
			}
			int[] B = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int Asum = 0;
			int Bsum = 0;
			int idxA = 1;
			int idxB = 1;
			while (idxA < A.length || idxB < B.length) {
				if (idxB == B.length) {
					Asum += A[idxA++];
				} else if (idxA == A.length) {
					Bsum += B[idxB++];
				} else {
					if (A[idxA] == B[idxB]) {
						Asum = Math.max(Asum + A[idxA], Bsum + A[idxA]);
						Bsum = Asum;
						idxA++;
						idxB++;
					} else if (A[idxA] < B[idxB]) {
						Asum += A[idxA++];
					} else {
						Bsum += B[idxB++];
					}
				}
			}
			sb.append(Math.max(Asum, Bsum)).append("\n");
		}
		System.out.print(sb.toString());
	}
}