package BFS;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import BFS.BOJ_적록색약_10026.Dot;

public class BOJ_벽부수고이동하기_2206 {
	static int N,M,map[][],min=Integer.MAX_VALUE, visited[][];
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static class Dot{
		int x,y,cnt;
		boolean drill;
		public Dot(int x, int y, boolean drill, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.drill = drill;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		bfs(new Dot(0, 0, true ,1));
		bw.write(String.valueOf((min == Integer.MAX_VALUE)? -1 : min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y] = 1;
		
		while(!q.isEmpty()) {
			Dot d = q.poll();
			
			if(d.x == N-1 && d.y == M-1) {
				min = d.cnt;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M ) {
					if(d.drill && visited[nx][ny]!=1 ) {
						if( map[nx][ny]==0) {
							q.offer(new Dot(nx, ny, true, d.cnt+1));
						}
						else {
							q.offer(new Dot(nx, ny, false, d.cnt+1));
						}
						visited[nx][ny]=1;
					}
					else if(!d.drill && visited[nx][ny]==0 && map[nx][ny]==0) {
						visited[nx][ny]=2;
						q.offer(new Dot(nx, ny, false, d.cnt+1));

					}
				}
					
			}
		}
		
	}

}
