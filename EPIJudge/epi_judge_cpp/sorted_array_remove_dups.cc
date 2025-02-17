#include <vector>

#include "test_framework/generic_test.h"
#include "test_framework/timed_executor.h"
using std::vector;
/*
 * <2,3,5,0,7,11,11,11,13>
 *        ^
 * <2,3,5,7,0,11,11,11,13>
 *          ^
 * <2,3,5,7,11,0,0,0,13>
 *             ^
 * */

// Returns the number of valid entries after deletion.
int DeleteDuplicates(vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  if (A.size() <= 1) return A.size();

  int frontier = 1;
  int current_val = A[0];
  for (int i = 1; i < A.size(); ++i){
    if (A[i] == current_val) {
      A[i] = 0;
    } else {
      current_val = A[i];
      std::swap(A[frontier++], A[i]);
    }
  }

  return frontier;
}
vector<int> DeleteDuplicatesWrapper(TimedExecutor& executor, vector<int> A) {
  int end = executor.Run([&] { return DeleteDuplicates(&A); });
  A.resize(end);
  return A;
}

int main(int argc, char* argv[]) {
  std::vector<std::string> args{argv + 1, argv + argc};
  std::vector<std::string> param_names{"executor", "A"};
  return GenericTestMain(
      args, "sorted_array_remove_dups.cc", "sorted_array_remove_dups.tsv",
      &DeleteDuplicatesWrapper, DefaultComparator{}, param_names);
}
