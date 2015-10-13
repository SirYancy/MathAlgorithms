package com.spin_onehalf.mathalgorithmexecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 10/12/15.
 */
public class MathAlgorithms {

    /**
     * This method uses Euclid's Algorithm to calculate the GCD of any two integers.  Negative numbers
     * automatically converted to their absolute value.
     * @param a first integer
     * @param b second integer
     * @return the GCD of two integers.
     */
    public static int euclidAlgorithm(int a, int b) {
        if(a < 0 || b< 0){
            a = Math.abs(a);
            b = Math.abs(b);
        }

        //swap the two variables if b > a
        if (b > a) {
            int swap = a;
            a = b;
            b = swap;
        }

        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        } else {
            return euclidAlgorithm(b, a % b);
        }
    }

    /**
     * Calculates Least Common Multiple of two integers.  It uses the
     * GCD Euclid's Algoirthm to calculate this.
     * @param a an integer
     * @param b an integer
     * @return an integer which is the LCM of the input ints.
     */
    public static int leastCommonMultiple(int a, int b){
        return (a * b) / euclidAlgorithm(a, b);
    }

    public static String doFactorize(int number){
        int n = number;
        List<Integer> factors = new ArrayList<>();
        for(int i = 2; i <= n / i; i++) {
            while( n % i == 0){
                factors.add(i);
                n /= i;
            }
        }
        if(n > 1)
            factors.add(n);

        return makeFactorString(factors);
    }

    private static String makeFactorString(List<Integer> factors) {
        StringBuilder outputString = new StringBuilder();

        int currentFactor = 0;
        int factorCount = 1;

        for(int i = 0; i < factors.size() + 1; i++){
            int nextFactor;
            //This is the cheatiest bunch of cheating ever.
            try {
                nextFactor = factors.get(i);
            }catch(Exception e){
                nextFactor = currentFactor + 1;
            }
            //if this is the same as the previous number
            if(nextFactor == currentFactor){
                factorCount ++;
            }
            //otherwise
            else{
                if(currentFactor != 0) {
                    outputString.append(currentFactor);
                    outputString.append("<sup><small>");
                    outputString.append(factorCount);
                    outputString.append("</small></sup>");
                    if (i < factors.size()) {
                        outputString.append(" * ");
                    }
                    currentFactor = nextFactor;
                    factorCount = 1;
                }
                else{
                    currentFactor = nextFactor;
                }
            }
        }
        return outputString.toString();
    }
}
