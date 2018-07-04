package hackerrank.challenges.dataStructures.java_priority_queue;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.*;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void test() {
        Priorities priorities = new Priorities();
        List<String> events = Arrays.asList("ENTER John 3.75 50", "ENTER Mark 3.8 24", "ENTER Shafaet 3.7 35", "SERVED",
                "SERVED", "ENTER Samiha 3.85 36", "SERVED", "ENTER Ashley 3.9 42", "ENTER Maria 3.6 46",
                "ENTER Anik 3.95 49", "ENTER Dan 3.95 50", "SERVED");
        List<Student> students = priorities.getStudents(events);
        assertThat(students.size(), is(4));
        assertThat(students.get(0).getName(), is("Dan"));
        assertThat(students.get(1).getName(), is("Ashley"));
        assertThat(students.get(2).getName(), is("Shafaet"));
        assertThat(students.get(3).getName(), is("Maria"));
    }
}
