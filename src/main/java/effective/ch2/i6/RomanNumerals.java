/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i6;

import java.util.regex.Pattern;

/**
 * Reusing expensive object for improved performance
 */
public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile( //
            "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static public boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
