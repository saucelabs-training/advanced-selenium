package test.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

    public class MyFirstTest {
        MyNumbers numbers = new MyNumbers();

        @Test
        public void canAddTwoPlusTwo() {
            Integer result = operate("add", 2, 2);
            numbers.setAnswer(result);
            assertEquals("2+2=4", 4, numbers.getAnswer());
        }

        // Exercise: Write Test to subtract 2 from 2
        @Test
        public void canSubtractTwoFromTwo() {
            Integer result = operate("subtract", 2, 2);
            numbers.setAnswer(result);
            assertEquals("2-2=0", 0, numbers.getAnswer());
        }

        public Integer operate(String operation, int first, int second) {
            if ("add".equals(operation)) {
                return first + second;
            } else if ("subtract".equals(operation)) {
                return first - second;
            } else {
                return null;
            }
        }
    }

