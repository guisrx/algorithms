package com.selau.algorithms.smu_numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/** Rules:
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

    public int generate(int position) {

        // initial SMUs
        final List<Integer> smus = new ArrayList<Integer>(MAX_SMUS);

        smus.add(Integer.valueOf(1));
        smus.add(Integer.valueOf(2));

        // candidates
        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(Integer.valueOf(3));

        final Set<Integer> smuPastCandidates = new HashSet<Integer>();

        for (int i = 0; i < 50; i++) {

            // generated from candidates
            final Map<Integer, Set<Integer>> generated = new HashMap<Integer, Set<Integer>>();
            final Set<Integer> touched = new HashSet<Integer>();

            for (Integer candidate : candidates) {
                final Set<Integer> generatedsFromCandidate = new HashSet<Integer>();

                for (Integer smu : smus) {
                    final Integer newGenerated = candidate + smu;

                    touched.add(newGenerated);
                    generatedsFromCandidate.add(newGenerated);
                }
                generated.put(candidate, generatedsFromCandidate);
            }

            final Set<Integer> newCandidates = new HashSet<Integer>();
            for (Integer candidate : candidates) {
                if ((! touched.contains(candidate)) && (! smuPastCandidates.contains(candidate))) {
                    smus.add(candidate);

                    final Set<Integer> candidateGenerated = generated.get(candidate);
                    candidateGenerated.removeAll(candidates);
                    newCandidates.addAll(candidateGenerated);
                }
            }
            smuPastCandidates.addAll(candidates);
            candidates = new ArrayList<Integer>(newCandidates);
        }

        for (Integer smu : smus) {
            System.out.print(smu + ", ");
        }

        return 0;
    }

    @Test
    public void testCases() {

        generate(50);

        // Assert.assertEquals(675904508, generate(500000));
    }

}
