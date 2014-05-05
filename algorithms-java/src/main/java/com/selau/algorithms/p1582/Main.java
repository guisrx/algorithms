//@JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

package com.selau.algorithms.p1582;

import java.io.IOException;
import java.util.StringTokenizer;

class Main {

	// utility function to read from stdin
	static String ReadLn(int maxLg) {
        byte lin[] = new byte[maxLg];
        int lg = 0, car = -1;
        String line = "";

        try {
            while (lg < maxLg) {
                car = System.in.read();
                if ((car < 0) || (car == '\n'))
                    break;
                lin[lg++] += car;
            }
        } catch (IOException e) {
            return (null);
        }

        if ((car < 0) && (lg == 0))
            return (null); // eof
        return (new String(lin, 0, lg));
    }

	// entry point from OS
    public static void main(String args[]) {
        Main myWork = new Main(); // create a dinamic instance
        myWork.Begin(); // the true entry point
    }

    void Begin() {
        String input;
        StringTokenizer idata;
        int a, b, min, max, num, n, cycle, cyclemax;

        while ((input = Main.ReadLn(255)) != null) {
            idata = new StringTokenizer(input);
            a = Integer.parseInt(idata.nextToken());
            b = Integer.parseInt(idata.nextToken());
            if (a < b) {
                min = a;
                max = b;
            } else {
                min = b;
                max = a;
            }
            for (cyclemax = -1, num = min; num <= max; num++) {
                for (n = num, cycle = 1; n != 1; cycle++)
                    if ((n % 2) != 0)
                        n = 3 * n + 1;
                    else
                        n >>= 1;
                if (cycle > cyclemax)
                    cyclemax = cycle;
            }
            System.out.println(a + " " + b + " " + cyclemax);
        }
    }
}
