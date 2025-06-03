/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5.i26;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Refactoring to parametrized collection
 */
public class ParametrizedCollectionUser {
    private final Collection<Stamp> stamps;

    public ParametrizedCollectionUser() {
        stamps = new HashSet<>(List.of(new Stamp()));

        // the compiler now could detect the mistake
        // stamps.add(new Coin());
    }

    public void doSomething() {
        for (Iterator<Stamp> i = stamps.iterator(); i.hasNext();) {
            Stamp stamp = i.next();
            System.out.println(stamp);
        }
    }

    @Override
    public String toString() {
        return "{" + stamps + "}";
    }
}
