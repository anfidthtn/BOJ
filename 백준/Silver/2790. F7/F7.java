import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] points = new int[N];
		for(int i = 0; i < N ; i++) {
			points[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(points);
		int count = 0;
		int minMaxPoint = 0;
		for(int idx = points.length - 1, point = 1; idx >= 0; idx--, point++) {
			if (points[idx] + N >= minMaxPoint) {
				count++;
			}
			if (minMaxPoint < points[idx] + point) {
				minMaxPoint = points[idx] + point;
			}
		}
		System.out.println(count);
		
	}
}