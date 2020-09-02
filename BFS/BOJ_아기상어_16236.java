package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_아기상어_16236 {
	static int N, map[][], size=2, cnt, sx, sy, time;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Dot {
		int x, y, time;

		public Dot(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 상어 시작위치 저장
				if (map[i][j] == 9) {
					sx = i;
					sy = j;
					// 상어는 맵에서 지워도된다 위치 기억만으로도 가능
					map[i][j] = 0;
				}
			}
		}
		
		// 벽에 막히는 경우도 생각해야하는가

		while (true) {

			// 더이상 먹을 물고기 없을 시 종료
			if(!bfs(new Dot(sx, sy,0))) break;
			
			// cnt == size일 시 업그레이드 및 리셋
			// 자신의 크기와 같은 수를 먹을 때마다 크기가 1증가 -> size cnt두기
			if(cnt == size) {
				size++;
				cnt=0;
			}
//			System.out.println(sx + " " + sy);
		}

		bw.write(String.valueOf(time));

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean bfs(Dot dot) {
		boolean[][] visited = new boolean[N][N];
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y] = true;
		int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, mintime = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			// 레벨별 구분
			while (qsize-- > 0) {

				Dot d = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = d.x + dx[i];
					int ny = d.y + dy[i];

					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
						if (!visited[nx][ny]) {
							// 자기보다 크기가 크면 지날 수 없음
							if(map[nx][ny] == size || map[nx][ny] == 0) {
								visited[nx][ny] = true;
								q.offer(new Dot(nx, ny, d.time + 1));
							}
							// 크기가 작은 물고기만 먹을 수 있음
							else if(map[nx][ny] < size && map[nx][ny] != 0) {
								visited[nx][ny] = true;

								// 먹는 기준 : 거리가장 가까운 순 && 가장 위 -> 가장 왼쪽
								// 먹으면 빈칸
								if(nx < minx) {
									minx = nx;
									miny = ny;
									mintime = d.time + 1;
								}
								else if(nx ==  minx) {
									if(ny < miny)
										miny = ny;
								}
							}
						}
					}
				}
			}
			
			if(minx != Integer.MAX_VALUE) {
				sx = minx;
				sy = miny;
				cnt++;
				map[minx][miny] = 0;
				time += mintime;
				return true;
			}
		}
		//먹을 게 없음
		return false;

	}
}