/*
Inspired by hold ups at traffic lights near his home, Dr
Rush decided to collect data on several sets of lights
and report his findings to the relevant transport
authorities.
Your task here is to process the data for Dr Rush and
pass on your observations. What he particularly wants
to know is what is the longest queue that formed during
        his observations, and what was the longest time it took a vehicle to pass through the
lights.
        Input
The first line will contain 2 positive integers each no less than 10 and no greater than
300. They will represent the observed length in seconds of the green and red phases
respectively during the observations.
The next line will also contain 2 non-negative integers representing the number of cars
in the queue at the start of observations, and how many seconds they had been
waiting.
The next line contains S, a positive integer no greater than 50 giving the number of
sequences that follow. The next S lines will each contain data for 1 sequence. Each
sequence will begin with G for green or R for red, and be followed by a space then a
non-negative number. Lines will alternate between red and green sequences.
For green sequences the number will be the number of cars who cleared the lights
while the green light was on. This will always be no greater than the number of cars
in the queue. For red sequences, it will be the number of cars that joined the queue
while the red light was on.
The cars clearing the green light will be assumed to take 0 seconds to do so. Cars
joining the queue at the red light will be assumed to have waited the full duration of
the red light.
        Continued

        Output
The observations are to be recorded on 2 lines. The first line will give the longest
        queue length, the second line the slowest through time. The format will be:
Longest queue was V vehicles.
Longest through time was M minutes and S seconds.
V is the number of vehicles in the longest queue.
M and S are the minute and second values for the slowest passage.
Note that the text must be contextually correct so “1 vehicle”, “1 minute” and “1
second.” must be used where appropriate.

        Sample Input
20 100
        6 100
        11
G 4
R 6
G 3
R 8
G 5
R 6
G 4
R 5
G 5
R 3
G 5

Explanation
26 cars cleared the lights. Call the cars A to Z for
explanation.
Cars A to F in the queue for 100s so far.
Cars A to D go after 100s.
Cars G to L join, 8 in queue.
Cars E and F leave after 220s, G after 100.
Cars M to T join, 13 in queue.
Cars H to L leave after 220s.
Cars U to Z join, 14 in queue.
Cars M to P leave after 220s.
5 more cars join, 15 in queue.
Cars Q to T leave after 340s, U after 220.
        3 more cars join, 13 in queue.
Cars V to Z leave after 340s. The extra cars are
now ignored.

Output for Sample Input
Longest queue was 15 vehicles.
Longest through time was 5 minutes and 40 seconds.*/

import java.util.Scanner;

public class TrafficLightSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取绿灯和红灯的时长
        int greenTime = scanner.nextInt();
        int redTime = scanner.nextInt();
        scanner.nextLine();  // 读取剩余的换行符

        // 读取初始的排队车辆数和等待时间
        int initialQueue = scanner.nextInt();
        int initialWaitTime = scanner.nextInt();
        scanner.nextLine();

        // 读取序列的数量
        int sequenceCount = scanner.nextInt();
        scanner.nextLine();

        int maxQueue = initialQueue;  // 记录最大排队车辆数
        int maxWaitTime = initialWaitTime;  // 记录最长等待时间
        int currentQueue = initialQueue;  // 当前排队车辆数

        // 逐个处理输入的序列
        for (int i = 0; i < sequenceCount; i++) {
            String[] sequence = scanner.nextLine().split(" ");
            char lightPhase = sequence[0].charAt(0);
            int number = Integer.parseInt(sequence[1]);

            if (lightPhase == 'G') {
                // 绿灯时，车辆通过
                currentQueue -= number;
            } else if (lightPhase == 'R') {
                // 红灯时，车辆加入队伍
                currentQueue += number;
                // 计算这些车辆的等待时间
                int waitTime = redTime;

                // 更新最长等待时间
                if (waitTime > maxWaitTime) {
                    maxWaitTime = waitTime;
                }
            }

            // 更新最大排队车辆数
            if (currentQueue > maxQueue) {
                maxQueue = currentQueue;
            }
        }

        // 将最长等待时间从秒转换为分钟和秒
        int maxWaitMinutes = maxWaitTime / 60;
        int maxWaitSeconds = maxWaitTime % 60;

        // 输出结果
        System.out.println("Longest queue was " + maxQueue + (maxQueue == 1 ? " vehicle." : " vehicles."));
        System.out.println("Longest through time was " + maxWaitMinutes + (maxWaitMinutes == 1 ? " minute " : " minutes ") + maxWaitSeconds + (maxWaitSeconds == 1 ? " second." : " seconds."));
    }
}
