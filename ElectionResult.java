/*
2023 is an election year in New Zealand, so here is an election
problem for you.
        In this problem you are simply given a list of votes from which to
determine the winner.  The vote list will contain one vote on each
line, “vote” being the name of the candidate for whom the vote was
cast. Your task is to find the name of the winning candidate (the one
who received most votes) and their majority (the number of votes by which they won).
If the top two or more candidates received the same number of votes, a tie must be
declared with the tied candidates listed in alphabetical order.  If only one candidate
        receives votes, then their majority is the number of votes they received.
        Input
The first line of input contains a single positive integer, n, no greater than 1,000. The
next n lines each contain a single name (as defined in the preamble) being the name
of the person for whom a vote was cast.   There will be at least one valid vote.
Output
Output should be the name of the winning candidate, followed by their majority in the
format
<name> won by <majority> votes.
where <name> is the winner’s name and <majority> is their majority.
NOTE If the majority is 1 you must use “vote” not “votes”.
In the case of a tie, the output should be
Tie between <names list>.
where <names list> is a comma separated list of tied candidates in alphabetical order.
Turn over for sample input and output.
Sample Input 1

        10
Ngata
        Li
Brown
        Li
Li
        Ngata
Brown
        Li
Li
        Brown

Output for Sample Input 1

Li won by 2 votes.

        Explanation:

Brown   3 votes
Li  5 votes
Ngata   2 votes

Sample Input 2

        9
Ngata
        Li
Brown
        Li
Li
        Ngata
Brown
        Ngata
Brown


Output for Sample Input 2

Tie between Brown, Li, Ngata.

        Explanation:

Brown   3 votes
Li  3 votes
Ngata   3 votes
*/

import java.util.*;

public class ElectionResult {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取投票数
        scanner.nextLine(); // 消耗换行符

        Map<String, Integer> voteCount = new HashMap<>();

        // 读取每个投票并统计每个候选人的票数
        for (int i = 0; i < n; i++) {
            String candidate = scanner.nextLine();
            voteCount.put(candidate, voteCount.getOrDefault(candidate, 0) + 1);
        }

        // 找到票数最多的候选人
        int maxVotes = Collections.max(voteCount.values());
        List<String> winners = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() == maxVotes) {
                winners.add(entry.getKey());
            }
        }

        Collections.sort(winners); // 按字母顺序排序

        if (winners.size() == 1) {
            int majority = maxVotes - Collections.max(voteCount.values().stream().filter(v -> v != maxVotes).mapToInt(v -> v).max().orElse(0));
            System.out.printf("%s won by %d %s.%n", winners.get(0), majority, majority == 1 ? "vote" : "votes");
        } else {
            System.out.print("Tie between ");
            System.out.println(String.join(", ", winners) + ".");
        }
    }
}