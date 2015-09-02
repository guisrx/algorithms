package com.selau.algorithms.quicksort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSort {


    public void quicksort(final int[] array) {
        if (array == null)
            throw new IllegalArgumentException();

        if (array.length == 0)
            return;

        quicksort(array, 0, array.length -1);
    }

    private void quicksort(final int[] array, final int left, final int right) {
        final int index = partition(array, left, right);

        if (left < index -1)
            quicksort(array, left, index -1);

        if (index < right)
            quicksort(array, index, right);
    }

    private int partition(final int[] array, int left, int right) {
        final int pivot = array[(left + right) / 2];

        while (left <= right) {

            while (array[left] < pivot)
                left++;

            while (array[right] > pivot)
                right--;

            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private void swap(final int[] array, final int left, final int right) {
        final int aux = array[left];

        array[left] = array[right];
        array[right] = aux;
    }

    @Test
    public void testCases() {

        final int[] arrayA = {1, 2, 3, 4, 5, 6, 7, 8};
        quicksort(arrayA);
        Assert.assertTrue(verifySorted(arrayA));

        final int[] arrayB = {8, 7, 6, 5, 4, 3, 2, 1};
        quicksort(arrayB);
        Assert.assertTrue(verifySorted(arrayB));

        final int[] arrayC = {7, 3, 2, 9, 5, -3, 2, 8, 4};
        quicksort(arrayC);
        Assert.assertTrue(verifySorted(arrayC));

        final int[] arrayD = {};
        quicksort(arrayD);
        Assert.assertTrue(verifySorted(arrayD));

        final int[] arrayE = {5};
        quicksort(arrayE);
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
