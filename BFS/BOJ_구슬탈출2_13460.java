package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_구슬탈출2_13460 {
   static int N, M, min = Integer.MAX_VALUE, gx, gy;
   static int[] dx = { -1, 0, 1, 0 };
   static int[] dy = { 0, 1, 0, -1 };

   static class Dot {
      int rx, ry, bx, by, cnt, before;
      char map[][];

      public Dot(int rx, int ry, int bx, int by, int cnt, int before, char[][] map) {
         super();
         this.rx = rx;
         this.ry = ry;
         this.bx = bx;
         this.by = by;
         this.cnt = cnt;
         this.before = before;
         this.map = map;
      }

   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      int rx = 0;
      int ry = 0;
      int bx = 0;
      int by = 0;
      gx = 0;
      gy = 0;

      char map[][] = new char[N][M];
      for (int i = 0; i < N; i++) {
         String s = br.readLine();
         for (int j = 0; j < M; j++) {
            map[i][j] = s.charAt(j);
            if (map[i][j] == 'B') {
               bx = i;
               by = j;
            } else if (map[i][j] == 'R') {
               rx = i;
               ry = j;
            } else if (map[i][j] == 'O') {
               gx = i;
               gy = j;
            }
         }
      }

      bfs(new Dot(rx, ry, bx, by, 0, -1, map));
      bw.write(String.valueOf((min == Integer.MAX_VALUE) ? -1 : min));

      bw.flush();
      br.close();
      bw.close();
   }

   private static void bfs(Dot dot) {
      Queue<Dot> q = new LinkedList<>();
      q.offer(dot);

      while (!q.isEmpty()) {
         Dot d = q.poll();

         // 10번 넘으면 -1 출력
         if(d.cnt >= 10) {
            return;
         }
//         System.out.println("============");
         // 돌릴 네방향
         for (int i = 0; i < 4; i++) {
            // 전방향과 반대방향이나 같은 방향으로는 돌릴필요 없음.
            if (d.before == i || d.before == (i + 2) % 4)
               continue;
            

            int rx = d.rx;
            int ry = d.ry;
            int bx = d.bx;
            int by = d.by;
            
            // red blue 굴러간 횟수 : 이거로 후에 위치 겹칠 시 미루기 기준을 만듬
            int rcnt = 0;
            int bcnt = 0;

            char[][] temp = new char[N][M];

            // 카피
            for (int l = 0; l < temp.length; l++) {
               temp[l] = d.map[l].clone();
            }

            // 방향에 따라 뭐가 더 방향쪽으로 가까운가 -> 뭐부터 움직일지.
            // 그냥 두개 다 움직여놓고 같은 위치면 움직인 방향의 반대로 한칸 무빙

            temp[rx][ry] = '.';
            temp[bx][by] = '.';

            boolean blueOver = false;
            boolean redOver = false;
            // 먼저 RED
            while (true) {
               rx += dx[i];
               ry += dy[i];
               
               // 사방이 #로 막혀있기에 0 N 범위 체크 안해줘도 된다
               if (temp[rx][ry] == '#') {
                  rx -= dx[i];
                  ry -= dy[i];
                  break;
               }

               else if (temp[rx][ry] == 'O') {
                  redOver = true;
                  break;
               }
               
               rcnt++;
            }
            // blue
            while (true) {
               bx += dx[i];
               by += dy[i];

               // 사방이 #로 막혀있기에 0 N 범위 체크 안해줘도 된다
               if (temp[bx][by] == '#') {
                  bx -= dx[i];
                  by -= dy[i];
                  break;
               }

               else if (temp[bx][by] == 'O') {
                  blueOver = true;
                  break;
               }
               bcnt++;

            }

            // 골인 시 종료
            if (redOver && !blueOver) {
               min = d.cnt + 1;
               return;
            } else if (blueOver) {
               continue;
            }

            // 구슬 위치 같으면 미루기
            if(rx==bx && ry==by) {
               if(rcnt > bcnt) {
                  rx -= dx[i];
                  ry -= dy[i];
               }else {
                  bx -= dx[i];
                  by -= dy[i];
               }
            }
            
            temp[rx][ry] = 'R';
            temp[bx][by] = 'B';
            
            q.offer(new Dot(rx, ry, bx, by, d.cnt + 1, i, temp));
//            System.out.println("dir"+i+":"+ rx + " " + ry);

         } // end of for

      }

   }

}