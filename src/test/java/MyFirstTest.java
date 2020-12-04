package test.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

    public class MyFirstTest {
        int answer;

        @Test
        public void canAddTwoPlusTwo() {
            answer = 2+2;
            assertEquals("2+2=4", 4, answer);
        }

        // Exercise: Write Test to subtract 2 from 2
        @Test
        public void canSubtractTwoFromTwo() {
            answer = 2-2;
            assertEquals("2-2=0", 0, answer);
        }
    }

