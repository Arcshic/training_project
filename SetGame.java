/*
Set is a card game played with a pack of 81 unique cards. Each card in the pack has 4 properties,
having one of the three possible values for each property.
Colour – Red (R), Green (G), Blue (B) Number 1, 2, 3,
Shape – Diamond (D), Oval (O), Squiggle (S) Fill – Filled (F), Shaded (S), Empty (E)
This sample contains 3 cards and illustrates all the variations. If it is not in colour, note that the
left card is red, the middle card is green and the right card is blue.

RD1F GO2S BS3E

Below each card is its representation, being the value for its colour, shape, number and fill in that
order. In this problem, cards will always be represented in this manner.
        The 3 cards illustrated form a set. A set is a collection of 3 cards such that for each property all
3 cards are either the same or different. The 3 cards above are different for all 4 properties.
Because each card is unique, at least one property must be different.
Here are some other examples.
RD1F RD1S RD1E SET. Same colour, shape and number,

different fill.

RD1F RD1S RD2E NOT a set. Two are 1s but one is not.

Your task in this problem is to solve the daily set puzzle on the Play Monster website1 which
shows 12 set cards and contains 6 sets. The idea is to identify those 6 sets as quickly as possible.
        Input
Input consists of 12 lines, each line representing a single card. Each card will be represented by
4 characters as described above. The cards have an implied numbering, the first card listed being
card 1, the last card 12.

        1 The website may be found on http://www.setgame.com/set/puzzle

Output
Six lines of output are required, one line to identify each of the sets presented in the puzzle. Each
set is identified by giving the numbers of its 3 cards, in numerical order. The sets are listed in
numerical order.
Sample Input
RO2E
        BD2F
BO3F
        GO3F
GD2S
        RO3F
RS2F
        RO1E
RD3S
        GO1S
BO2S
        BO2E
Output for Sample Input
1 3 10
        3 4 6
        4 8 11
        5 7 12
        6 10 12
        7 8 9
Explanation
The cards as listed: The 6 sets: Explanation:
Same shape
        Diff colour, number, fill
        Same shape, number, fill
Diff colour
Same shape
        Diff colour, number, fill
Same number
        Diff colour, shape, fill
Same shape
        Diff colour, number, fill
Same colour
        Diff shape, number, fill*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cards = new String[12];

        // 读取12张牌
        for (int i = 0; i < 12; i++) {
            cards[i] = scanner.nextLine();
        }

        // 存储找到的集合
        List<String> sets = new ArrayList<>();

        // 检查每一组三张牌
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 11; j++) {
                for (int k = j + 1; k < 12; k++) {
                    if (isSet(cards[i], cards[j], cards[k])) {
                        sets.add((i + 1) + " " + (j + 1) + " " + (k + 1));
                    }
                }
            }
        }

        // 输出找到的集合
        for (String set : sets) {
            System.out.println(set);
        }
    }

    // 检查三张牌是否构成一个集合
    private static boolean isSet(String card1, String card2, String card3) {
        for (int i = 0; i < 4; i++) {
            char a = card1.charAt(i);
            char b = card2.charAt(i);
            char c = card3.charAt(i);

            // 如果某个属性不全相同且不全不同，则这三张牌不构成一个集合
            if (!((a == b && b == c) || (a != b && b != c && a != c))) {
                return false;
            }
        }
        return true;
    }
}