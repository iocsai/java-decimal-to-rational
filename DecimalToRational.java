/**
 * The program transforms a decimal number into a rational.
 */
package decimaltorational;

import java.util.Scanner;

public class DecimalToRational {
    
    private final double decimal;
    private final double EPSILON = 1E-5;

    public static void main(String[] args) {
        DecimalToRational dr = new DecimalToRational(input(""));
        
        long startTime = System.nanoTime();
        
        Fraction solution = dr.solve();
        
        long estimatedTime = System.nanoTime() - startTime;
        System.out.printf("%n%12.11f = %s", dr.decimal, solution);
        System.out.printf("%nEstimated time in microseconds: %,d%n%n",
                estimatedTime / 1000);
    }

    public DecimalToRational(double decimal) {
        this.decimal = decimal;
    }
    
    private static double input(String message) {
        Scanner sc = new Scanner (System.in);
        while (true) {
            System.out.print(message.length() < 1 ? "input: " : message);
            String msg = sc.nextLine();
            try {
                return Double.parseDouble(msg);
            } catch(NumberFormatException ex) {
                System.err.println(ex.toString());
            }
        }
    }

    private Fraction solve() {
        int denominator = 1;
        double number = 0;
        while (true) {
            number = this.decimal * denominator++;
            double difference = number - Math.round(number);
            //System.out.printf("%8.5f / %d %20.16f%n", number, denominator - 1, difference);
            if (difference < this.EPSILON && difference > -this.EPSILON) {
                return new Fraction((int) Math.round(number), --denominator);
            }
        }
    }

    private static class Fraction {
        
        public final int numerator;
        public final int denominator;

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public String toString() {
            return String.format("%d/%d", numerator, denominator);
        }
        
        
    }
}
