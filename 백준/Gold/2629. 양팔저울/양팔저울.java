import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * 입력부분
		 * N : 추 개수
		 * weights : 추 무게정보
		 * M : 구슬 개수
		 * balls : 구슬 무게정보
		 */
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] weights = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int[] balls = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
		}
		
		// 측정이 가능한 무게인지 체크해서 가능한 무게라면 저장해두는 집합
		Set<Integer> checkAbleWeight = new HashSet<>();
		// 각 추별로 추가를 한다.
		for(int i = 0; i < N; i++) {
			// 이번 추를 추가하면서 새롭게 측정가능해진 녀석 + 기존에 있던 무게
			Set<Integer> updateWeight = new HashSet<>();
			// 이번 추를 이용해서 무게가 동일한 것은 측정 가능하다.
			updateWeight.add(weights[i]);
			// 이번 추 이전의 추들로 측정가능했던 무게에 대해서 이번 추가 추가되며 새로 측정가능한 정보를 얻는다.
			for(int weight : checkAbleWeight) {
				// 기존에 측정 가능하던 무게에서 이번 추의 무게를 뺀 것 (측정하려는 물건쪽에 이번 추를 올리기)이 0이상이면 새로 측정가능한 정보를 등록한다.
				if (weight >= weights[i]) {
					updateWeight.add(weight - weights[i]);
				}
				// 이번 추의 무게에서 기존 측정 가능하던 무게를 뺀 것 (기존 측정 가능하던 무게 측정을 이번 추의 반대쪽에 올리기)가 0 이상이면 새로 측정가능한 정보를 등록한다.
				if (weights[i] >= weight) {
					updateWeight.add(weights[i] - weight);
				}
				// 기존 측정가능 무게 + 이번 추의 무게 (측정하려는 물건 반대쪽에 이번 추를 올리기)를 새로 측정할 수 있다.
				updateWeight.add(weight + weights[i]);
				/**
				 * checkAbleWeight에 updateWeight의 원소를 하나하나 넣는 것보다
				 * checkAbleWeight를 순회하는 김에 updateWeight에 넣고, updateWeight의 주소를 checkAbleWeight로 넣어서 
				 * checkAbleWeight가 이번 추를 넣은 상태로 가능한 정보를 모두 저장하게 한다.
				 */
				updateWeight.add(weight);
			}
			// 바로 위 for문 하단에 길게 적어놓은 주석을 보면 된다.
			checkAbleWeight = updateWeight;
		}
		// 조건에 따라 출력
		for(int i = 0; i < M; i++) {
			if(checkAbleWeight.contains(balls[i])) {
				System.out.print("Y ");
			}
			else {
				System.out.print("N ");
			}
		}
	}
}