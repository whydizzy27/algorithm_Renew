package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
그래프 다익스트라
한 정점 스타트 기준으로 나머지 정점으로의 최단 경로 구할 때 사용
그래프 문제 풀 때 주의점 : 단방향과 양방향에 주의 할 것*/
public class BOJ_최단경로_1753 {
	static class Dot implements Comparable<Dot>{
		int vertex, cost;

		public Dot(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Dot d) {
			return Integer.compare(this.cost, d.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());

		//인접리스트
		ArrayList<Dot>[] list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			//단방향 양방향 구분 잘할 것
			list[s].add(new Dot(e,val));
		}
		
		//시작점과의 최단 거리 배열
		int[] distance = new int[N+1];
		//방문 배열 (효율성 위해서) && 우선순위큐를 쓴다는 가정하에만 방문 배열 사용함. 우선순위큐 미사용시 방문처리하면 안됨.
		boolean[] visited = new boolean[N+1];
		
		//거리배열 최대값으로 setting
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		//시작점을 0으로 둠
		distance[start] = 0;
		
		//우선순위큐를 써야 오퍼 수가 줄어듦.
		PriorityQueue<Dot> pq = new PriorityQueue<>();
		pq.offer(new Dot(start, 0));
		
		while(!pq.isEmpty()) {
			Dot current = pq.poll();
			
			//이미 최단경로로 굳혀진 정점이라면 컨티뉴
			if(visited[current.vertex]) continue;
			//아니라면 방문표시(bfs와 다르게 poll할 때 방문처리)
			visited[current.vertex] = true;
			
//			//만약 문제 조건에 end 정점까지의 최소 거리만 구하는 거라면 기저 조건 이렇게 추가하여 효율성 up
//			//poll한 정점이 도착정점이면 break;
//			//current.cost가 정답이다.
//			if(current.vertex == end){ 
//				break;
//			}
			
			for (Dot dot : list[current.vertex]) {
				int next = dot.vertex;
				int cost = dot.cost;
				
				if(!visited[next] && distance[next] > distance[current.vertex] + cost) {
					distance[next] = distance[current.vertex] + cost;
					pq.offer(new Dot(next, distance[next]));
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			//삼항연산자 사용 시 주의 : 반드시 괄호로 싸줄 것
			bw.write((distance[i]==Integer.MAX_VALUE?"INF":String.valueOf(distance[i])) + "\n");
		}
		

		bw.flush();
		br.close();
		bw.close();
	}
	
}