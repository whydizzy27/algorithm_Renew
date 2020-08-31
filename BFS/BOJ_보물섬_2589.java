package BFS;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import BFS.BOJ_보물섬_2589.Dot;

public class BOJ_보물섬_2589 {
	static int N,M;
	static char[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<ArrayList<Dot>> listarr;
	static ArrayList<Dot> list;
	static boolean[][] visited;
	static class Dot{
		int x,y,cnt;

		public Dot(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		//육지 리스트 (2중첩 리스트)
		listarr = new ArrayList<>();
		
		visited = new boolean[N][M];
		//육지 그룹 나누기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]=='W' || visited[i][j]) continue;
				
				//육지 하나 리스트
				list = new ArrayList<>();
				bfs(new Dot(i, j, 0));
				listarr.add(list);
			}
		}
		
		int max = 0;
		for (ArrayList<Dot> ls : listarr) {
			for (Dot d : ls) {
				visited = new boolean[N][M];

				//각각 한 위치씩  bfs해보면서 최대 찾기
				int val = bfs2(d);
				max = Math.max(max, val);
			}
		}
		
		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static int bfs2(Dot dd) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dd);
		int ans = 0;
		visited[dd.x][dd.y] = true;
		while(!q.isEmpty()) {
			Dot d = q.poll();
			if(d.cnt > ans)
				ans = d.cnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M)
					if(map[nx][ny]=='L' && !visited[nx][ny]) {
						visited[nx][ny]=true;
						q.offer(new Dot(nx, ny, d.cnt + 1));
					}
			}
		}
		
		return ans;
	}

	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		list.add(new Dot(dot.x,dot.y,0));
		visited[dot.x][dot.y] = true;
		while(!q.isEmpty()) {
			Dot d = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M)
					if(map[nx][ny]=='L' && !visited[nx][ny]) {
						visited[nx][ny]=true;
						list.add(new Dot(nx, ny, 0));
						q.offer(new Dot(nx, ny, 0));
					}
			}
		}
	}

}
