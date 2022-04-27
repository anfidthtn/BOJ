class Solution {
	public int solution(int n, int k) {
		String number = Integer.toString(n, k);
		int count = 0;
		long value = 0;
		for (int i = 0; i <= number.length(); i++) {
			if (i == number.length() || number.charAt(i) == '0') {
				if (value > 1 && check(value)) {
					count++;
				}
				value = 0;
				if (i == number.length()) {
					break;
				}
			}
			else {
				value *= 10;
				value += number.charAt(i) - '0';
			}
		}
		return count;
	}
	public boolean check(long num) {
		for(long i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}