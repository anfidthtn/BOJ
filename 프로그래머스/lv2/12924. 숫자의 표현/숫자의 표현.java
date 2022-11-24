class Solution {
	public int solution(int n) {
		int answer = 0;
		int len = 1;
		while (true) {
			if (n - (len - 1) * len / 2 <= 0) {
				break;
			}
			if ((n - (len - 1) * len / 2) % len == 0) {
				answer++;
			}
			len++;
		}
		return answer;
	}
}