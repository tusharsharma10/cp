package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumMeetings {

  public static int maximumMeetings(int[] start, int[] end) {
    TimeMeeting[] timeMeetings = new TimeMeeting[start.length];
    for (int i = 0; i < start.length; i++) {
      TimeMeeting t = new TimeMeeting(start[i], end[i]);
      timeMeetings[i] = t;
    }

    Arrays.sort(timeMeetings, new Comparator<TimeMeeting>() {
      @Override
      public int compare(TimeMeeting t1, TimeMeeting t2) {
        if (t1.end > t2.end) {
          return 1;
        } else if (t1.end < t2.end) {
          return -1;
        } else {
          if (t1.start > t2.start) {
            return 1;
          } else {
            return -1;
          }
        }
      }
    });

    int count = 1;
    int lastEnd = timeMeetings[0].end;
    for (int i = 0; i < timeMeetings.length - 1; i++) {
      TimeMeeting t1 = timeMeetings[i];
      TimeMeeting t2 = timeMeetings[i + 1];

      if (t2.start > lastEnd) {
        count++;
        lastEnd = t2.end;
      }
    }

    return count;

  }

  public static void main(String[] args) {
    System.out.println(maximumMeetings(new int[]{0, 7, 1, 4, 8}, new int[]{2, 9, 5, 9, 10}));
  }

}

class TimeMeeting {

  int start;
  int end;

  public TimeMeeting(int s, int e) {
    this.start = s;
    this.end = e;
  }
}