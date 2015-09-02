package com.selau.algorithms.amazon;

import org.junit.Assert;
import org.junit.Test;

/**
 *  Problem B:
 *
 *  Solution: Generates all the possibilities top -> bottom and bottom -> top.
 *  Evaluates if the possibility is valid and then evaluates its value.
 *  Outputs the highest value found for a valid possibility.
 *
 *  Improvement: generate only valid possibilities changing the iterations indexes
 *  and use dynamic programming to not have to calculate possibilities already evaluated.
 */

public class QuestionB {

    public static Integer cake(final int[] layers) {
        int maxPleasure = 0;

        for (int topLayer = -1; topLayer < layers.length; topLayer++) {
            for (int bottomLayer = layers.length; bottomLayer >= 0; bottomLayer--) {

                final int pleasure = evaluatePleasure(layers, topLayer, bottomLayer);

                if (pleasure > maxPleasure) {
                    maxPleasure = pleasure;
                }
            }
        }
        return maxPleasure;
    }

    private static int evaluatePleasure(final int[] layers, final int topLayer, final int bottomLayer) {
        if (topLayer >= bottomLayer)
            return Integer.MIN_VALUE;

        int totalPleasure = 0;

        for (int layer = 0; layer < layers.length; layer++) {
            if ((layer <= topLayer) || (layer >= bottomLayer)) {

                totalPleasure += layers[layer];
            }
        }
        return totalPleasure;
    }

    @Test
    public void testCases() {

        final int[] layersA = {10, -2, 5, -4, 3, -5, 1};
        Assert.assertEquals(Integer.valueOf(14), QuestionB.cake(layersA));

        final int[] layersB = {10, -2};
        Assert.assertEquals(Integer.valueOf(10), QuestionB.cake(layersB));

        final int[] layersC = {10};
        Assert.assertEquals(Integer.valueOf(10), QuestionB.cake(layersC));

        final int[] layersD = {};
        Assert.assertEquals(Integer.valueOf(0), QuestionB.cake(layersD));

        final int[] layersE = {-2};
        Assert.assertEquals(Integer.valueOf(0), QuestionB.cake(layersE));

        final int[] layersF = {-1, -5, -7, 9, 10, 8};
        Assert.assertEquals(Integer.valueOf(27), QuestionB.cake(layersF));

        final int[] layersG = {-1, -2, 1, -7};
        Assert.assertEquals(Integer.valueOf(0), QuestionB.cake(layersG));

        final int[] layersH = {-99, 100, -98};
        Assert.assertEquals(Integer.valueOf(2), QuestionB.cake(layersH));
    }

}
