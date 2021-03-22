package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // TODO - you fill in here.
    double currBuy = prices.get(0), lowestBuy = prices.get(0), maxDiff = 0.0;

    for(Double currSell: prices){
      lowestBuy = Math.min(currSell, lowestBuy);
      maxDiff = Math.max(currSell-currBuy, maxDiff);
      if( maxDiff < (currSell-lowestBuy) ) currBuy=lowestBuy;
      maxDiff = Math.max(currSell-currBuy, maxDiff);
    }
    return maxDiff;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
