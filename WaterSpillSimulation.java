/*A faucet is pouring water into a long, thin aquarium with
various vertical dividers (walls) in it. The aquarium is initially
empty, and its bottom is perfectly level. How long will it take
for water to spill over its left- or right-most divider?
The faucet is above location x = 0, and the dividers are
located at x = -1, -3, -5, ..., L and 1, 3, 5, ..., R. The dividers
are attached perpendicular to the floor and sides of the
aquarium, and have various heights. The aquarium's length
is greater than R - L, its walls are higher than the highest
divider, and its width is 1 unit everywhere. Water pours from the faucet at a rate of 1
cubic unit per second.
For the purpose of this problem, assume that water is an ideal liquid: it always flows
downhill if it can, and if it cannot it spreads at an equal rate in all horizontal
directions.
        Input
The first line will consist of two integers: L (an odd negative integer) and R (an odd
positive integer). The second line will contain the heights of the dividers (positive
                                                                                     integers, at most 109

) from left to right. There will be at most 1000 dividers.

        Output
Output an integer on a single line, indicating how long it will take, in seconds, before
water starts spilling over either the left or right divider.
Sample Input 1
        -1 1
        3 5

Output for Sample Input 1
        6

Sample Input 2
        -3 3
        4 3 2 1

Output for Sample Input 2
        6

Sample Input 3
        -3 5
        1 2 2 1 1

Output for Sample Input 3
        8*/

import java.util.Scanner;

public class WaterSpillSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取 L 和 R
        int L = scanner.nextInt();
        int R = scanner.nextInt();

        // 计算隔板的数量
        int numDividers = (R - L) / 2 + 1;
        int[] heights = new int[numDividers];
        int[] waterLevel = new int[numDividers];

        // 读取隔板高度
        for (int i = 0; i < numDividers; i++) {
            heights[i] = scanner.nextInt();
            waterLevel[i] = 0;
        }

        int time = 0;

        // 模拟倒水过程
        while (true) {
            time++;
            int pos = numDividers / 2; // 从中间开始倒水

            // 水从中间向左右扩散
            while (pos > 0 && waterLevel[pos] >= waterLevel[pos - 1]) {
                pos--;
            }
            while (pos < numDividers - 1 && waterLevel[pos] >= waterLevel[pos + 1]) {
                pos++;
            }

            // 更新水位
            waterLevel[pos]++;

            // 检查是否溢出
            if (waterLevel[0] > heights[0] || waterLevel[numDividers - 1] > heights[numDividers - 1]) {
                System.out.println(time);
                break;
            }
        }
    }
}
