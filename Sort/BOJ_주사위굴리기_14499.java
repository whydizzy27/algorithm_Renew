package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_주사위굴리기_14499 {
	static int N, M;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 }, row, col;
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int ordercnt = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int[] order = new int[ordercnt];
		for (int i = 0; i < ordercnt; i++) {
			order[i] = Integer.parseInt(st.nextToken()) - 1;
		}
//		System.out.println(Arrays.toString(order));

		// 주사위 가로 세로. 처음엔 둘다 0 0 0 0
		row = new int[4]; // 1 3 6 4
		col = new int[4]; // 1 2 6 5
		// 1 6 자리는 두 배열이 값 공유해야함. 인덱스 0 2

		// 첫 도장
		stamp(sx, sy);

		for (int i = 0; i < ordercnt; i++) {
			sx += dx[order[i]];
			sy += dy[order[i]];
			
			if(sx <0 || sx>=N || sy <0 || sy>=M) {
				sx -= dx[order[i]];
				sy -= dy[order[i]];
				continue;
			}

			turn(order[i]);
			
//			System.out.println(sx +" "+sy);
			stamp(sx, sy);
//			System.out.println("row"+Arrays.toString(row));
//			System.out.println("col"+Arrays.toString(col));
//			System.out.println("---------------");
			bw.write(String.valueOf(row[1])+"\n");
		}

		bw.flush();
		br.close();
		bw.close();

	}

	private static void turn(int dir) {
		//동 서 북 남 순서
		int temp = 0;
		if (dir == 0) {
			temp = row[3];
			for (int i = 3; i >0; i--) {
				row[i]=row[i-1];
			}
			row[0]=temp;
			col[1]=row[1];
			col[3]=row[3];
		} else if (dir == 1) {
			temp = row[0];
			for (int i = 0; i < 3; i++) {
				row[i]=row[i+1];
			}
			row[3]=temp;
			col[1]=row[1];
			col[3]=row[3];
		} else if (dir == 2) {
			temp = col[0];
			for (int i = 0; i < 3; i++) {
				col[i]=col[i+1];
			}
			col[3]=temp;
			row[1]=col[1];
			row[3]=col[3];
		} else {
			temp = col[3];
			for (int i = 3; i >0; i--) {
				col[i]=col[i-1];
			}
			col[0]=temp;
			row[1]=col[1];
			row[3]=col[3];
		}
		
		
	}

	private static void stamp(int x, int y) {
		if (map[x][y] == 0) {
			map[x][y] = row[3];
		} else {
			row[3] = map[x][y];
			col[3] = map[x][y];
			map[x][y] = 0;

		}
	}

}
