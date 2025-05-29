/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch2.i7.LeakingStack;

/**
 * Eliminate obsolete object references
 */
public class Item7 {
    private static Logger log = LoggerFactory.getLogger(Item7.class);

    public static void main(String[] args) {
        log.trace("Enter");

        LeakingStack ls = new LeakingStack();
        ls.push("Bob");
        ls.push("Tom");
        ls.push("Jim");
        System.out.println("Pop: " + ls.pop());
        System.out.println("Pop: " + ls.pop());
        ls.checkElements();

        ls.push("Tom");
        ls.push("Jim");
        System.out.println("Pop (fix): " + ls.popFix());
        System.out.println("Pop (fix): " + ls.popFix());
        ls.checkElements();

        log.trace("Exit");
    }
}
