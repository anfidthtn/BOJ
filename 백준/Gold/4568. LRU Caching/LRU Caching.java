import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Info{
		int idx;
		boolean status;
		public Info(int idx) {
			this.idx = idx;
			this.status = true;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tCase = 1;; tCase++) {
			st = new StringTokenizer(br.readLine());
			int cacheSize = Integer.parseInt(st.nextToken());
			// 입력 0이면 끝
			if (cacheSize == 0) {
				break;
			}
			// 출력문 출력
			sb.append("Simulation ").append(tCase).append("\n");
			String querys = st.nextToken();
			// 캐시 있는지 여부 비트화
			int cacheCheck = 0;
			// 캐시 우선순위 나타내기
			Queue<Info> cacheOrder = new LinkedList<>();
			// 최종 캐시 상태
			char[] cache = new char[cacheSize];
			// 알파벳별 캐시 정보
			Info[] infos = new Info[26];
			// 캐시에 몇 개 차있는지
			int cacheFill = 0;
			for(int i = 0; i < querys.length(); i++) {
				char query = querys.charAt(i);
				switch(query) {
				case '!':
					// 물음표는 출력
					sb.append(readCache(cache, cacheOrder, cacheFill)).append("\n");
					break;
				default:
					if (check(query, cacheCheck)) {
						// 캐시에 이미 있는 경우는
						// 기존 정보를 없애고(우선순위 때문)
						infos[query - 'A'].status = false;
						// 새 정보를 만들어서 넣음
						infos[query - 'A'] = new Info(infos[query - 'A'].idx);
						// 큐의 젤 뒤에.
						cacheOrder.add(infos[query - 'A']);
					}
					else {
						// 캐시에 없는데
						if (cacheFill < cacheSize) {
							// 캐시가 덜 찼으면
							// 캐시에 그대로 넣음
							cacheCheck ^= 1 << (query - 'A');
							cache[cacheFill] = query;
							// 정보 만들고
							infos[query - 'A'] = new Info(cacheFill);
							// 정보 넣기
							cacheOrder.add(infos[query - 'A']);
							cacheFill++;
						}
						else {
							// 캐시가 다 찼으면 우선순위대로 비워야함
							while (!cacheOrder.peek().status) {
								// 이미 캐시 히트된 경우 넘긴다.
								cacheOrder.poll();
							}
							// 비울 위치정보 갖고온다.
							Info info = cacheOrder.poll();
							// 원래 정보를 빼내고
							cacheCheck ^= 1 << (cache[info.idx] - 'A');
							// 비울 위치에 새 쿼리 넣는다.
							cacheCheck ^= 1 << (query - 'A');
							cache[info.idx] = query;
							// 새 정보 만든다.
							infos[query - 'A'] = new Info(info.idx);
							// 만든 정보를 넣는다.
							cacheOrder.add(infos[query - 'A']);
						}
					}
					break;
				}
			}
		}
		System.out.print(sb.toString());
	}
	public static boolean check(char query, int cacheCheck) {
		if ((cacheCheck & (1 << (query - 'A'))) != 0) {
			return true;
		}
		return false;
	}
	public static String readCache(char[] cache, Queue<Info> cacheOrder, int readCount) {
		StringBuilder sb = new StringBuilder();
		while (readCount > 0) {
			Info info = cacheOrder.poll(); 
			if (info.status) {
				sb.append(cache[info.idx]);
				cacheOrder.add(info);
				readCount--;
			}
		}
		return sb.toString();
	}
}