#include <vector>

#include "test_framework/generic_test.h"
using std::vector;

/*
 * <310, 315, 275, 295, 260, 270, 290, 230, 255, 250>
 * Find the max profit you can earn by completing buy-sell combo < ith day.
 * Find the max profit you can earn by completing buy-sell combo >= ith day.
 * */
double BuyAndSellStockTwice(const vector<double>& prices) {
  double smallest = prices[0];
  double max_profit = 0;
  vector<double> daily_profit(prices.size(), 0.0); 
  // Calculate max backward looking profit.
  for (int i = 1; i < prices.size(); ++i) {
    max_profit = std::max(max_profit, prices[i] - smallest);
    daily_profit[i] = max_profit;
    smallest = std::min(smallest, prices[i]);
  }
  // Calculate max forward looking profit.
  double largest = *prices.rbegin();
  max_profit = 0;
  double global_max_profit = 0.0;
  for (int i = prices.size()-1; i > 0; --i) {
    largest = std::max(prices[i], largest);
    max_profit = std::max(largest - prices[i], max_profit);
    daily_profit[i] += max_profit;
    global_max_profit = std::max(global_max_profit, daily_profit[i]);
  }
  return global_max_profit;
}

int main(int argc, char* argv[]) {
  std::vector<std::string> args{argv + 1, argv + argc};
  std::vector<std::string> param_names{"prices"};
  return GenericTestMain(args, "buy_and_sell_stock_twice.cc",
                         "buy_and_sell_stock_twice.tsv", &BuyAndSellStockTwice,
                         DefaultComparator{}, param_names);
}
