/*When James Wardle invented his popular word game, Wordle, he
was actually producing a modern version of a much older game.
So old that William Shakespeare and Jane Austen may well have
played it with their friends.
The game requires 2 players.  The chooser sets a word of 4 letters
and the guesser tries to guess it. After each guess, the chooser
tells the guesser how many bulls and how many cows their guess
has scored.
A bull is scored for every correct letter in the guess that is in the correct position in the
word.  A cow is scored for any other correct letter that is in the wrong place. If either
word has repeated letters the rule is that each letter can only count towards the score
once, and bulls are counted before cows. For example, if the set word is NEED, and
the guess  was EVEN, the score would be 1 bull (for the E in position 3) and 2 cows
        (for the N and the other E).  The V would not score.  If the set word is LOPE and the
guess is EVEN, the score would be just 1 cow, for the first E.
        Input
Each line of input  consists of a single 4 letter word, all letters being in upper case.
The first line will be the secret word selected by the chooser.
Each other line will be a word put forward by the guesser.  Input will end with a correct
guess, or with the guesser entering GIVE UP.
        Output
For each guess in the input, apart from GIVE UP, a single line will be output.  The line
will consist of the guess, followed by a space, followed by the number of bulls awarded,
followed by a space, followed by the number of cows awarded. As shown in the
sample, bulls and cows must be bull and cow if there is only 1.
The final line of output will be either
The word was guessed in N.
or
The word was not guessed. Answer: <word>.
N in the first version is the number of guesses made, an integer.
<word> in the second version is the secret word.
Turn over for sample input and output.
Sample Input 1
LOVE
        FISH
VAIN
        LANE
VILE
        LOVE
Output for Sample Input 1
FISH Score 0 bulls and 0 cows.
VAIN Score 0 bulls and 1 cow.
LANE Score 2 bulls and 0 cows.
VILE Score 1 bull and 2 cows.
LOVE Score 4 bulls and 0 cows.
The word was guessed in 5.
Sample Input 2
QUAG
        HOST
PINS
        MELD
BARK
        BLUB
DRAB
        BARE
GIVE UP
Output for Sample Input 2
HOST Score 0 bulls and 0 cows.
PINS Score 0 bulls and 0 cows.
MELD Score 0 bulls and 0 cows.
BARK Score 0 bulls and 1 cow.
BLUB Score 0 bulls and 1 cow.
DRAB Score 1 bull and 0 cows.
BARE Score 0 bulls and 1 cow.
The word was not guessed. Answer: QUAG.*/

import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String secretWord = scanner.nextLine(); // 读取秘密单词
        int guessCount = 0; // 记录猜测次数
        boolean wordGuessed = false; // 标记是否猜中单词

        while (true) {
            String guess = scanner.nextLine(); // 读取猜测单词

            if (guess.equals("GIVE UP")) {
                break; // 如果输入为 "GIVE UP"，结束游戏
            }

            guessCount++;

            int bulls = 0;
            int cows = 0;
            boolean[] usedInSecret = new boolean[4];
            boolean[] usedInGuess = new boolean[4];

            // 首先计算 bulls
            for (int i = 0; i < 4; i++) {
                if (guess.charAt(i) == secretWord.charAt(i)) {
                    bulls++;
                    usedInSecret[i] = true;
                    usedInGuess[i] = true;
                }
            }

            // 然后计算 cows
            for (int i = 0; i < 4; i++) {
                if (!usedInGuess[i]) {
                    for (int j = 0; j < 4; j++) {
                        if (!usedInSecret[j] && guess.charAt(i) == secretWord.charAt(j)) {
                            cows++;
                            usedInSecret[j] = true;
                            usedInGuess[i] = true;
                            break;
                        }
                    }
                }
            }

            // 打印猜测结果
            System.out.printf("%s Score %d bull%s and %d cow%s.%n",
                    guess, bulls, bulls == 1 ? "" : "s", cows, cows == 1 ? "" : "s");

            // 检查是否猜中了单词
            if (bulls == 4) {
                wordGuessed = true;
                break;
            }
        }

        // 打印最终结果
        if (wordGuessed) {
            System.out.printf("The word was guessed in %d.%n", guessCount);
        } else {
            System.out.printf("The word was not guessed. Answer: %s.%n", secretWord);
        }
    }
}
