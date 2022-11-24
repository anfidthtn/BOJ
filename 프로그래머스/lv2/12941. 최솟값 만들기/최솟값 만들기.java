import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        int[] AA = Arrays.copyOf(A, A.length);
        int[] BB = Arrays.copyOf(B, B.length);
        Arrays.sort(AA);
        Arrays.sort(BB);
        int answer = 0;
        for(int i = 0; i < A.length; i++) {
        	answer += AA[i] * BB[A.length - i - 1]; 
        }
        return answer;
    }
}