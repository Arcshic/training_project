/*You have just moved from Christchurch to study at a
University in a big overseas city. Instead of cycling
everywhere, you now get to walk and take the subway.
Because you don't want to be late for class, you want
to know how long it will take you to get to the
University.
You walk at a speed of 10 km/h. The subway travels at 40 km/h. Assume that you are
lucky, and whenever you arrive at a subway station, a train is there that you can board
immediately. You may get on and off the subway any number of times, and you may
switch between different subway lines if you wish. All subway lines go in both
directions.
        Input
The first line of input contains five space-separated non-negative integers: xh, yh, xu,
yu and n, which are the x, y coordinates of your home, the x, y coordinates of your
University and the number of subway lines, respectively.
The following n lines each contain a sequence of 6 or more integers x1, y1, x2, y2, ...,
which are the x, y coordinates of each stop on the line, in order. The last 2 coordinates
are -1, -1 to mark the end of the line; these should be ignored. You may assume the
subway runs in a straight line between adjacent stops, and the coordinates represent
an integral number of metres.
In total there are at most 200 subway stops in the city and all coordinates are in the
range 0 – 12,000 inclusive.
        Output
Output is the number of minutes it will take you to get to University, rounded to the
        nearest minute, taking the fastest route.

        Sample Input
0 0 10000 1000 2
        0 200 5000 200 7000 200 -1 -1
        2000 600 5000 600 10000 600 -1 -1

Output for Sample Input
21*/

import java.util.*;

public class FastestRoute {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public double distance(Point other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        int xh = scanner.nextInt();
        int yh = scanner.nextInt();
        int xu = scanner.nextInt();
        int yu = scanner.nextInt();
        int n = scanner.nextInt();

        Point home = new Point(xh, yh);
        Point university = new Point(xu, yu);

        List<Point> subwayStations = new ArrayList<>();
        subwayStations.add(home);
        subwayStations.add(university);

        for (int i = 0; i < n; i++) {
            while (true) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x == -1 && y == -1) break;
                subwayStations.add(new Point(x, y));
            }
        }

        int stationCount = subwayStations.size();
        double[][] graph = new double[stationCount][stationCount];
        for (int i = 0; i < stationCount; i++) {
            Arrays.fill(graph[i], Double.MAX_VALUE);
        }

        for (int i = 0; i < stationCount; i++) {
            for (int j = i + 1; j < stationCount; j++) {
                double distance = subwayStations.get(i).distance(subwayStations.get(j));
                double time;
                if (i < 2 || j < 2) {
                    // Home or University to any station, use walking speed
                    time = distance;
                } else {
                    // Between subway stations, use subway speed
                    time = distance / 4.0;
                }
                graph[i][j] = graph[j][i] = time;
            }
        }

        // 处理地铁线路
        int index = 2;
        for (int i = 0; i < n; i++) {
            List<Integer> lineStations = new ArrayList<>();
            while (true) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x == -1 && y == -1) break;
                lineStations.add(index++);
            }

            for (int j = 0; j < lineStations.size() - 1; j++) {
                int s1 = lineStations.get(j);
                int s2 = lineStations.get(j + 1);
                double distance = subwayStations.get(s1).distance(subwayStations.get(s2));
                double time = distance / 4.0;
                graph[s1][s2] = graph[s2][s1] = time;
            }
        }

        // 使用 Dijkstra 算法找到最短路径
        double[] dist = new double[stationCount];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0; // 从家开始

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> dist[i]));
        pq.add(0);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            for (int v = 0; v < stationCount; v++) {
                if (graph[u][v] < Double.MAX_VALUE) {
                    double newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(v);
                    }
                }
            }
        }

        // 输出结果
        int timeInSeconds = (int) Math.round(dist[1]);
        int timeInMinutes = (timeInSeconds + 59) / 60;
        System.out.println(timeInMinutes);
    }
}
