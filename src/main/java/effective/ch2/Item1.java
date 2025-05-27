/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block 
 */
package effective.ch2;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consider static factory methods instead of constructors
 */
public class Item1 {
    private static Logger log = LoggerFactory.getLogger(Item1.class);

    public static void main(String[] args) {
        // calling a static factory method is expressive
        // and could not create a new object (all the primitive wrappers are immutable)
        Boolean flag = Boolean.valueOf(true);
        log.info("The flag is {}", flag);

        // calling a ctor could be obscure
        BigInteger bigInt = new BigInteger(8, 10, new Random());
        log.info("The bigInt is {}", bigInt);

        // a few common names for static factory methods - beside valueOf()

        // from
        log.info("A Date: {}", Date.from(Instant.now()));

        // of
        List<Integer> values = List.of(1, 2, 3);
        log.info("A list: {}", values);
        log.info("An EnumSet: {}", EnumSet.of(Friends.BOB, Friends.TOM));

        // instance / getInstance
        log.info("A StackWalker: {}", StackWalker.getInstance());

        // create / newInstance - stress the fact a new instance is created
        log.info("A new int array: {}", Array.newInstance(int.class, 5));

        // getXyz / newXyz / xyz - a new Xyz object from a different class
        log.info("An array list from an enumeration: {}", Collections.list(Collections.enumeration(values)));
    }

    enum Friends {
        TOM, BOB, JOE
    }
}
