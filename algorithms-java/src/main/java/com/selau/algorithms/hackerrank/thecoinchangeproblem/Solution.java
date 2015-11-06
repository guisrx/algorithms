package com.selau.algorithms.hackerrank.thecoinchangeproblem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

/**
 * The Coin Change Problem
 * Problem Statement
 *
 * How many different ways can you make change for an amount, given a list of coins? In this problem, your code will
 * need to efficiently compute the answer.
 *
 * Task
 *
 * Write a program that, given
 *
 * An amount N and types of infinite available coins M
 * A list of M coins - C={C1,C2,C3,..,CM}
 * Prints out how many different ways you can make change from the coins to STDOUT.
 *
 * https://www.hackerrank.com/challenges/coin-change
 *
 * @author selau
 *
 */
public class Solution {


    static Set<List<Integer>> countMakeChangeWays(final int amount, final int[] coins) {
        final Map<Integer, Set<List<Integer>>> cache = new HashMap<Integer, Set<List<Integer>>>();

        return countMakeChangeWays(amount, coins, cache);
    }

    private static Set<List<Integer>> countMakeChangeWays(
            final int amount,
            final int[] coins,
            final Map<Integer, Set<List<Integer>>> cache) {

        final Set<List<Integer>> ways = new HashSet<List<Integer>>();

        if (amount == 0) {
            final Integer[] coinsAmount = new Integer[coins.length];
            Arrays.fill(coinsAmount, Integer.valueOf(0));
            List<Integer> emptySolution = Arrays.asList(coinsAmount);
            ways.add(emptySolution);
            return ways;
        }

        for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
            final int coin = coins[coinIndex];

            if (amount < coin)
                continue;

            final int amountUsingCoin = amount - coin;
            final Set<List<Integer>> newWays;

            if (cache.containsKey(amountUsingCoin)) {
                newWays = cache.get(amountUsingCoin);
            } else {
                newWays = countMakeChangeWays(amountUsingCoin, coins, cache);
                cache.put(amountUsingCoin, newWays);
            }

            for (final List<Integer> way : newWays) {
                final List<Integer> newWay = new ArrayList<Integer>(way);
                final int newCoinAmount = newWay.get(coinIndex).intValue() +1;

                newWay.set(coinIndex,  newCoinAmount);
                ways.add(newWay);
            }
        }
        return ways;
    }

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int amount = scanner.nextInt();
        final int coinsSize = scanner.nextInt();
        final int[] coins = new int[coinsSize];

        for (int coinIndex = 0; coinIndex < coinsSize; coinIndex++) {
            coins[coinIndex] = scanner.nextInt();
        }
        scanner.close();

        final int ways = countMakeChangeWays(amount, coins).size();

        System.out.println(ways);
    }

    @Test
    public void shouldMakeChangeWaysCorrectly() {

        assertEquals(4, countMakeChangeWays(4, new int[] {1, 2, 3}).size());

        assertEquals(5, countMakeChangeWays(10, new int[] {2, 5, 3, 6}).size());

        assertEquals(16694, countMakeChangeWays(75, new int[] {25, 10, 11, 29, 49, 31, 33, 39, 12, 36, 40,
                22, 21, 16, 37, 8, 18, 4, 27, 17, 26, 32, 6, 38, 2, 30, 34}).size());

        assertEquals(127101770, countMakeChangeWays(240, new int[] {23, 20, 35, 42, 19, 3, 34, 9, 28, 38,
                13, 41, 26, 14, 27, 39, 24, 37, 46, 29, 43, 1, 21}).size());
    }

}
