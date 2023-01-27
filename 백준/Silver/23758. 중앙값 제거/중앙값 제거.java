import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        // 입력받는 수를 이진법으로 표현했을 때 몇 자리인지를 저장한다.
        // 1은 1
        // 2는 2
        // 3은 2
        // 4는 3
        // ...
		int[] counts = new int[100];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
            // 입력받는 수를 10진수로 몇 자리인지 / log2를 해주면 2진수로 몇 자리인지 나온다.
            // 예를 들어 10은 이진수로 1010이라 4자리인데,
            // log10 == 1, log2 = 0.301c
            // log10 / log2 = 밑이 2인 log(2)10 = 3.3219... 라서 내림 후 + 1하면 자리수인 4가 나온다.
            // 마찬가지로 8은 이준수로 1000이라 4자리인데,
            // 같은 계산을 하면 값이 3이 나와서 +1하면 자리수인 4가 나온다.
            // 그런데 우리는 (N + 1) / 2 번째 수를 1로만 만든다면, 다음 번 연산에 해당 1을 0으로 만들며
            // 문제의 조건을 충족할 수 있다.
            // 그래서 +1을 한 자리수가 아닌 +1을 하기 전 값을 저장한다.
            // 예를 들어 10은 3.32... 로 1010인데,
            // 2로 1번 나누면 101, 2번 나누면 10, 3번 나누면 1이 되어 1이 된다.
            // 다른 수도 마찬가지로 해당 값(내림)만큼 나누면 1이 된다.
			counts[(int) (Math.log10(Integer.parseInt(st.nextToken())) / Math.log10(2))]++;
		}
        // 결국 최초에 (N + 1) / 2 보다 큰 수는 연산으로 건드려지지 않을 것이므로 limit를 이걸로 둔다.
		int limit = (N + 1) / 2;
        // idx번 나눴을 때 1이 되는 수를 (N + 1) / 2 번째까지만 찾아서 전부 1로 만들기 위한 로직
		int idx = 0;
		long answer = 0;
		while(limit > 0) {
            // idx번 나눴을 때 1이 되는 수가 counts[idx]개 있으니 해당 수를 전부(limit제한 안에서) 1로 만든다.
            // 그러면서 그런 연산의 횟수를 센다.
			answer += Math.min(limit, counts[idx]) * idx;
            // 그런 연산이 끝나면 끝난 개수만큼 빼준다.
			limit -= Math.min(limit, counts[idx]);
            // 연산 필요횟수를 증가시킨다.
			idx++;
		}
        // (N + 1) / 2번째 수가 1이 된 상태이니, 해당 상태에서 1번 더 연산해주면 0이 등장한다.
		System.out.println(answer + 1);
	}
}