package com.selau.algorithms.binarysearch;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearch {


    public int search(int[] source, int target) {
        if (source == null)
            return -1;

        int low = 0;
        int high = source.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            int midValue = source[mid];
            if (midValue < target) {
                low = mid +1;
            } else if (midValue > target) {
                high = mid -1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        // given
        int[] source = {1, 2, 3, 4, 8, 9, 10, 11, 15, 20};

        // when then
        Assert.assertEquals(5, search(source, 9));
        Assert.assertEquals(-1, search(source, 7));
    }

}
