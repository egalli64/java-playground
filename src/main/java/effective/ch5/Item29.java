/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch5.i29.Stack;

/**
 * Favor generic types
 */
public class Item29 {
    private static Logger log = LoggerFactory.getLogger(Item29.class);

    public static void main(String[] args) {
        log.trace("Enter");

        // a class refactored to be generic
        Stack<String> stack = new Stack<>();
        for (String s : List.of("a", "b", "c")) {
            stack.push(s);
        }
        while (!stack.isEmpty()) {
            // before refactoring a cast to String was required
            System.out.println(stack.pop().toUpperCase());
        }

        log.trace("Exit");
    }
}
