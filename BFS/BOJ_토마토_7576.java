package BFS;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_토마토_7576 {
	static int N,M, map[][],day;
	static Queue<Dot> q;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Dot{
		int x,y,day;

		public Dot(int x, int y,int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				//토마토 위치
				if(map[i][j]==1)
					q.offer(new Dot(i, j,0));
			}
		}
		
		bfs();
		
		//검사
		L:for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0) {
					day = -1;
					break L;
				}
			}
		}
		bw.write(String.valueOf(day));

		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Dot d = q.poll();
			day = d.day;
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M)
					if(map[nx][ny]==0) {
						map[nx][ny]=d.day+1;
						q.offer(new Dot(nx, ny, d.day+1));
					}
			}
			
		}
		
	}
	

}
