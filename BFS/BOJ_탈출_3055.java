package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import BFS.BOJ_탈출_3055.Dot;

public class BOJ_탈출_3055 {
	static int N, M, min;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Deque<Dot> q;
	static class Dot {
		int x, y, cnt;
		char c;
		public Dot(int x, int y, int cnt, char c) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '*') {
					q.offerFirst(new Dot(i, j, 0, '*'));
				} else if (map[i][j] == 'S') {
					q.offerLast(new Dot(i, j, 0, 'S'));
				}
			}
		}

		bfs();
		
		bw.write(String.valueOf((min==0)?"KAKTUS":min));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Dot d = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					//물
					if(d.c == '*') {
						if(map[nx][ny] == 'X' || map[nx][ny] == 'D' || map[nx][ny] == '*') continue;
						map[nx][ny]='*';
						q.offer(new Dot(nx, ny, 0, '*'));
					}
					//고슴도치
					else {
						if(map[nx][ny] == 'X' || map[nx][ny] == '*' || map[nx][ny] == 'S') continue;
						if(map[nx][ny]=='D') {
							min = d.cnt+1;
							return;
						}
						map[nx][ny]='S';
						q.offer(new Dot(nx, ny, d.cnt+1, 'S'));
					}
				}
			}
		}
	}

}
