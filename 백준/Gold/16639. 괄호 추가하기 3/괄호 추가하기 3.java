import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Operator{
		Number before, after;
		char operator;
	}
	static class Number{
		Operator before, after;
		long number;
	}
	
	static int N;
	static int opesLen;
	static String exp;
	static long maxResult;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		exp = br.readLine();
		if (N == 1) {
			System.out.println(exp.charAt(0));
			return;
		}
		opesLen = N / 2;
		maxResult = Integer.MIN_VALUE;
		
		boolean[] visited = new boolean[opesLen];
		int[] order = new int[opesLen];
		permu(visited, order, 0);
		System.out.println(maxResult);
	}
	
	public static void permu(boolean[] visited, int[] order, int nowIdx) {
		if (nowIdx == opesLen) {
			Operator[] opes = new Operator[opesLen];
			setList(opes);
			calculateAndUpdateMax(order, opes);
		}
		for(int i = 0 ; i < opesLen; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			order[nowIdx] = i;
			permu(visited, order, nowIdx + 1);
			visited[i] = false;
		}
	}
	
	public static void setList(Operator[] opes) {
		int opeIdx = -1;
		Number beforeNumber = null;
		for(int i = 0; i < N; i++) {
			if ((i & 1) == 0) {
				Number number = new Number();
				number.number = exp.charAt(i) - '0';
				if (opeIdx >= 0) {
					number.before = opes[opeIdx];
					opes[opeIdx].after = number;
				}
				beforeNumber = number;
			}
			else {
				Operator operator = new Operator();
				operator.operator = exp.charAt(i);
				operator.before = beforeNumber;
				beforeNumber.after = operator;
				opes[++opeIdx] = operator;
			}
		}
	}
	
	public static void calculateAndUpdateMax(int[] order, Operator[] opes) {
		long finalResult = 0;
		for(int i = 0; i < opesLen; i++) {
			Number result = new Number();
			switch (opes[order[i]].operator) {
			case '+':
				result.number = opes[order[i]].before.number + opes[order[i]].after.number;
				break;
			case '-':
				result.number = opes[order[i]].before.number - opes[order[i]].after.number;
				break;
			case '*':
				result.number = opes[order[i]].before.number * opes[order[i]].after.number;
				break;
			}
			result.before = opes[order[i]].before.before;
			result.after = opes[order[i]].after.after;
			if (opes[order[i]].before.before != null) {
				opes[order[i]].before.before.after = result;
			}
			if (opes[order[i]].after.after != null) {
				opes[order[i]].after.after.before = result;
			}
			finalResult = result.number;
		}
		if (finalResult > maxResult) {
			maxResult = finalResult;
		}
	}
}