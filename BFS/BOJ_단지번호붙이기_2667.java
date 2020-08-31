package BFS;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_단지번호붙이기_2667 {
	static int N, map[][],cnt=1;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static LinkedList<Integer> list;
	static class Pot{
		int x,y;

		public Pot(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		list = new LinkedList<Integer>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]!=1) continue;
				bfs(new Pot(i, j));
			}
		}
		
		list.sort(null);
		
		bw.write(String.valueOf(cnt-1)+"\n");
		for (int k : list) {
			bw.write(String.valueOf(k)+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs(Pot pot) {
		Queue<Pot> q = new LinkedList<>();
		q.offer(pot);
		int count = 1;
		map[pot.x][pot.y]=++cnt;
		
		while(!q.isEmpty()) {
			Pot p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >=0 && nx < N && ny >=0 && ny < N ) {
					if(map[nx][ny]==1) {
						map[nx][ny]=cnt;
						count++;
						q.offer(new Pot(nx, ny));
					}
				}
			}
		}
		
		list.add(count);
	}

}
