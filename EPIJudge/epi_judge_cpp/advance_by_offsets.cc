#include <vector>
#include <algorithm>

#include "test_framework/generic_test.h"
using std::vector;
/*
 * <3,3,1,0,2,0,1>
 *              x
 *
 * <3,0,0,0,2,0,1>
 * */
bool CanReachEnd(const vector<int>& max_advance_steps) {
  int64_t max_reachable_index = 0;
  for (int64_t i = 0; i < max_advance_steps.size() && i <= max_reachable_index;
        ++i) {
    max_reachable_index = 
      std::max(max_reachable_index, i + max_advance_steps[i]);
  }

  return max_reachable_index >= max_advance_steps.size() - 1;
}

int main(int argc, char* argv[]) {
  std::vector<std::string> args{argv + 1, argv + argc};
  std::vector<std::string> param_names{"max_advance_steps"};
  return GenericTestMain(args, "advance_by_offsets.cc",
                         "advance_by_offsets.tsv", &CanReachEnd,
                         DefaultComparator{}, param_names);
}
