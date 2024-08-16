/*PROBLEM A GIRL SWAPS 3 POINTS
        Part of an exercise at the Whangerei School for
        Young Ladies involves girls standing in a line.
        Their position in a line is given by a number; 1
        means the first girl, 3 the third girl etc.  The
        exercise involves girls swapping places and the
        participants having to remember the new order.

        You are to write a program which accepts as input pairs of numbers which mean you
        should swap the positions of the girls at those two positions. The program will then
        report the new line positions which the participants are trying to remember.

        Input
        Input will consist of the first names of the girls in the line from first to last at the start of
        the exercise. Names will be unique, and will be as defined in the preamble. The names
        will be entered on one line, each name separated by a space. There will be at least 2
        names and a maximum of 26 names.

        The number of pairs (swaps) will be n (a non-negative integer no greater than 1000)
        on the second line. There will follow n lines of pairs of numbers which will be separated
        by a single space.  The two numbers in a pair will always be different to each other
        and will both represent the position of a girl in the line. These are the girls who must
        swap places.

        Output
        Output should be of the names of the girls in their new order in the line.

        Sample Input
        Ruby Jo Fiona Phoebe Angela Sophia
        3
        1 3
        2 6
        4 1

        Output for Sample Input
        Phoebe Sophia Ruby Fiona Angela Jo*/


import java.util.Scanner;

public class GirlSwaps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入第一行：女孩的名字
        String[] names = scanner.nextLine().split(" ");

        // 输入第二行：交换次数
        int n = scanner.nextInt();

        // 执行每次交换
        for (int i = 0; i < n; i++) {
            int pos1 = scanner.nextInt() - 1; // 将输入的索引转换为从0开始
            int pos2 = scanner.nextInt() - 1;

            // 交换位置
            String temp = names[pos1];
            names[pos1] = names[pos2];
            names[pos2] = temp;
        }

        // 输出最终的顺序
        for (String name : names) {
            System.out.print(name + " ");
        }
    }
}
