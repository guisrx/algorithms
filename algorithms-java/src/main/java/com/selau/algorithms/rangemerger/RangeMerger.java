package com.selau.algorithms.rangemerger;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Write a subroutine which will merge integer ranges in the given array
 * 
 *   input:  [[1, 2], [4, 7], [6, 9], [8,13], [11, 15]]
 *   output: [[1, 2], [4, 15]]
 *   
 * @author selau
 *
 */
public class RangeMerger {
	
	public List<int[]> merge(final List<int[]> ranges) {
		
		final List<Integer> firstValues = new ArrayList<Integer>();
		final List<Integer> secondValues = new ArrayList<Integer>();
		
		for(final int[] range : ranges) {
			
			firstValues.add(range[0]);
			secondValues.add(range[1]);
		}
		
		final List<int[]> mergedRanges = new ArrayList<int[]>();
		
		int startRangeValue = firstValues.get(0);
		int finalIndex = 0;
		
		for(int startIndex = 0; (startIndex +1) < firstValues.size(); startIndex++) {
			
			if(firstValues.get(startIndex +1) < secondValues.get(finalIndex)) {
				finalIndex++;
				continue;
			}
			
			mergedRanges.add(new int[] {startRangeValue, secondValues.get(finalIndex)});
			
			startRangeValue = firstValues.get(startIndex +1);
			finalIndex++;
		}
		mergedRanges.add(new int[] {startRangeValue, secondValues.get(finalIndex)});
		
		return mergedRanges;
	}

	@Test
	public void shouldMergeOrderedRangesCorrectly() {
		// given
		final RangeMerger subject = new RangeMerger();
		final List<int[]> ranges = new ArrayList<int[]>();
		
		ranges.add(new int[] {1, 2});
		ranges.add(new int[] {4, 7});
		ranges.add(new int[] {6, 9});
		ranges.add(new int[] {8, 13});
		ranges.add(new int[] {11, 15});
		
		final List<int[]> expectedMergedRanges = new ArrayList<int[]>();
		
		expectedMergedRanges.add(new int[] {1, 2});
		expectedMergedRanges.add(new int[] {4, 15});
		
		// when
		final List<int[]> mergedRanges = subject.merge(ranges);
		
		// then
		assertThat(mergedRanges, containsInAnyOrder(expectedMergedRanges.toArray()));
	}
	
}
