class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int[] weightCount = new int[limit + 10];
        for(int p : people) {
        	weightCount[p]++;
        }
        for(int first = limit; first >= 40; first--) {
        	for(int second = limit - first; second >= 40 && weightCount[first] > 0; second--) {
        		if (first != second) {
        			int boat = Math.min(weightCount[first], weightCount[second]);
        			weightCount[first] -= boat;
        			weightCount[second] -= boat;
        			answer += boat;
        		}
        		else {
        			answer += weightCount[first] / 2;
        			weightCount[first] -= weightCount[first] / 2 * 2;
        		}
        	}
        	answer += weightCount[first];
        	weightCount[first] = 0;
        }
        return answer;
    }
}