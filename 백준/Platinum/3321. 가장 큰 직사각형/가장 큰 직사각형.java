import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] maps;
	static int[] counts, befores, afters;
	static int ans, sum, size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[2][M];
		counts = new int[N + 2];
		befores = new int[N + 2];
		afters = new int[N + 2];
		counts[0] = M;
		Arrays.fill(befores, -1);
		Arrays.fill(afters, -1);
		befores[0] = 0;
		befores[N + 1] = 0;
		afters[0] = N + 1;
		afters[N + 1] = N + 1;
		ans = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			size = 0;
			for (int j = 0; j < M; j++) {
				maps[(i + 1) % 2][j] = str.charAt(j) - '0';
				maps[(i + 1) % 2][j] += maps[(i + 1) % 2][j] == 1 ? maps[i % 2][j] : 0;
				change(maps[i % 2][j], maps[(i + 1) % 2][j]);
			}
			sum = 0;
			while (size > 0) {
				if (size * M < ans) {
					break;
				}
				sum += counts[size];
				if (size * sum > ans) {
					ans = size * sum;
				}
				size = befores[size];
			}
		}
		System.out.print(ans);
	}

	public static void change(int a, int b) {
		if (a == b) {
			return;
		}
		if (counts[b]++ == 0 && b != 0) {
//			System.out.printf("%d add before\n", b);
//			printArr(counts);
//			printArr(befores);
//			printArr(afters);
			if (counts[b - 1] > 0) {
				befores[b] = b - 1;
				afters[b] = afters[b - 1];
				afters[b - 1] = b;
				befores[afters[b]] = b;
			} else if (counts[b + 1] > 0) {
				afters[b] = b + 1;
				befores[b] = befores[b + 1];
				befores[b + 1] = b;
				afters[befores[b]] = b;
			}
//			System.out.printf("%d add after\n", b);
//			printArr(counts);
//			printArr(befores);
//			printArr(afters);
		}
		if (--counts[a] == 0 && a != 0) {
//			System.out.printf("%d remove before\n", a);
//			printArr(counts);
//			printArr(befores);
//			printArr(afters);
			befores[afters[a]] = befores[a];
			afters[befores[a]] = afters[a];
			befores[a] = -1;
			afters[a] = -1;
			// System.out.printf("%d remove after\n", a);
			// printArr(counts);
//			printArr(befores);
//			printArr(afters);
		}
		if (size < a && counts[a] > 0) {
			size = a;
		}
		if (size < b && counts[b] > 0) {
			size = b;
		}
	}

//	public static void printArr(int[] arr) {
//		for (int i = 0; i < arr.length; i++) {
//			System.out.printf("%2d ", arr[i]);
//		}
//		System.out.println();
//	}
}