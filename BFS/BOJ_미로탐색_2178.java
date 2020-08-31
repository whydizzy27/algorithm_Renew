package BFS;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_미로탐색_2178 {
	static int N,M, map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pot{
		int x,y,cnt;

		public Pot(int x, int y, int cnt) {
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
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		bfs(new Pot(0, 0, 2));
		
		bw.write(String.valueOf(map[N-1][M-1]-1));
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs(Pot pot) {
		Queue<Pot> q = new LinkedList<>();
		q.offer(pot);
		map[pot.x][pot.y]=2;
		
		while(!q.isEmpty()) {
			Pot p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(map[nx][ny]==1) {
						map[nx][ny]=p.cnt+1;
						q.offer(new Pot(nx, ny, p.cnt+1));
					}
				}
			}
		}
	}

}
