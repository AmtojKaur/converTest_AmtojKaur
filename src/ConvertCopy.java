/*
 * Convert.java
 *
 * TCSS 371 assignment 1
 */

/**
 * A class to provide static methods for converting numbers between bases.
 *
 * @author Alan Fowler
 * @author Angelynna Pyeatt and Amtoj Kaur
 * @version 1.1
 */
public final class ConvertCopy {

    /**
     * A private constructor to inhibit instantiation of this class.
     */
    public ConvertCopy() {
        // Objects should not be instantiated from this class.
        // This class is just a home for static methods (functions).
        // This design is similar to the Math class in the Java language.
    }

    /**
     * Accepts an array of characters representing the bits in a 2's complement number
     * and returns the decimal equivalent.
     *
     * precondition:
     * This method requires that the maximum length of the parameter array is 16.
     *
     * @param theBits an array representing the bits in a 2's complement number
     * @throws IllegalArgumentException if the length of the parameter array > 16
     * @return the decimal equivalent of the 2's complement parameter
     */
    public static int convert2sCompToDecimal(final char[] theBits) throws IllegalArgumentException {
        if(theBits.length > 16) {
            throw new IllegalArgumentException();
        }
        int ret = 0;
        boolean one = false;
        boolean neg = false;
        if(theBits[0] == '1') {
            neg = true;
        }
        char[] converted = new char[theBits.length];

        //flip and convert to binary
        for (int i = (theBits.length - 1); i >= 0; i--) {
            if(!one) {
                if(theBits[i] == '1'){
                    one = true;
                }
                converted[i] = theBits[i];
            } else {
                if(theBits[i] == '0') {
                    converted[i] = '1';
                } else {
                    converted[i] = '0';
                }
            }
        }

        //finds integer value of 2's complement
        if(!neg) {
            for (int i = 1; i < converted.length; i++) {
                int n = converted.length - i;
                int k = (int) converted[i] - 48;
                ret += k * Math.pow(2, n);
            }
        } else {
            for (int i = 1; i < converted.length; i++) {
                int n = converted.length - i - 1;
                //System.out.println("n=" + n);
                int k = (int) converted[i] - 48;
                //System.out.println("k= "+ k);
                ret -= k * Math.pow(2, n);
            }
        }

        return ret; // Replace the zero return value with a correct return value.
    }

    /**
     * Accepts a decimal parameter and returns an array of characters
     * representing the bits in the 16 bit two's complement equivalent.
     *
     * precondition:
     * This method requires that the two's complement equivalent
     * won't require more than 16 bits
     *
     * @param theDecimal the decimal number to convert to 2's complement
     * @throws IllegalArgumentException if the parameter cannot be represented in 16 bits
     * @return a 16 bit two's complement equivalent of the decimal parameter
     */
    public static char[] convertDecimalTo2sComp(final int theDecimal) throws IllegalArgumentException {
        int dec = theDecimal;
        if(theDecimal == 0) {
            return new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        if(dec < 0) {
            sb.append('-');
            dec *= -1;
        }

        while(dec > 0) {
            int num = dec % 2;
            sb.append(num);
            dec /= 2;
            k++;
        }

        if(sb.length() >= 16) {
            throw new IllegalArgumentException();
        }

        char[] ch = sb.toString().toCharArray();
        boolean one = false;

        //flip and convert to 2's complement
        for (int i = ch.length - 1; i >= 0; i--) {
            if(!one) {
                if(ch[i] == '1'){
                    one = true;
                }
            }
            if(one) {
                if(ch[i] == '0') {
                    ch[i] = '1';
                } else {
                    ch[i] = '0';
                }
            }
        }
        char[] n;
        if(ch[0] != '-') {   //if negative
            n = new char[ch.length];
            n[0] = '0';
            for(int i = ch.length-1; i > 0; i--) {
                n[ch.length-i] = ch[i];

            }

        } else {
            n = new char[ch.length - 1];
            for(int i = 0; i < ch.length-1; i++) {
                n[i] = ch[ch.length - i - 1];
            }
        }

        //make 16 bits:
        char[] c = new char[16];
        int j = 16 - n.length;
        int p = 0;
        if(n[0] == 1) {         //for negative
            for(int i = 15; i >= 0; i--) {
                if (i <= j) {
                    c[i] = n[p];
                } else if (i > 0) {
                    c[i]='0';
                } else {
                    c[i] = '1';
                }
                p++;
            }
        } else {
            for(int i = 15; i >= 0; i--) {
                if(i <= j) {
                    c[i] = n[p];
                } else {
                    c[i] = '0';
                }
                p++;
            }

        }
        return c;

    }


    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */

}
