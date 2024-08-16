/*
Angus has recently moved country. He had shipped all his
belongings in wooden cubical boxes supplied by a packing
company in his home country and he now wishes to return the
empty boxes to the company. He can fit smaller boxes inside
        larger boxes, and a set of boxes nested in this way is called a
“unit”. To minimise shipping costs he wants as few units as
possible. One box will fit inside another if the side length of the
inner box is strictly less than the side length of the outer box.
Given that the total number of units has been minimised, Angus further wishes to
minimise the maximum number of boxes in any one unit.
You are to write a program to help Angus determine a suitable nesting of boxes.
        Input
The first line of input is the number of boxes, n ≦ 10000. The second line is a list of n
space-separated integers, which are the side-lengths of all the boxes.
        Output
The first line of output is an integer k, the minimum number of units to be shipped back.
This is followed by k lines, each giving the side-lengths of the boxes comprising one
unit, separated by spaces. Each side-length in the input should appear exactly once
in the output, and the boxes in each unit must fit nested one within another. The order
of the units in the output and the order of side-lengths in each unit does not matter. If
there is more than one solution, any one will do.
Sample Input
6
        1 1 2 2 2 3

Output for Sample Input
3
        1 2
        1 2
        3 2*/

import java.util.*;

public class BoxNesting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] boxes = new int[n];

        for (int i = 0; i < n; i++) {
            boxes[i] = scanner.nextInt();
        }

        // 对箱子按边长排序
        Arrays.sort(boxes);

        // 使用列表保存所有的单位
        List<List<Integer>> units = new ArrayList<>();

        for (int box : boxes) {
            boolean placed = false;
            // 尝试将当前箱子放入已有的单位中
            for (List<Integer> unit : units) {
                if (unit.get(unit.size() - 1) < box) {
                    unit.add(box);
                    placed = true;
                    break;
                }
            }
            // 如果找不到合适的单位，则创建一个新的单位
            if (!placed) {
                List<Integer> newUnit = new ArrayList<>();
                newUnit.add(box);
                units.add(newUnit);
            }
        }

        // 输出结果
        System.out.println(units.size());
        for (List<Integer> unit : units) {
            for (int box : unit) {
                System.out.print(box + " ");
            }
            System.out.println();
        }
    }
}
