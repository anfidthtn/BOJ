class Solution {
	public int solution(String s) {
		int answer = 0;
		char start = s.charAt(0);
		// start와 같은 문자 나온 개수
		int startCount = 1;
		// start와 다른 문자 나온 개수
		int elseCount = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == start) {
				startCount++;
			}
			else {
				// 다른 경우
				if(startCount == ++elseCount) {
					// start문자와 start문자 아닌 거 개수 같아지면 분리
					answer++;
					if (i == s.length() - 1) {
						return answer;
					}
					start = s.charAt(i + 1);
					startCount = 1;
					elseCount = 0;
					i++;
				}
			}
		}
		return answer + 1;
	}
}