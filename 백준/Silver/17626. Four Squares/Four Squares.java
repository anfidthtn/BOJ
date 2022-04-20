import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		for(int i= 1; i*i<=n; i++)dp[i*i] = 1;
		Arrays.fill(dp, -1);
		for(int i = 1; i <= n; i++) {
			count(dp, i);
		}
		System.out.println(dp[n]);
	}
	
	public static int count(int[] dp, int x) { //몇개의 수가 더해졌는지 리턴
		int ret = 100000;
		if(x==0)return 0;
		if(x<0)return 100000;
		if(dp[x]!=-1)return dp[x];
		for(int i = 1; i*i<=x; i++) {
			ret = Math.min(count(dp, x-i*i)+1,ret);
			dp[x] = ret;
		}
		return dp[x];
		
	}
}