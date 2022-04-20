import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static boolean check(StringBuilder sb, String pattern) {
		int sbLen = sb.length();
		String subsb = sb.substring(sbLen - pattern.length());
		if (subsb.equals(pattern)) {
			sb.delete(sbLen - pattern.length(), sbLen);
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		String pattern = br.readLine();
		int sLen = s.length();
		int pLen = pattern.length();
		char pLast = pattern.charAt(pLen - 1);
		int nowInsertIdx = 0;
		fullIn : while (nowInsertIdx < sLen) {
			// 최초 or 폭발 문자열 끝 문자 발견으로 문자열 폭발 시도 실패 후에는 패턴의 길이 - 1만큼은 문자열 폭발이 일어나지 않으므로 추가로 받을 수 있다.
			for (int i = 0; i < pLen - 1; i++) {
				sb.append(s.charAt(nowInsertIdx++));
				if (nowInsertIdx == sLen) break fullIn;
			}
			while (nowInsertIdx < sLen) {
				// 폭발 문자열의 끝 문자를 받기 전까지 계속 넣어준다.
				sb.append(s.charAt(nowInsertIdx++));
				if (sb.charAt(sb.length() - 1) == pLast) {
					if (sb.length() < pLen) {
						// 패턴의 끝문자가 나왔으나, 있는 문자 수가 패턴보다 더 적으면 이후로 패턴 길이 - 1개만큼 프리패스 가능
						continue fullIn;
					}
					// 폭발문자열의 끝 문자를 발견했다면 폭발문자열과 겹쳤는지 확인한다.
					if (!check(sb, pattern)) {
						// 만약 폭발문자열을 발견하지 못했다면 해당 지점으로부터 패턴의 길이 - 1만큼은 프리패스로 받을 수 있다.
						continue fullIn;
					}
				}
			}
		}
		if (sb.length() == 0) {
			System.out.println("FRULA");
		}
		else {
			System.out.println(sb.toString());
		}
	}
}
