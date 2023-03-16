package org.example;

public class Popelrob {
    public static int factorialRek(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorialRek(n-1);
    }

    public static int myFactorialRek(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorialRek(n-1);
    }

    public static int factorialIter(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++) {
            ret *= i;
        }
        return ret;
    }
}
