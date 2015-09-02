package com.selau.algorithms.mergesort;

import org.junit.Assert;
import org.junit.Test;

public class MergeSort {

    public void mergesort(final int[] array) {
        if (array == null)
            throw new IllegalArgumentException();

        final int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length -1);
    }

    private void mergesort(final int[] array, final int[] helper, final int low, final int high) {

        if (low < high) {
            final int middle = (low + high) / 2;

            mergesort(array, helper, low, middle);
            mergesort(array, helper, middle +1, high);
            merge(array, helper, low, middle, high);
        }
    }

    private void merge(final int[] array, final int[] helper, final int low, final int middle, final int high) {

        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle +1;
        int current = low;

        while ((helperLeft <= middle) && (helperRight <= high)) {

            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        final int remaining = middle - helperLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current +i] = helper[helperLeft +i];
        }
    }

    @Test
    public void testCases() {

        final int[] arrayA = {1, 2, 3, 4, 5, 6, 7, 8};
        mergesort(arrayA);
        Assert.assertTrue(verifySorted(arrayA));

        final int[] arrayB = {8, 7, 6, 5, 4, 3, 2, 1};
        mergesort(arrayB);
        Assert.assertTrue(verifySorted(arrayB));

        final int[] arrayC = {7, 3, 2, 9, 5, -3, 2, 8, 4};
        mergesort(arrayC);
        Assert.assertTrue(verifySorted(arrayC));

        final int[] arrayD = {};
        mergesort(arrayD);
        Assert.assertTrue(verifySorted(arrayD));

        final int[] arrayE = {5};
        mergesort(arrayE);
        Assert.assertTrue(verifySorted(arrayE));
    }

    private boolean verifySorted(final int[] array) {

        for (int i = 0; i < array.length -1; i++) {
            if (array[i] > array[i +1]) {
                return false;
            }
        }
        return true;
    }

}
