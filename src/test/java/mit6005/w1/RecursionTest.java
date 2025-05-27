package mit6005.w1;

import static org.hamcrest.MatcherAssert.assertThat;
import static mit6005.w1.Recursion.*;
import static org.hamcrest.core.Is.*;

import org.junit.jupiter.api.Test;

public class RecursionTest {

    @Test
    public void testSubsequencesAbc() {
        assertThat(subsequences("abc"), is(",c,b,bc,a,ac,ab,abc"));
    }

    @Test
    public void testSubsequencesEmpty() {
        assertThat(subsequences(""), is(""));
    }

    @Test
    public void testSubsequencesA() {
        assertThat(subsequences("a"), is(",a"));
    }

    @Test
    public void testLuis() {
        assertThat(subsequencesLouis("c"), is(",c"));
        assertThat(subsequencesLouis("a"), is("c,ca")); // this silly result is expected!
    }

    @Test
    public void testStringValue289() {
        assertThat(stringValue(289, 10), is("289"));
    }

    @Test
    public void testStringValue0() {
        assertThat(stringValue(0, 10), is("0"));
    }

    @Test
    public void testStringValueHex() {
        assertThat(stringValue(170, 16), is("10A"));
    }
}
