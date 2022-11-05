import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.Comparator;

import java.util.PriorityQueue;

import java.util.StringTokenizer;

public class Main {

   static class Point {

      int row;

      int col;

      public Point(int row, int col) {

         this.row = row;

         this.col = col;

      }

      public boolean equal(Point point) {

         return equal(point.row, point.col);

      }

      public boolean equal(int row, int col) {

         return this.row == row && this.col == col;

      }

   }

   static class Node {

      /**

       * 0 : knight

       * 1 : bishop

       * 2 : rook

       */

      int statue;

      int change;

      int time;

      Point point;

      public Node(int statue, int change, int time, Point point) {

         this.statue = statue;

         this.change = change;

         this.time = time;

         this.point = point;

      }

   }

   static class MinNode {

      int minTime;

      int minChange;

      public MinNode() {

         this.minTime = 1 << 20;

         this.minChange = 1 << 20;

      }

   }

   static int N;

   static int[][] map;

   static Point[] points;

   static Point[][] mapPoints;

   static MinNode[][] minNodes;

   

   static int[] kDr = { -2, -1, 1, 2, 2, 1, -1, -2};

   static int[] kDc = { 1, 2, 2, 1, -1, -2, -2, -1};

   static int[] bDr = {-1, 1, 1, -1};

   static int[] bDc = { 1, 1, -1, -1};

   static int[] rDr = {-1, 0, 1, 0};

   static int[] rDc = {0, -1, 0, 1};

   public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      N = Integer.parseInt(br.readLine());

      map = new int[N][N];

      mapPoints = new Point[N][N];

      points = new Point[N * N + 1];

      minNodes = new MinNode[N * N + 1][3];

      for (int i = 0; i < N; i++) {

         StringTokenizer st = new StringTokenizer(br.readLine());

         for (int j = 0; j < N; j++) {

            map[i][j] = Integer.parseInt(st.nextToken());

            points[map[i][j]] = new Point(i, j);

            mapPoints[i][j] = points[map[i][j]];

         }

      }

      for (int i = 0; i < 3; i++) {

         minNodes[1][i] = new MinNode();

         minNodes[1][i].minChange = 0;

         minNodes[1][i].minTime = 0;

      }

      for (int i = 1; i < N * N; i++) {

         Point start = points[i];

         PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

            @Override

            public int compare(Node o1, Node o2) {

               if (o1.time == o2.time) {

                  return o1.change - o2.change;

               }

               return o1.time - o2.time;

            }

         });

         MinNode[][][] visited = new MinNode[N][N][3];

         for (int r = 0; r < N; r++) {

            for (int c = 0; c < N; c++) {

               for (int s = 0; s < 3; s++) {

                  visited[r][c][s] = new MinNode();

               }

            }

         }

         for (int s = 0; s < 3; s++) {

            visited[start.row][start.col][s] = minNodes[i][s];

            pq.add(new Node(s, minNodes[i][s].minChange, minNodes[i][s].minTime, start));

         }

         while (!pq.isEmpty()) {

            Node now = pq.poll();

            for(int nextS = 0; nextS < 3; nextS++) {

               if (nextS == now.statue) {

                  continue;

               }

               MinNode nextVisit = visited[now.point.row][now.point.col][nextS]; 

               if (nextVisit.minTime <= now.time) {

                  continue;

               }

               if (nextVisit.minTime == now.time + 1 && nextVisit.minChange <= now.change + 1) {

                  continue;

               }

               nextVisit.minTime = now.time + 1;

               nextVisit.minChange = now.change + 1;

               pq.add(new Node(nextS, nextVisit.minChange, nextVisit.minTime, now.point));

            }

            switch(now.statue) {

            case 0:

               // knight

               for(int d = 0; d < 8; d++) {

                  int nextRow = now.point.row + kDr[d];

                  int nextCol = now.point.col + kDc[d];

                  pqProcessing(pq, visited, now, nextRow, nextCol);

               }

               break;

            case 1:

               for(int k = 0; k < 4; k++) {

                  for (int d = 1; d <= N; d++) {

                     int nextRow = now.point.row + d * bDr[k];

                     int nextCol = now.point.col + d * bDc[k];

                     if (!boundaryCheck(nextRow, nextCol)) {

                        break;

                     }

                     pqProcessing(pq, visited, now, nextRow, nextCol);

                  }

               }

               break;

            case 2:

               for(int k = 0; k < 4; k++) {

                  for (int d = 1; d <= N; d++) {

                     int nextRow = now.point.row + d * rDr[k];

                     int nextCol = now.point.col + d * rDc[k];

                     if (!boundaryCheck(nextRow, nextCol)) {

                        break;

                     }

                     pqProcessing(pq, visited, now, nextRow, nextCol);

                  }

               }

               break;

            }

         }

         minNodes[i + 1] = visited[points[i + 1].row][points[i + 1].col];

      }

      int ansTime = 1 << 20;

      int ansChange = 1 << 20;

      for(int s = 0; s < 3; s++) {

         if (minNodes[N * N][s].minTime > ansTime) {

            continue;

         }

         if (minNodes[N * N][s].minTime == ansTime && minNodes[N * N][s].minChange > ansChange) {

            continue;

         }

         ansTime = minNodes[N * N][s].minTime;

         ansChange = minNodes[N * N][s].minChange;

      }

      System.out.print(ansTime + " " + ansChange);

   }

   public static void pqProcessing(PriorityQueue<Node> pq, MinNode[][][] visited, Node now, int nextRow, int nextCol) {

      if (!boundaryCheck(nextRow, nextCol)) {

         return;

      }

      MinNode nextVisit = visited[nextRow][nextCol][now.statue];

      if (nextVisit.minTime < now.time + 1) {

         return;

      }

      if (nextVisit.minTime == now.time + 1 && nextVisit.minChange <= now.change) {

         return;

      }

      nextVisit.minChange = now.change;

      nextVisit.minTime = now.time + 1;

      pq.add(new Node(now.statue, nextVisit.minChange, nextVisit.minTime, mapPoints[nextRow][nextCol]));

   }

   public static boolean boundaryCheck(Point point) {

      return boundaryCheck(point.row, point.col);

   }

   public static boolean boundaryCheck(int row, int col) {

      return 0 <= row && row < N && 0 <= col && col < N;

   }

}