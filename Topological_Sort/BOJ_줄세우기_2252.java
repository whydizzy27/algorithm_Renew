package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*위상정렬
반드시 지켜져야할 순서가 있는 경우 사용
이 문제 같은 경우 위상정렬 케이스 중 하나 뽑는 문제임
ACM Craft로 풀어보기*/
public class BOJ_줄세우기_2252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//인접리스트 배열
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		//자기가 가리킴 받는 횟수 배열
		int[] indegree = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			//indegree 감소용으로 인접리스트 만든 것임
			list[x].add(y);
			indegree[y]++;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		//현재 가리킴 받고 있는 횟수가 0 인 요소들 큐에 오퍼
		for (int i = 1; i <= N; i++) {
			if(indegree[i]==0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current + " ");
			
			for (int next : list[current]) {
				indegree[next]--;
				if(indegree[next]==0)
					q.offer(next);
			}
		}
		

		br.close();
	}
	
}