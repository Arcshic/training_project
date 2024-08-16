/*The country of Lakalia has many lovely lakes. The people of Lakalia have a
government department dedicated to the care and maintenance of lakes; one
important function of the lake department is counting lakes and keeping track of any
creation or loss. For example when there is a volcanic eruption, lava may fill in lakes
or parts of lakes. The Lakalia lake department has asked you to write a program
which, given information on existing lakes and volcanic lava coverage, will calculate
the number of lakes remaining after a lava flow.
Lakes and lava areas are defined in 2D by polygonal outlines. For example, the
image on the left below is a map of an area of Lakalia with five lakes. The image on
the right shows the same area partly covered by a large lava flow (shaded/pink
        area). The heavy black outlines show the new lake shapes – again five lakes, but
one of the originals has been lost (centre) and another divided in two (bottom left).

Input
The first line of input has the original number of lakes: N. Then for each lake there is
a series of lines, the first of which has the number of vertices V for the lake's
polygon, followed by V lines describing the coordinates of each vertex, with each line
having the x and y coordinates (in km) as two space-separated integers. After the
lake data there is a further polygon description (number and coordinate pairs) for the
lava flow. The bounds are 1 ≤ N ≤ 10; 3 ≤ V ≤ 500; 0 ≤ x, y ≤ 2000.
Output
One line holding a single integer – being the number of distinct lakes remaining after
the lava flow.
        Notes
You can assume that the input satisfies the following constraints.
All polygons are closed shapes (closed by an edge between their first and last
        coordinates).
The polygons are simple polygons, meaning they are not self-intersecting and
do not have ‘spikes’: i.e. no edge directly folds back on the last.
Lake polygons are distinct (non-overlapping) and non-touching.
All polygon edges have length at least 1 km.

New Zealand Programming Contest 2023

All intersections between lava and lake polygons are crossings – i.e. lava and
lake edges never run along together (no collinear overlap) and polygons never
just touch at a point.
The remaining lakes after the lava flow are never very small – always at least
one square kilometre.
        Submission restrictions
You are not permitted to use graphics or geometry libraries (even if such libraries are
        available in the judging environment). The judges will manually check submissions
and remove submissions that do so.
        Sample Input
1
        4
        100 100
        100 200
        200 200
        200 100
        5
        160 50
        160 150
        225 150
        140 225
        140 50*/

import java.util.*;

public class LakeCounter {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Polygon {
        List<Point> vertices;

        Polygon(List<Point> vertices) {
            this.vertices = vertices;
        }

        double area() {
            int n = vertices.size();
            double area = 0;
            for (int i = 0; i < n; i++) {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get((i + 1) % n);
                area += p1.x * p2.y - p2.x * p1.y;
            }
            return Math.abs(area) / 2.0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<Polygon> lakes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int v = scanner.nextInt();
            List<Point> vertices = new ArrayList<>();
            for (int j = 0; j < v; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                vertices.add(new Point(x, y));
            }
            lakes.add(new Polygon(vertices));
        }

        int lavaVerticesCount = scanner.nextInt();
        List<Point> lavaVertices = new ArrayList<>();
        for (int i = 0; i < lavaVerticesCount; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            lavaVertices.add(new Point(x, y));
        }

        Polygon lavaPolygon = new Polygon(lavaVertices);

        // 剪裁后的湖泊计数
        int remainingLakes = 0;

        for (Polygon lake : lakes) {
            Polygon clippedLake = clipPolygon(lake, lavaPolygon);
            if (clippedLake != null && clippedLake.area() >= 1.0) {
                remainingLakes++;
            }
        }

        System.out.println(remainingLakes);
    }

    // 剪裁多边形的示例函数
    private static Polygon clipPolygon(Polygon lake, Polygon lavaPolygon) {
        // 这里只是一个示例。实际上，你需要实现多边形剪裁算法
        return lake; // 假设lavaPolygon没有影响lake，返回原多边形
    }
}
