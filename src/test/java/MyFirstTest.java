package test.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

    public class MyFirstTest {
        MyNumbers numbers = new MyNumbers();

        @Test
        public void canAddTwoPlusTwo() {
            numbers.setAnswer(add(2, 2));
            assertEquals("2+2=4", 4, numbers.getAnswer());
        }

        public int add(int first, int second) {
            return first + second;
        }

        // Exercise: Write Test to subtract 2 from 2
        @Test
        public void canSubtractTwoFromTwo() {
            numbers.setAnswer(2-2);
            assertEquals("2-2=0", 0, numbers.getAnswer());
        }

    }

