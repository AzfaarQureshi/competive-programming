package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // TODO - you fill in here.
    ListNode<Integer> merge = new ListNode<Integer>(0,null);
    ListNode<Integer> head = merge;
    while(L1 != null || L2 != null) {
      if(L2 == null) {
        head.next = L1;
        L1 = L1.next;
      } else if (L1 == null) {
        head.next = L2;
        L2 = L2.next;
      } else if(L1.data < L2.data) {
        head.next = L1;
        L1 = L1.next;
      } else {
        head.next = L2;
        L2 = L2.next;
      }
      head = head.next;
    }
    return merge.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
