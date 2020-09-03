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


public class BOJ_인구이동_16234 {
	static int N,L,R, map[][],temp[][];
	static boolean check, visited[][];
	static ArrayList<Dot> list;
	static class Dot{
		int x,y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		temp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			check = false;
			visited = new boolean[N][N];
			
			//인구이동
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					list = new ArrayList<>();
					bfs(new Dot(i, j));
				}
			}
			
			if(!check) break;
			
			for (int i = 0; i < N; i++) {
				map[i]=temp[i].clone();
			}
			
			time++;
		}
		
		bw.write(String.valueOf(time));

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y]=true;
		list.add(new Dot(dot.x, dot.y));
		int cnt=1;
		int sum=map[dot.x][dot.y];
		while(!q.isEmpty()) {
			Dot d = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if(!visited[nx][ny]) {
						int dif = Math.abs(map[d.x][d.y] - map[nx][ny]);
						if(dif >= L && dif <= R) {
							visited[nx][ny] = true;
							cnt++;
							sum += map[nx][ny];
							list.add(new Dot(nx, ny));
							q.offer(new Dot(nx, ny));
						}
					}
				}
			}
		}
		
		if(list.size() != 1) {
			check = true;
			int avg = sum / cnt;
			for (Dot dd : list) {
				temp[dd.x][dd.y] = avg;
			}
		}else {
			temp[dot.x][dot.y] = map[dot.x][dot.y];
		}

		
	}

}