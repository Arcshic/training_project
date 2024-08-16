/*
Organic molecules can be amazingly complex and need a great
variety of shapes and conventions to represent them,
particularly if we wish to depict details of their 3-dimensional
structures. For this problem, we will restrict ourselves to
reasonably simple compounds with only single bonds between
atoms, that can be represented on a simple rectangular grid
with bonds aligned horizontally or vertically. In such molecules,
carbon is bonded to 4 adjacent atoms, nitrogen to 3, oxygen to
2 and hydrogen to 1. These numbers are called the valences of
the atoms.
        Of course, not all such grids represent valid molecules. Your task is to write a
program that will determine whether a given grid could represent one or more valid
molecules, satisfying the valences of all the atoms. Note that the molecule(s) in a
valid grid do not need to exist in the real world.
Input
The first line of the input consists of a pair of integers (r and c, 1 ≤ r, c ≤ 5)
representing the number of rows and columns in the rectangle to follow. The next r
lines contain c characters each, where the characters are chosen from the set {‘H’,
        ‘O’, ‘N’, ‘C’, ‘.’} representing hydrogen, oxygen, nitrogen, carbon and ‘empty’,
respectively.
        Output
Output consists of a single line containing a single word:
Valid if it possible to draw horizontal and/or vertical bonds between
neighbouring atoms so as to satisfy the valences of all the atoms in the grid, or
Invalid if no such set of bonds exists.

Turn over for Sample Input and Sample Output

New Zealand Programming Contest 2023

Sample Input #1
        3 4
HOH.
        NCOH
OO..

Output for Sample Input #1
Valid

        Explanation
A valid bonding is:
H O—H
| |
N—C—O—H
| |
O—O

Sample Input #2
        3 4
HOH.
        NCOH
        OCNH

Output for Sample Input #2
Invalid

Sample Input #3
        2 3
HOH
        HOH

Output for Sample Input #3
Valid
        Explanation
This could represent two water
molecules:
H—O—H
H—O—H

Problem M Molecules part 2 +70 points
Note: This problem is the same as Problems L except that the limits are
1 ≤ r, c ≤ 20. To score the full points, you must submit to both problems. You may
submit the same program to both (but you might find that Problem M requires a
        more refined solution).
Sample Input 1
        4 10
OOOOOOOOOO
        OOOOOOOOOO
OOOONOOOOO
        OOOOOOOOOO
Output for Sample Input 1
Invalid
The sample inputs and outputs from Problem L also apply to this problem.*/

import java.util.Scanner;

public class MoleculeValidator {
    // 原子的价数映射
    private static final int[] valence = new int[256];
    static {
        valence['H'] = 1;
        valence['O'] = 2;
        valence['N'] = 3;
        valence['C'] = 4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取行和列
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.nextLine();  // 读取换行符

        char[][] grid = new char[r][c];

        // 读取网格数据
        for (int i = 0; i < r; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }

        // 遍历网格，检查每个原子是否满足价数要求
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char atom = grid[i][j];
                if (atom == '.') continue;  // 空位跳过

                int bondCount = 0;

                // 检查四个方向的相邻原子
                if (i > 0 && grid[i-1][j] != '.') bondCount++; // 上
                if (i < r-1 && grid[i+1][j] != '.') bondCount++; // 下
                if (j > 0 && grid[i][j-1] != '.') bondCount++; // 左
                if (j < c-1 && grid[i][j+1] != '.') bondCount++; // 右

                // 如果当前原子的键数不符合其价数要求，直接输出 Invalid
                if (bondCount != valence[atom]) {
                    System.out.println("Invalid");
                    return;
                }
            }
        }

        // 所有原子都符合要求，输出 Valid
        System.out.println("Valid");
    }
}
