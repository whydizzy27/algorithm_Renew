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

public class BOJ_모노미노도미노_19235 {
   static int[][] block, downmap, rightmap;
   static int score;

   public static void main(String[] args) throws IOException {
      System.setIn(new FileInputStream("input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int N = Integer.parseInt(br.readLine());
      block = new int[N + 1][3];
      downmap = new int[10][4];
      rightmap = new int[4][10];

      // 도형모양, x y
      for (int i = 1; i <= N; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         block[i][0] = Integer.parseInt(st.nextToken());
         block[i][1] = Integer.parseInt(st.nextToken());
         block[i][2] = Integer.parseInt(st.nextToken());
      }

      // 도미노 시작
      for (int i = 1; i <= N; i++) {
         int type = block[i][0];
         int x = block[i][1];
         int y = block[i][2];


         // 내려보내기
         movedown(type, x, y, i);
         moveright(type, x, y, i);

         // 만약 콤보 있을 시 다 지우고 중력 적용해서 내리기
         while (true) {
            if (!checkdown())
               break;
         }
         while (true) {
            if (!checkright())
               break;
         }
         // 연한 구역에 줄만큼 최하단 컷 & 중력 미적용하고 그대로 내리기
         overdown();
         overright();
         
      } // 반복끝

      // 점수와 남은 벽돌 개수 출력
      bw.write(String.valueOf(score) + "\n");
      bw.write(String.valueOf(count()) + "\n");

      bw.flush();
      br.close();
      bw.close();
   }

   private static int count() {
      int cnt = 0;
      for (int i = 6; i < 10; i++) {
         for (int j = 0; j < 4; j++) {
            if (downmap[i][j] != 0)
               cnt++;
         }
      }
      for (int j = 6; j < 10; j++) {
         for (int i = 0; i < 4; i++) {
            if (rightmap[i][j] != 0)
               cnt++;
         }
      }

      return cnt;
   }

   private static void overdown() {
      int cnt = 0;
      for (int i = 4; i < 6; i++) {
         if (downmap[i][0] != 0 || downmap[i][1] != 0 || downmap[i][2] != 0 || downmap[i][3] != 0) {
            cnt++;
         }
      }

      if (cnt != 0) {

         for (int i = 9; i >= 4; i--) {
            for (int j = 0; j < 4; j++) {
               downmap[i][j] = downmap[i - cnt][j];
            }
         }
      }

   }

   private static void overright() {
      int cnt = 0;
      for (int j = 4; j < 6; j++) {
         if (rightmap[0][j] != 0 || rightmap[1][j] != 0 || rightmap[2][j] != 0 || rightmap[3][j] != 0) {
            cnt++;
         }
      }

      if (cnt != 0) {

         for (int j = 9; j >= 4; j--) {
            for (int i = 0; i < 4; i++) {
               rightmap[i][j] = rightmap[i][j - cnt];
            }
         }
      }
   }

   private static boolean checkdown() {
      // 지운게 있는지
      boolean chk = false;
      for (int i = 6; i < 10; i++) {
         if (downmap[i][0] != 0 && downmap[i][1] != 0 && downmap[i][2] != 0 && downmap[i][3] != 0) {
            score++;
            // 빈칸만들기
            for (int j = 0; j < 4; j++) {
               downmap[i][j] = 0;
            }
            chk = true;
         }
      }

      // 지운게 있다면 싹대 내리기
      if (chk) {
         // 변화없을때까지
         while (true) {
            boolean isend = false;
            for (int i = 8; i >= 4; i--) {
               for (int j = 0; j < 3; j++) {
                  if (downmap[i][j] == 0)
                     continue;
//                  if (downmap[i + 1][j] == 0) {
                  if (downmap[i][j + 1] == downmap[i][j]) {
                     if (downmap[i + 1][j] == 0 && downmap[i + 1][j + 1] == 0) {
                        downmap[i + 1][j] = downmap[i][j];
                        downmap[i + 1][j + 1] = downmap[i][j + 1];
                        downmap[i][j] = 0;
                        downmap[i][j + 1] = 0;
                        isend = true;
                     }
                     j++;
                  } else {
                     if (downmap[i + 1][j] == 0) {
                        downmap[i + 1][j] = downmap[i][j];
                        downmap[i][j] = 0;
                        isend = true;
                     }
                  }
//                  }
               }
               if (downmap[i][3] == 0)
                  continue;
               if (downmap[i + 1][3] == 0 && downmap[i][3] != downmap[i][2]) {
                  downmap[i + 1][3] = downmap[i][3];
                  downmap[i][3] = 0;
                  isend = true;
               }

            }
            if (!isend)
               break;
         }
         return true;
      }
      return false;
   }

   private static boolean checkright() {
      // 지운게 있는지

      boolean chk = false;
      for (int j = 6; j < 10; j++) {
         if (rightmap[0][j] != 0 && rightmap[1][j] != 0 && rightmap[2][j] != 0 && rightmap[3][j] != 0) {
            score++;
            // 빈칸만들기
            for (int i = 0; i < 4; i++) {
               rightmap[i][j] = 0;
            }
            chk = true;
         }
      }

      // 지운게 있다면 싹대 내리기
      if (chk) {

         while (true) {
            boolean isend = false;
            for (int j = 8; j >= 4; j--) {
               for (int i = 0; i < 3; i++) {
                  if (rightmap[i][j] == 0)
                     continue;
//                  if(rightmap[i][j+1]==0) {
                  if (rightmap[i + 1][j] == rightmap[i][j]) {
                     if (rightmap[i][j + 1] == 0 && rightmap[i + 1][j + 1] == 0) {
                        rightmap[i][j + 1] = rightmap[i][j];
                        rightmap[i + 1][j + 1] = rightmap[i + 1][j];
                        rightmap[i][j] = 0;
                        rightmap[i + 1][j] = 0;
                        isend = true;
                     }
                     i++;
                  } else {
                     if (rightmap[i][j + 1] == 0) {
                        rightmap[i][j + 1] = rightmap[i][j];
                        rightmap[i][j] = 0;
                        isend = true;
                     }
                  }
//                  }
               }
               if (rightmap[3][j] == 0)
                  continue;
               if (rightmap[3][j + 1] == 0 && rightmap[3][j] != rightmap[2][j]) {
                  rightmap[3][j + 1] = rightmap[3][j];
                  rightmap[3][j] = 0;
                  isend = true;
               }

            }
            if (!isend)
               break;
         }
         return true;

      }
      return false;
   }

   private static void movedown(int type, int x, int y, int i) {
      int nx = x;
      int ny = y;

      if (type != 2) {
         while (true) {
            nx++;

            if (nx == 10) {
               nx--;
               break;
            }
            if (downmap[nx][ny] != 0) {
               nx--;
               break;
            }
         }
         // 얹을 위치 정함
         downmap[nx][ny] = i;

         if (type == 3) {
            downmap[nx - 1][ny] = i;
         }
      } else {
         while (true) {
            nx++;

            if (nx == 10) {
               nx--;
               break;
            }
            if (downmap[nx][ny] != 0 || downmap[nx][ny + 1] != 0) {
               nx--;
               break;
            }
         }
         // 얹을 위치 정함
         downmap[nx][ny] = i;
         downmap[nx][ny + 1] = i;
      }
   }

   private static void moveright(int type, int x, int y, int i) {
      int nx = x;
      int ny = y;

      if (type != 3) {
         while (true) {
            ny++;

            if (ny == 10) {
               ny--;
               break;
            }
            if (rightmap[nx][ny] != 0) {
               ny--;
               break;
            }
         }
         // 얹을 위치 정함
         rightmap[nx][ny] = i;

         if (type == 2) {
            rightmap[nx][ny - 1] = i;

         }
      } else {
         while (true) {
            ny++;

            if (ny == 10) {
               ny--;
               break;
            }
            if (rightmap[nx][ny] != 0 || rightmap[nx + 1][ny] != 0) {
               ny--;
               break;
            }
         }
         rightmap[nx][ny] = i;
         rightmap[nx + 1][ny] = i;

      }
   }
}