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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prefer try-with-resources to try-finally
 */
public class Item9 {
    private static Logger log = LoggerFactory.getLogger(Item9.class);

    private static int BUFFER_SIZE = 128;

    /**
     * A method using and closing a resource in the classic way
     * <p>
     * try-finally is not anymore the preferred way to go
     */
    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            // debugging is going to be more difficult in case of an exception from
            // readLine() and then another one here in close()
            br.close();
        }
    }

    /**
     * A method using and closing a resource in the classic way
     * <p>
     * try-finally is not anymore the preferred way to go, and with more that a
     * resource to be close we can understand why
     */
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    /**
     * try-with-resources is simpler and more robust
     */
    static String firstLineOfFileModern(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    /**
     * try-with-resources on multiple resources
     */
    static void copyModern(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src); //
                OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }

    /**
     * try-with-resources with a catch clause
     */
    static String firstLineOfFile(String path, String fallback) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            log.error("Using the passed fallback", e);
            return fallback;
        }
    }

    public static void main(String[] args) throws IOException {
        log.trace("Enter");

        firstLineOfFile("README.md");
        copy("README.md", "README.bak");

        firstLineOfFileModern("README.md");
        copyModern("README.md", "README.bak");

        System.out.println(firstLineOfFile("missing.txt", "A fallback message"));

        log.trace("Exit");
    }
}
