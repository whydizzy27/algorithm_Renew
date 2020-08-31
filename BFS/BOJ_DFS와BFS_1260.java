package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_DFS와BFS_1260 {
	static ArrayList<Integer>[] list;
	static BufferedWriter bw;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		
		for (int i = 1; i <= N; i++) {
			list[i].sort(null); //null 두면 오름차순
		}
		visited = new boolean[N+1];
		dfs(start);
		bw.write("\n");

		visited = new boolean[N+1];
		bfs(start);

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(int idx) throws IOException {
		Queue<Integer> q = new LinkedList<>();
		q.offer(idx);
		visited[idx]=true;
		while(!q.isEmpty()) {
			int x = q.poll();
			bw.write(String.valueOf(x)+" ");
			
			for (int t : list[x]) {
				if(visited[t])continue;
				visited[t]=true;
				q.offer(t);
			}
		}
		
	}

	private static void dfs(int idx) throws IOException {
		if(visited[idx]) return;
		
		visited[idx]=true;
		bw.write(String.valueOf(idx)+" ");
		for (int tar : list[idx]) {
			if(visited[tar]) continue;
			dfs(tar);
		}
		
		
	}

}
