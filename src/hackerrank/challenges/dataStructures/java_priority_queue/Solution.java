/**
 * HackerRank - Java - Data Structures - Java Priority Queue
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-priority-queue/problem
 *
Input:
12
ENTER John 3.75 50
ENTER Mark 3.8 24
ENTER Shafaet 3.7 35
SERVED
SERVED
ENTER Samiha 3.85 36
SERVED
ENTER Ashley 3.9 42
ENTER Maria 3.6 46
ENTER Anik 3.95 49
ENTER Dan 3.95 50
SERVED

Output (the items still in queue):
Dan
Ashley
Shafaet
Maria
 */
package hackerrank.challenges.dataStructures.java_priority_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//// code above immutable
import java.util.PriorityQueue;
import java.util.Comparator;

class Student {
    private int token;
    private String name;
    private double CGPA;

    public Student(String[] tokens) {
        token = Integer.parseInt(tokens[3]);
        name = tokens[1];
        CGPA = Double.parseDouble(tokens[2]);
    }

    public int getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return CGPA;
    }
}

class Priorities {
    private static PriorityQueue<Student> pq = new PriorityQueue<>( //
            Comparator.comparing(Student::getCGPA).reversed()   // higher CGPA first
            .thenComparing(Student::getName)                    // then name
            .thenComparing(Student::getToken));                 // then token

    public List<Student> getStudents(List<String> events) {
        pq.clear();

        for (String event : events) {
            String[] tokens = event.split(" ");
            if(tokens[0].equals("ENTER")) {
                pq.add(new Student(tokens));
            } else {
                pq.poll();
            }
        }

        List<Student> result = new ArrayList<Student>();
        while(! pq.isEmpty()) {
            result.add(pq.poll());
        }
        return result;
    }
}
//// code below immutable

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}
