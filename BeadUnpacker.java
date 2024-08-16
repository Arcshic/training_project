/*
Your friend Allie designs necklaces from beads with various shapes
and she wishes to share her design with another friend, Bella, who
makes the necklaces.  Allie uses a compact representation.
Each shape is described with a single bead letter (S for star, T for
        triangle, R for rectangle and L for line).
Instead of just writing down the sequence of beads in the necklace,
Allie uses the following rules:
        10 POINTS
• if there are several identical beads following each other, write the number of beads,
followed by the bead letter 
• if there is a repeating sequence of beads, write the number of repetitions followed
by the repeated sequence in parentheses  
• otherwise, just write the bead letter
        For example, the following necklace:
may be described as:  S3(TR)3SL
You receive such a compact description from Allie and need to unpack it for Bella. She
also needs to know how many of each type of bead is required.
        Input
A bead sequence following the rules as described above. There will be at least 1 bead
in the sequence.
The number of beads or the number of repetitions (in front of parentheses) in the bead
sequence is guaranteed to be less than 100. There will not be any nested repeating
sequences such as 3(S2(TR)L).
Output
Two output lines are required.  The first contains the unpacked sequence of beads in
the order given in the input.
The second line has 4 integers, space separated, representing the total number of
Stars, Triangles, Rectangles and Lines needed, in that order.
Turn over for sample input and output
Acknowledgement to Sébastien Combéfis, Belgium, whose 2021 Bebras problem inspired 
this problem.
        Sample Input
S3(TR)3SL
Output for Sample Input
STRTRTRSSSL  
4 3 3 1
Explanation
        The sequence, unpacked, contains 4 Stars, 3
Triangles, 3 Rectangles and 1 Line*/

import java.util.Scanner;

public class BeadUnpacker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String compressed = scanner.nextLine();

        StringBuilder unpacked = new StringBuilder();
        int[] beadCounts = new int[4]; // S, T, R, L

        for (int i = 0; i < compressed.length(); i++) {
            char currentChar = compressed.charAt(i);

            if (Character.isDigit(currentChar)) {
                int number = currentChar - '0';
                while (i + 1 < compressed.length() && Character.isDigit(compressed.charAt(i + 1))) {
                    number = number * 10 + (compressed.charAt(i + 1) - '0');
                    i++;
                }

                if (compressed.charAt(i + 1) == '(') {
                    int j = i + 2;
                    StringBuilder sequence = new StringBuilder();
                    while (compressed.charAt(j) != ')') {
                        sequence.append(compressed.charAt(j));
                        j++;
                    }
                    for (int k = 0; k < number; k++) {
                        unpacked.append(sequence);
                        countBeads(sequence.toString(), beadCounts);
                    }
                    i = j;
                } else {
                    char beadType = compressed.charAt(i + 1);
                    for (int k = 0; k < number; k++) {
                        unpacked.append(beadType);
                        countSingleBead(beadType, beadCounts);
                    }
                    i++;
                }
            } else {
                unpacked.append(currentChar);
                countSingleBead(currentChar, beadCounts);
            }
        }

        // 输出解压缩后的序列
        System.out.println(unpacked.toString());

        // 输出每种珠子的数量
        for (int count : beadCounts) {
            System.out.print(count + " ");
        }
    }

    private static void countBeads(String sequence, int[] beadCounts) {
        for (char c : sequence.toCharArray()) {
            countSingleBead(c, beadCounts);
        }
    }

    private static void countSingleBead(char bead, int[] beadCounts) {
        switch (bead) {
            case 'S':
                beadCounts[0]++;
                break;
            case 'T':
                beadCounts[1]++;
                break;
            case 'R':
                beadCounts[2]++;
                break;
            case 'L':
                beadCounts[3]++;
                break;
        }
    }
}