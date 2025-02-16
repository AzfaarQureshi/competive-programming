#include <vector>

#include "test_framework/generic_test.h"
using std::vector;
/*
 * 1,2,9 => 130
 * 1,9,9 => 200
 * 9,9,9 => 1000
 * 1,2,3 => 124
 */
vector<int> PlusOne(vector<int> A) {
  bool carry = true;
  for (auto it = A.rbegin(); it != A.rend() && carry; ++it) {
    *it += 1;
    if (*it > 9) {
      *it = 0;
    } else {
      carry = false;
    }
  }
  if (carry) {
    A.push_back(1);
    std::swap(A[0], A[A.size()-1]);
  }
  return A;
}

int main(int argc, char* argv[]) {
  std::vector<std::string> args{argv + 1, argv + argc};
  std::vector<std::string> param_names{"A"};
  return GenericTestMain(args, "int_as_array_increment.cc",
                         "int_as_array_increment.tsv", &PlusOne,
                         DefaultComparator{}, param_names);
}
