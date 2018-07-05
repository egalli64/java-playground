/**
 * HackerRank - Java - Advanced - Java Reflection Attributes
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-reflection-attributes/problem
 */
package hackerrank.challenges.advanced.java_reflection_attributes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

class Student {
    private String name;
    private String id;
    private String email;

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void anothermethod() {
        System.out.println(name + id + email);
    }
}

public class Solution {
    private static void classic() {
        Method[] methods = Student.class.getDeclaredMethods();

        ArrayList<String> methodList = new ArrayList<>();
        for (Method method : methods) {
            methodList.add(method.getName());
        }

        Collections.sort(methodList);
        for (String name : methodList) {
            System.out.println(name);
        }
    }

    private static void modern() {
        Stream.of(Student.class.getDeclaredMethods()) //
                .map(Method::getName) //
                .sorted() //
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println("classic solution:");
        classic();
        System.out.println("modern solution:");
        modern();
    }
}
