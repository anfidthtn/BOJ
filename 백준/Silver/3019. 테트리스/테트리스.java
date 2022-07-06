import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	// : ctrl + shift + f 에서 줄바꿈 안 되게 하기
	static final int[][][] blocks = new int[][][] { //
			{}, //
			{ { 0 }, { 0, 0, 0, 0 } }, //
			{ { 0, 0 } }, //
			{ { 1, 1, 0 }, { 0, 1 } }, //
			{ { 0, 1, 1 }, { 1, 0 } }, //
			{ { 0, 0, 0 }, { 0, 1 }, { 0, 1, 0 }, { 1, 0 } }, //
			{ { 0, 0, 0 }, { 0, 0 }, { 0, 2 }, { 1, 0, 0 } }, //
			{ { 0, 0, 0 }, { 0, 0 }, { 2, 0 }, { 0, 0, 1 } } //
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] heights = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int count = 0;
		for (int[] rotatedBlockDiffs : blocks[P]) {
			int needCol = rotatedBlockDiffs.length;
			for (int col = 0; col <= C - needCol; col++) {
				boolean able = true;
				int before = heights[col] + rotatedBlockDiffs[0];
				for(int blockcol = 1; blockcol < needCol; blockcol++) {
					if (before != heights[col + blockcol] + rotatedBlockDiffs[blockcol]) {
						able = false;
						break;
					}
				}
				if (able) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}