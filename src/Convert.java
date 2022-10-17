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
public final class Convert {


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

        int result = 0;

        //determines whether method will return a positive or negative int
        boolean pos = theBits[0] == '0';

        //iterates through theBits
        for (int i = 1; i < theBits.length; i++) {
            //automatically flips to binary and calculates 2^n (decrementing n) and adds to result
            if ((pos && theBits[i] == '1') || (!pos && theBits[i] == '0')) {
                result += Math.pow(2, theBits.length - i - 1);
            }
        }

        if(!pos) {      //if negative
            return -1 - result; //return negative result
        } else {        //if positive
            return result;  //return positive result
        }
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
        if(theDecimal >= 32768 || theDecimal <= -32769) {   //throws exception when anything above/below
            //max/min illegal ints are sent into method
            throw new IllegalArgumentException();
        }
        char[] c = new char[16];

        // check whether the number is positive
        boolean pos = theDecimal >= 0;
        int num;
        if(pos) {           //if positive
            num = theDecimal;
        } else {            //else: negative
            num = -theDecimal - 1;
        }

        int index = 0;
        int f;
        if(pos) {
            f = 1;
        } else {
            f = 0;
        }

        //converts decimal to binary by taking the mod of the num
        while (num > 0) {
            if(num % 2 == f) {
                c[index++] = '1';
            } else {
                c[index++] = '0';
            }
            num /= 2;
        }
        //difference between length of temporary char array and index
        int dif = c.length - index;

        if (dif == 0) { //if difference is 0, length = 16 (at max), return array
            return c;

        } else {        //if negative, convert to 2s comp
            char[] result = new char[16];

            for (int i = 0; i < result.length; i++) {
                if(i < dif) {
                    if(pos) {
                        result[i] = c[c.length - 1 - i] = '0';
                    } else {
                        result[i] = c[c.length - 1 - i] = '1';
                    }
                } else {
                    result[i] = c[c.length - 1 - i];
                }
            }
            return result;
        }
    }


    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */

}
