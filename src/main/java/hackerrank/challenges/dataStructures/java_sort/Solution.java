/**
 * HackerRank - Java - Data Structures - Java Sort
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/2018/06/hackerrank-java-sort.html
 *       https://www.hackerrank.com/challenges/java-sort/problem
 *
 * Write a Comparator for Student
 * - CGPA in decreasing order
 * - then first name
 * - then id
 */
package hackerrank.challenges.dataStructures.java_sort;

import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

class Student {
    private int id;
    private String fname;
    private double cgpa;

    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public double getCgpa() {
        return cgpa;
    }
}

public class Solution {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            sc.useLocale(Locale.ENGLISH); // ensure dot used in floating numbers

            int count = sc.nextInt();
            sc.nextLine();

            Stream.generate(() -> new Student(sc.nextInt(), sc.next(), sc.nextDouble())) //
                    .limit(count) //
                    .sorted(Comparator.comparingDouble(Student::getCgpa).reversed() //
                            .thenComparing(Student::getFname) //
                            .thenComparingInt(Student::getId)) //
                    .map(Student::getFname) //
                    .forEach(System.out::println);
        }
    }
}