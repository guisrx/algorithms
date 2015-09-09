package com.selau.algorithms.smu_numbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/** Ulam Sequence
 *
 * Rules:
 *
 * 1) The first SMU number is 1.
 * 2) The second SMU number is 2.
 * 3) Any other SMU number is the sum of a single combination of previous SMU numbers.
 *
 * @author selau
 *
 */
public class SMUNumbersGenerator {

    private static final int MAX_SMUS = 500010;

    public int generate(final int position) {

        // initial SMUs
        final List<Integer> smus = new ArrayList<Integer>(MAX_SMUS);

        smus.add(Integer.valueOf(1));
        smus.add(Integer.valueOf(2));

        // candidates
        Integer nextSMU = Integer.valueOf(3);

        final Set<Integer> pastSMUCandidates = new HashSet<Integer>(MAX_SMUS);
        final TreeSet<Integer> smuCandidates = new TreeSet<Integer>();

        for (int i = 0; i < position; i++) {

            // generated from candidate
            for (final Integer smu : smus) {
                final Integer newGenerated = nextSMU + smu;

                if (pastSMUCandidates.contains(newGenerated)) {
                    smuCandidates.remove(newGenerated);
                } else {
                    smuCandidates.add(newGenerated);
                }
                pastSMUCandidates.add(newGenerated);
            }
            smus.add(nextSMU);
            nextSMU = smuCandidates.pollFirst();
        }

//        for (final Integer smu : smus) {
//            System.out.print(smu + ", ");
//        }

        return smus.get(position -1);
    }

    @Test
    public void testCases() {

        System.out.println("15: " + generate(15));
        System.out.println("10000: " + generate(10000));
        System.out.println("20000: " + generate(20000));
        System.out.println("50000: " + generate(50000));
        System.out.println("100000: " + generate(100000));
        System.out.println("200000: " + generate(200000));
        System.out.println("500000: " + generate(500000));

        System.out.println("expected 500000: " + 675904508);

        // Assert.assertEquals(675904508, generate(500000));
    }

}
