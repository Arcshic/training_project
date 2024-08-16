/*
In most major football tournaments, in knock out games
a penalty shootout is used to determine the winners of a
drawn match.  The most recent FIFA World Cup (men)
was decided by such means.  For the uninitiated, 5
players from each team take alternating penalties, with
the team scoring most goals winning.
For those taking the penalties, what is the best part of the goal to aim at for maximum
chance of success?  In this problem you will attempt to supply the answer.
The goal in a football match is approximately 7.5 metres wide and 2.5 metres high. To
record the part of the goal the ball hit, the goal is divided into ½ metre square grids.
Grids are numbered horizontally from the left of the goal from 0 to 14, and vertically
from the ground upwards from 0 to 4. So, the bottom left hand corner would be grid 
0,0 while the top right corner would be grid 14, 4.
Input
Input will consist of data from a number of penalty kicks.  On the first line will be a
single positive integer, n, being the number of kicks recorded, at least 5, no greater
than 500.  The next n lines will each contain data on 1 kick.
The first character of the kick data will be G for a goal, S for a save or M for a miss.
The grid coordinates follow, horizontal first, with a space between each item. For a
miss, the coordinates will be given as -1 -1 as no part of the goal was hit.
You may assume that there is at least one square with a score greater than 0.
Output
Output will consist of a line giving the best target spot or spots.  This is found by scoring
each grid square with +1 for each goal and -1 for each save. The square or squares
with the highest score will be presented.  Misses are ignored.
Where more than one square has the same best score, they must be presented in
numerical order of horizontal coordinates then numerical order of vertical coordinates.
See the sample outputs for the required wording.  Note the use of “place” and “places”
as appropriate.
Turn over for sample input and output
Sample Input 1
Sample Input 2
        15
G 0 1
G 14 1
S 7 1
G 10 1
G 0 1
S 2 1
S 11 2
M -1 -1
G 7 1
G 7 1
S 10 1
S 5 2
G 0 1
G 6 3
G 7 1
Output for Sample Input 1
Best place 0 1.
Explanation 
3 goals were scored at location 0, 1 with
no saves made.
        Location 7, 1 was also scored from 3
times, but there was also 1 save. 
12
M -1 -1
G 14 1
S 8 2
S 6 3
G 0 1
G 0 2
G 14 1
M -1 -1
G 1 3
S 9 2
G 0 2
G 2 4
Output for Sample Input 2
Best places 0 2, 14 1.
Explanation
Both locations recorded 2 goals with no
saves. The location with the 0 horizontal
value is shown first*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PenaltyShootout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入第一个数字，表示有多少个踢球记录
        int n = scanner.nextInt();
        scanner.nextLine();  // 读取剩余的换行符

        // 创建一个得分数组，表示每个网格的位置
        int[][] grid = new int[15][5];

        // 处理每个踢球记录
        for (int i = 0; i < n; i++) {
            String type = scanner.next(); // G, S, 或 M
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (type.equals("G")) {
                grid[x][y] += 1;
            } else if (type.equals("S")) {
                grid[x][y] -= 1;
            }
        }

        // 找到得分最高的网格
        int maxScore = Integer.MIN_VALUE;
        List<int[]> bestPlaces = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] > maxScore) {
                    maxScore = grid[i][j];
                    bestPlaces.clear();
                    bestPlaces.add(new int[]{i, j});
                } else if (grid[i][j] == maxScore) {
                    bestPlaces.add(new int[]{i, j});
                }
            }
        }

        // 输出结果
        if (bestPlaces.size() == 1) {
            System.out.printf("Best place %d %d.\n", bestPlaces.get(0)[0], bestPlaces.get(0)[1]);
        } else {
            System.out.print("Best places");
            for (int[] place : bestPlaces) {
                System.out.printf(" %d %d,", place[0], place[1]);
            }
            // 移除最后一个逗号，并加上句号
            System.out.println().replace(",", ".");
        }
    }
}