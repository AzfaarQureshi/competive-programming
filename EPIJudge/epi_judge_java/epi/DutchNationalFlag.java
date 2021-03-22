package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void swap(int a, int b, List<Color> A){
    Color a_color = A.get(a);
    Color b_color = A.get(b);
    A.set(a, b_color);
    A.set(b, a_color);
    return;
  }
  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    //ArrayList<Color> small = new ArrayList<Color>();
    //ArrayList<Color> equal = new ArrayList<Color>();
    //ArrayList<Color> big = new ArrayList<Color>();
    //Color pivot = A.get(pivotIndex);
    //for (Color i : A) {
    //  if (i.ordinal() < pivot.ordinal()) small.add(i);
    //  else if(i == pivot) equal.add(i);
    //  else big.add(i);
    //}
    //A.clear();
    //A.addAll(small);
    //A.addAll(equal);
    //A.addAll(big);
    int lo = 0;
    int equal = 0;
    int hi = A.size();
    int pivot = A.get(pivotIndex).ordinal();

    while(equal < hi){
     int cur = A.get(equal).ordinal();
     if(cur < pivot) Collections.swap(A, lo++, equal++);
     else if(cur == pivot) equal++;
     else Collections.swap(A, equal, --hi);
    }
    return;
  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
