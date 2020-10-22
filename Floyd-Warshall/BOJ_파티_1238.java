package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//플로이드 워셜
//시간복잡도 : N세제곱
//1초제한 시 500이 마지노선
//한번에 모든 지점 -> 모든 지점으로의 최단경로 구할 수 있음
public class BOJ_파티_1238 {
	// INF를 맥스밸류로 안주는 이유 : 맥스밸류 + 맥스밸류는 음수가 나와버리기에 오버플로우 방지목적이다.
	// 다익스트라는 맥스밸류로 둬도 최단경로가 보장되기에 오버플로우 발생X
	static final int INF = 999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		// 정점이 1부터 N이기에 N+1
		int[][] adj = new int[N + 1][N + 1];

		// 인접행렬 세팅 : 시작점과 종점 같은 곳은 0, 나머지는 INF
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					adj[i][j] = 0;
				else
					adj[i][j] = INF;
			}
		}

		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[s][e] = cost;
		}

		// 경유지 출발지 도착지 순
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				// 시작점과 경유지 같을 시 넘기기
				if (i == k)
					continue;
				for (int j = 1; j <= N; j++) {
					// 시작점 도착점 같을시, 경유지와 도착점 같을시 넘기기
					if (i == j || k == j)
						continue;
					if (adj[i][j] > adj[i][k] + adj[k][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, adj[i][X] + adj[X][i]);
		}

		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}

}