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


public class BOJ_연구소_14502 {
	static int N,M, map[][],copy[][],max,remain,cnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<Dot> virlist;
	static class Dot{
		int x,y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map =new int[N][M];
		copy =new int[N][M];
		
		virlist = new ArrayList<Dot>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2)
					virlist.add(new Dot(i, j));
				else if(map[i][j]==1)
					continue;
				else
					remain++;
			}
		}
//		System.out.println(remain);
		remain-=3;
		combination(0,0);
		bw.write(String.valueOf(max));

		bw.flush();
		br.close();
		bw.close();
	}
	
	//조합 벽 3개 0->1
	private static void combination(int idx, int cur) {
		if(idx==3) {
			copymap();
			cnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(copy[i][j]!=2) continue;
					bfs(new Dot(i, j));
				}
			}
//			System.out.println(remain-cnt);
			max = Math.max(max, remain-cnt);
			return;
		}
		
		
		for (int i = cur; i < N*M; i++) {
			
			int r = i / M;
			int c = i % M;
			
			if(map[r][c]!=0) continue;
			map[r][c]=1;
			combination(idx+1, i+1);
			map[r][c]=0;
		}
	}
	
	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		copy[dot.x][dot.y]=3;
		
		while(!q.isEmpty()) {
			Dot d = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >=0 && nx<N &&ny >=0 && ny<M && copy[nx][ny]==0) {
					copy[nx][ny]=3;
					cnt++;
					q.offer(new Dot(nx, ny));
				}
					
			}
		}
		
	}

	//카피 맵
	private static void copymap() {
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
	}
	
	

}
