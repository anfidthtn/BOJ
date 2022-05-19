import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] ways;

	static final int MODNUM = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ways = new int[N * 3][1 << 8];
		System.out.print(getWays(0, 0));
	}

	public static int getWays(int linearIdx, int status) {
		if (linearIdx >= 3 * N) {
			if (linearIdx == 3 * N && status == 0) {
				return 1;
			}
			return 0;
		}
		if (ways[linearIdx][status] != 0) {
			if (ways[linearIdx][status] == -1) {
				return 0;
			}
			return ways[linearIdx][status];
		}

		int result = 0;
		
		if ((status & 1) != 0) {
			// 이미 차 있는 칸이면 그냥 넘어감
			result += getWays(linearIdx + 1, status >> 1);
		}
		else {
			/**
			 * ■ ■
			 * ■ ■
			 */
			if (linearIdx % 3 != 2) {
				int nextStatus = check(status, new int[] {1, 3, 4});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * □ ■
			 * ■ ■
			 * ■
			 */
			if (linearIdx % 3 != 0) {
				int nextStatus = check(status, new int[] {2, 3, 5});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■ ■ □
			 * □ ■ ■
			 */
			if (linearIdx % 3 == 0) {
				int nextStatus = check(status, new int[] {1, 4, 5});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■
			 * ■ ■
			 * □ ■
			 */
			if (linearIdx % 3 != 2) {
				int nextStatus = check(status, new int[] {3, 4, 7});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * □ ■ ■
			 * ■ ■ □
			 */
			if (linearIdx % 3 == 1) {
				int nextStatus = check(status, new int[] {1, 2, 3});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■ ■ ■
			 * □ ■
			 */
			if (linearIdx % 3 == 0) {
				int nextStatus = check(status, new int[] {1, 2, 4});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * □ ■
			 * ■ ■ ■
			 */
			if (linearIdx % 3 == 1) {
				int nextStatus = check(status, new int[] {2, 3, 4});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * □ ■
			 * ■ ■
			 * □ ■
			 */
			if (linearIdx % 3 != 0) {
				int nextStatus = check(status, new int[] {2, 3, 6});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■
			 * ■ ■
			 * ■
			 */
			if (linearIdx % 3 != 2) {
				int nextStatus = check(status, new int[] {3, 4, 6});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * □ □ ■
			 * ■ ■ ■
			 */
			if (linearIdx % 3 == 2) {
				int nextStatus = check(status, new int[] {1, 2, 3});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■ ■ ■
			 * ■
			 */
			if (linearIdx % 3 == 0) {
				int nextStatus = check(status, new int[] {1, 2, 3});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■ ■
			 * □ ■
			 * □ ■
			 */
			if (linearIdx % 3 != 2) {
				int nextStatus = check(status, new int[] {1, 4, 7});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■
			 * ■
			 * ■ ■
			 */
			if (linearIdx % 3 != 2) {
				int nextStatus = check(status, new int[] {3, 6, 7});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■
			 * ■ ■ ■
			 */
			if (linearIdx % 3 == 0) {
				int nextStatus = check(status, new int[] {3, 4, 5});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■ ■ ■
			 * □ □ ■
			 */
			if (linearIdx % 3 == 0) {
				int nextStatus = check(status, new int[] {1, 2, 5});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * ■ ■
			 * ■
			 * ■
			 */
			if (linearIdx % 3 != 2) {
				int nextStatus = check(status, new int[] {1, 3, 6});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
			/**
			 * □ ■
			 * □ ■
			 * ■ ■
			 */
			if (linearIdx % 3 != 0) {
				int nextStatus = check(status, new int[] {3, 5, 6});
				if(nextStatus >= 0) {
					result += getWays(linearIdx + 1, nextStatus);
				}
			}
		}
		if (result == 0) {
			ways[linearIdx][status] = -1;
			return 0;
		} else {
			return ways[linearIdx][status] = result % MODNUM;
		}
	}
	public static int check(int status, int[] checkPoint) {
		for(int check : checkPoint) {
			if ((status & (1 << check)) != 0) {
				return -1;
			}
			else {
				status |= (1 << check);
			}
		}
		return status >> 1;
	}
}