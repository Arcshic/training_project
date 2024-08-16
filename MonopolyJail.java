/*
According to Wikipedia, “Monopoly is a multi-player economics
        themed board game”.  It goes on to say: “In the game, players
        roll two dice to move around the game board, buying and trading
        properties and developing them with houses and hotels. Players
        collect rent from their opponents, aiming to drive them into
        bankruptcy.”
        There is a jail on the board where players may find themselves sent for a number of
        reasons, most obviously for landing on the “Go to Jail” square. There are also 2 packs
        of cards which players have to obey when they land on appropriate squares.  Both
        packs contain a “Go to Jail” card, but also a “Get out of jail free” card. A player may
        keep a “Get out of jail free” card until it is needed.
        This problem assumes a player is in jail, and has to get out.  There are 3 ways:
        1. Throw doubles, that is where both dice show the same number.
        2. After 3 turns use a “Get out of jail free” card.
        3. After 3 turns, pay a $50 fine.
        This problem assumes that the player will wait until they have played 3 turns before
        using their card or paying the fine.
        Once out, the player moves forward the number of squares indicated by adding the
        dice from the last turn.
        Input
        The first line of input will be the dice scores for the first throw, on a single line and
        separated by a space.
        If doubles are not thrown, a second similar line will appear, and similarly a third line if
        doubles are still not thrown.
        If all 3 lines do not show doubles, a fourth line of input will show if the player has a
        “Get out of jail free” card (lower case y or n).
        Output
        Output will consist of a single line with the following format:
        <Reason>.  Move forwards n squares.
        <Reason> is one of
        Doubles
        Use card $50 fine
        n is the sum of the last 2 dice thrown.
        Sample Input 1

        2 6
        4 5
        1 2
        n

        Sample Input 2

        1 3
        4 4

        Output for Sample Input 1

        $50 fine. Move forwards 3 squares.

        Output for Sample Input 2

        Doubles. Move forwards 8 squares.

        Sample Input 3

        4 2
        3 1
        6 3
        y


        Output for Sample Input 3

        Use card. Move forwards 9 squares.
*/


import java.util.Scanner;

public class MonopolyJail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dice1 = new int[2];
        int[] dice2 = new int[2];
        int[] dice3 = new int[2];
        String getOutCard = "";

        // 读取第一行输入（第一次掷骰子）
        dice1[0] = scanner.nextInt();
        dice1[1] = scanner.nextInt();

        if (dice1[0] == dice1[1]) {
            System.out.println("Doubles. Move forwards " + (dice1[0] + dice1[1]) + " squares.");
            return;
        }

        // 读取第二行输入（第二次掷骰子）
        dice2[0] = scanner.nextInt();
        dice2[1] = scanner.nextInt();

        if (dice2[0] == dice2[1]) {
            System.out.println("Doubles. Move forwards " + (dice2[0] + dice2[1]) + " squares.");
            return;
        }

        // 读取第三行输入（第三次掷骰子）
        dice3[0] = scanner.nextInt();
        dice3[1] = scanner.nextInt();

        if (dice3[0] == dice3[1]) {
            System.out.println("Doubles. Move forwards " + (dice3[0] + dice3[1]) + " squares.");
            return;
        }

        // 如果没有在前三次掷骰子中掷出相同的点数，读取第四行输入
        getOutCard = scanner.next();

        if (getOutCard.equals("y")) {
            System.out.println("Use card. Move forwards " + (dice3[0] + dice3[1]) + " squares.");
        } else {
            System.out.println("$50 fine. Move forwards " + (dice3[0] + dice3[1]) + " squares.");
        }
    }
}
