package com.sujeet.Interview;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoorDash_Calendar {
    /*
Google Calendar, Outlook, iCal has been banned from your company! So an intrepid engineer has decided to roll their own implementation. Unfortunately one major missing feature is the ability to find out what time slots are free for a particular individual.

Given a list of time blocks where a particular person is already booked/busy, a start and end time to search between, a minimum duration to search for, find all the blocks of time that a person is free for a potential meeting that will last the aforementioned duration.

Given: start_time, end_time, duration, meetings_list -> suggested_meeting_times

Let's assume we abstract the representation of times as simple integers, so a valid time is any valid integer supported by your environment. Here is an example input:

meetings_list: [3,20], [-2, 0], [0,2], [16,17], [19,23], [30,40], [27, 33]
start_time: -5
end_time: 27
min_duration: 2
expected answer:

free_time: [-5, -2], [23,27]
Feel free to represent the meetings however you would like, i.e. List of Lists, Lists of Objects etc.
*/

    public static List<Pair<Integer, Integer>> getFreeTimes(List<Pair<Integer, Integer>> meetings, int start_time, int end_time, int minSlot) {
        Collections.sort(meetings, (a, b) -> a.getKey() - b.getKey());

        int maxEndTimeYet = Integer.MIN_VALUE;
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        if (meetings.size() == 0 && end_time - start_time >= minSlot) {
            return Arrays.asList(new Pair<>(start_time, end_time));
        }
        for (Pair<Integer, Integer> meeting : meetings) {
            if (meeting.getKey() < start_time) {
                maxEndTimeYet = Math.max(maxEndTimeYet, meeting.getValue());
                continue;
            }
            if (meeting.getKey() > end_time) {
                break;
            }
            int previousStart = Math.max(maxEndTimeYet, start_time);
            if (previousStart > meeting.getKey()) {
                maxEndTimeYet = Math.max(maxEndTimeYet, meeting.getValue());
                continue;
            }

            int previousGap = meeting.getKey() - previousStart;
            if (previousGap >= minSlot) {
                result.add(new Pair<Integer, Integer>(previousStart, meeting.getKey()));
            }
            maxEndTimeYet = Math.max(maxEndTimeYet, meeting.getValue());
        }
        if (maxEndTimeYet <= start_time) {
            int start = Math.max(maxEndTimeYet, start_time);
            if (end_time - start >= minSlot) {
                result.add(new Pair<>(start, end_time));
            }
        }

        return result;
    }

    // private void addIfValid(List<Pair<Integer, Integer>>) {
    //     return;
    // }
    public static void main(String[] args) {
        //[3,20], [-2, 0], [0,2], [16,17], [19,23], [30,40], [27, 33]
        List<Pair<Integer, Integer>> meetings = Arrays.asList(
                new Pair<>(-100, 100),
                new Pair<>(-2, 0),
                new Pair<>(0, 2),
                new Pair<>(16, 17),
                new Pair<>(19, 23),
                new Pair<>(30, 40),
                new Pair<>(27, 33)
        );
        System.out.print(getFreeTimes(meetings, -4, -2, 2));
    }
}
