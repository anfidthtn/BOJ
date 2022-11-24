class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int left = 0;
        int right = 1000;
        while(left <= right) {
        	int mid = (left + right) >> 1;
        	// 이분탐색으로 짜야하는데 귀찮아
        	int count = 0;
        	for(int c : citations) {
        		if (c >= mid) {
        			count++;
        		}
        	}
        	if (count >= mid) {
        		answer = mid;
        		left = mid + 1;
        	}
        	else {
        		right = mid - 1;
        	}
        }
        return answer;
    }
}