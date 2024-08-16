/*Recent flooding has damaged a bridge over a
major river. The transport agency, Waka Kotahi,
has taken the unusual action of reducing the
bridge to one-way traffic, and have arranged for
a car ferry to take cars in the other direction.
The ferry can take n cars at a time across the river in t minutes, and returns again after
a further t minutes. The ferry operator is pondering what the optimal strategy would be
if she knew all the car arrival times for the day in advance. Her goal is to finish for the
day as early as possible with the secondary goal of making the minimum number of
trips for that completion time. She can choose to leave on each crossing at any time
        she chooses, but obviously she can only take cars that have arrived up to that time.
The times that individual cars have to wait is of no concern to her in this little
algorithmic challenge.
Input
The first line of input contains three space-separate integers n, t and m where n and t
are as above and m is the total number of cars arriving for the day. The following m
lines each contain a single integer, the arrival time of a car in minutes from the start of
the day. You may assume 0 < n, t, m < 1440. Car arrival times are in non-decreasing
order.
        Output
The output is two space-separated integers: the minimum time in minutes from
the start of the day at which she can drop off the last car of the day and the minimum
number of trips to achieve that time. Each departure with a load of cars counts as one
trip.
Sample Input 1
        2 10 10
        0
        10
        20
        30
        40
        50
        60
        70
        80
        90

Output for Sample Input 1
        100 5*/

import java.util.*;

public class CarFerry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取n, t, m
        int n = scanner.nextInt(); // 每次渡轮最多载车数
        int t = scanner.nextInt(); // 每次渡河时间
        int m = scanner.nextInt(); // 总共的车数

        int[] arrivalTimes = new int[m];
        for (int i = 0; i < m; i++) {
            arrivalTimes[i] = scanner.nextInt();
        }

        int trips = 0;
        int currentTime = 0;

        for (int i = 0; i < m; i += n) {
            trips++;
            currentTime = Math.max(currentTime, arrivalTimes[i]) + t * 2;
        }

        // 最后一次渡河只需要过去，不需要返回，所以减去一次回程的时间
        currentTime -= t;

        System.out.println(currentTime + " " + trips);
    }
}
