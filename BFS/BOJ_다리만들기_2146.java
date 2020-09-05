package bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_다리만들기_2146 {
	static int map[][],N;
	static boolean visited[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Dot{
		int x,y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static ArrayList<ArrayList<Dot>> listArr;
	static ArrayList<Dot> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		listArr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0 || visited[i][j]) continue;
				list = new ArrayList<>();
				bfs(new Dot(i, j));
				listArr.add(list);
			}
		}
		
		int min = Integer.MAX_VALUE;
		int len = listArr.size();
		for (int i = 0; i < len; i++) {
			for (int j = i+1; j < len; j++) {
				if(i==j) continue;
				
				//비교
				ArrayList<Dot> alist = listArr.get(i);
				ArrayList<Dot> blist = listArr.get(j);
				for (int j2 = 0; j2 < alist.size(); j2++) {
					for (int k = 0; k < blist.size(); k++) {
						int dist = Math.abs(alist.get(j2).x - blist.get(k).x) + Math.abs(alist.get(j2).y - blist.get(k).y);
						min = Math.min(min, dist);
					}
				}
			}
		}
		
		bw.write(String.valueOf(min-1));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y] = true;
		list.add(new Dot(dot.x, dot.y));
		
		while(!q.isEmpty()) {
			Dot d = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >= 0 && nx<N && ny >= 0 && ny<N){
					if(!visited[nx][ny] && map[nx][ny]==1) {
						visited[nx][ny]=true;
						list.add(new Dot(nx, ny));
						q.offer(new Dot(nx, ny));
					}
				}
			}
		}
	}

}
