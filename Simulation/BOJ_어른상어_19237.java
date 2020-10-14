package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_어른상어_19237 {

	static int N, M, K, map[][], dir[], priority[][][], sharkloc[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Dot[][] smell;

	static class Dot {
		int no, remain;

		public Dot(int no, int remain) {
			this.no = no;
			this.remain = remain;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 기본 맵
		map = new int[N][N];
		// 냄새 맵
		smell = new Dot[N][N];
		// 상어 현위치
		sharkloc = new int[M + 1][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 미리 냄새 세팅
				if (map[i][j] != 0) {
					smell[i][j] = new Dot(map[i][j], K);
					sharkloc[map[i][j]][0] = i;
					sharkloc[map[i][j]][1] = j;
				}
			}
		}

		dir = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int j = 1; j <= M; j++) {
			dir[j] = Integer.parseInt(st.nextToken()) - 1;
		}

		priority = new int[M + 1][4][4];

		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < 4; j2++) {
					priority[i][j][j2] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(priority[1][0]));
//		}
		
		int time = 0;
		while (true) {
			time++;

			moveshark();
			boolean checkonlyone = downsmell();
			// 1번상어 혼자 남거나 1000 넘겼을 때 break;
			if(time>1000 ) {
				time = -1;
				break;
			}
			if(checkonlyone) {
				break;
			}
//			System.out.println("time"+time);
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(sharkloc[i]));
//			}
//			System.out.println("--------");
		}

		bw.write(String.valueOf(time));

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean downsmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(smell[i][j]==null) continue;
				if(smell[i][j].remain==1) smell[i][j]=null;
				else if(smell[i][j].remain>1) {
					smell[i][j].remain--;
				}
			}
		}
		
		int cnt=0;
		//현재 자리에 새거 박기
		for (int i = 1; i <= M; i++) {
			if(sharkloc[i][0]==-1) continue;
			smell[sharkloc[i][0]][sharkloc[i][1]] = new Dot(i, K);
			cnt++;
//			System.out.println(i);
		}
//		System.out.println("==");
		
		if(cnt==1) {
			return true;
		}
		else
			return false;
	}

	private static void moveshark() {

		//모든 상어에 대해
		for (int s = 1; s <= M; s++) {
			if(sharkloc[s][0]==-1) continue;
			
			//가용 공간 리스트
			LinkedList<Integer> list = new LinkedList<>();
			//자기 냄새 리스트
			LinkedList<Integer> mysmell = new LinkedList<>();
			// 먼저 빈 공간 탐색
			for (int i = 0; i < 4; i++) {
				int nx = sharkloc[s][0] + dx[i];
				int ny = sharkloc[s][1] + dy[i];
				
				if(nx<0 || nx >= N || ny<0 || ny >= N) continue;
				
				if(smell[nx][ny]==null) {
					list.add(i);
				}
				else if(smell[nx][ny].no == s) {
					mysmell.add(i);
				}
			}
			//빈공간o
			int rec = -99;
//			System.out.println(list);
			if(list.size()!=0) {
				for (int i = 0; i < 4; i++) {
					//공간리스트에 우선순위 애가 있는지
					if(list.contains(priority[s][dir[s]][i])){
						rec=priority[s][dir[s]][i];
						break;
					}
				}
			}
			//빈공간x
			else {
				for (int i = 0; i < 4; i++) {
					//공간리스트에 우선순위 애가 있는지
					if(mysmell.contains(priority[s][dir[s]][i])){
						rec=priority[s][dir[s]][i];
						break;
					}
				}
			}
			//넣기
			
			boolean chk = false;
			for (int i = 1; i <= M; i++) {
				if(i==s)continue;
				if(sharkloc[i][0]==-1)continue;
				//좌표 겹치면?
				if(sharkloc[i][0]==sharkloc[s][0]+dx[rec] && sharkloc[i][1]==sharkloc[s][1]+dy[rec]) {
					chk = true;
					//번호 작은게 win
					if(i<s) {
						sharkloc[s][0]=-1;
						break;
					}
					else {
						sharkloc[s][0]+=dx[rec];
						sharkloc[s][1]+=dy[rec];
						sharkloc[i][0]=-1;
						dir[s]=rec;
					}
				}
			}
			if(!chk) {
				sharkloc[s][0]+=dx[rec];
				sharkloc[s][1]+=dy[rec];
				dir[s]=rec;	
			}
			
		}

	}

}