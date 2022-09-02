import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int left;
		int right;
		int num;
	}

	static Node[][] map;
	static int[] line;
	static int[][] originalMap;

	static int answer;
	static int originalBallCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		originalMap = new int[8][9];
		originalBallCount = 0;
		for (int i = 7; i >= 1; i--) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 7; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
				originalBallCount += originalMap[i][j] > 0 ? 1 : 0;
			}
		}
		originalBallCount++;
		answer = originalBallCount;
		map = new Node[8][9];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 9; j++) {
				map[i][j] = new Node();
			}
		}
		line = new int[9];
		int ballNum = Integer.parseInt(br.readLine());
		for (int i = 1; i <= 7; i++) {
			int ballCount = originalBallCount;
			mapInit(i, ballNum);
			int res = 0;
			do {
				makeInfo();
				res = eraseBall();
				ballCount -= res;
			} while (res > 0);
			answer = Math.min(answer, ballCount);
		}
		System.out.println(answer);
	}

	public static int eraseBall() {
		int result = 0;
		int[] temp = new int[9];
		for (int j = 1; j <= 7; j++) {
			for (int i = 1; i <= line[j]; i++) {
				if (map[i][j].num > 0) {
					if (map[i][j].num == line[j]) {
						map[i][j].num = 0;
						temp[j]++;
						result++;
					} else if (map[i][j].num == map[i][j - map[i][j].left + 1].right) {
						map[i][j].num = 0;
						temp[j]++;
						result++;
					}
				}
			}
		}
		for (int j = 1; j <= 7; j++) {
			int bottom = 1;
			int top = 2;
			while (bottom < line[j] && top <= line[j]) {
				if (map[bottom][j].num > 0) {
					bottom++;
					top++;
				} else if (map[top][j].num == 0) {
					top++;
				} else {
					map[bottom][j].num = map[top][j].num;
					map[top][j].num = 0;
					bottom++;
					top++;
				}
			}
			line[j] -= temp[j];
		}
		return result;
	}

	public static void makeInfo() {
		for (int j = 1; j <= 7; j++) {
			for (int i = 1; i <= 7; i++) {
				if (map[i][j].num == 0) {
					map[i][j].left = 0;
				} else {
					map[i][j].left = map[i][j - 1].left + 1;
				}
			}
		}
		for (int j = 7; j >= 1; j--) {
			for (int i = 1; i <= 7; i++) {
				if (map[i][j].num == 0) {
					map[i][j].right = 0;
				} else {
					map[i][j].right = map[i][j + 1].right + 1;
				}
			}
		}
	}

	public static void mapInit(int idx, int ballNum) {
		Arrays.fill(line, 0);
		for (int i = 1; i <= 7; i++) {
			for (int j = 1; j <= 7; j++) {
				map[i][j].num = originalMap[i][j];
				if (map[i][j].num > 0) {
					line[j] = i;
				}
			}
		}
		line[idx]++;
		map[line[idx]][idx].num = ballNum;
	}
}