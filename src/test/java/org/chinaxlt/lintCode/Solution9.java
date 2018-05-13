package org.chinaxlt.lintCode;

import org.junit.Test;

import java.util.ArrayList;

public class Solution9 {

    public ArrayList<String> fizzBuzz(int n) {
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                results.add("fizz buzz");
            } else if (i % 5 == 0) {
                results.add("buzz");
            } else if (i % 3 == 0) {
                results.add("fizz");
            } else {
                results.add(String.valueOf(i));
            }
        }
        return results;
    }
    
    @Test
    public void testS9() {
        ArrayList<String> r = fizzBuzz(15);
        System.out.println(r.toString());
    }
}
