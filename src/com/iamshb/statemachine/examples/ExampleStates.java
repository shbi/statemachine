package com.iamshb.statemachine.examples;

import com.iamshb.statemachine.pub.State;

/**
 * Example Class For how to write states.
 */
public class ExampleStates extends ExampleStateMachine {

    /** Total number of states. */
    private static final int STATE_COUNT = 4;

    /** The idle State. */
    final State idle = new State() {

        /** count of number of updates. */
        private int count;

        @Override
        public void enter() {
            System.out.println("idle:enter");
        }

        @Override
        public void update() {
            ++count;
            System.out.println("idle:update");
        }

        @Override
        public void exit() {
            System.out.println("idle:exit");
        }

        /** Every time we run, use another state. */
        @Override
        public State getNextState() {
            count = count % STATE_COUNT;
            switch (count) {
                case 1: return countToTen;
                case 2: return printX;
                case 3: return countTenFromX;
                default: return this;
            }
        }
    };

    /** Counts to 10. */
    final State countToTen = new State() {

        /** Current Count. */
        private int count;

        @Override
        public void enter() {
            // Start at 0, always.
            count = 0;
            System.out.println("countToTen:enter. count is " + count);
        }

        @Override
        public void update() {
            System.out.println("countToTen:update. count is " + count);
            count++;
        }

        @Override
        public void exit() {
            System.out.println("countToTen:exit. count is " + count);
        }

        @Override
        public State getNextState() {
            // go back to idle after the count is over.
            return count >= 10 ? idle : this;
        }
    };

    /** Print the current x input variable. */
    final State printX = new State() {

        @Override
        public void enter() {
            System.out.println("printX:enter.");
        }

        @Override
        public void update() {
            System.out.println("printX:update. x is " + getX());
        }

        @Override
        public void exit() {
            System.out.println("printX:exit.");

        }

        @Override
        public State getNextState() {
            // Always go back to idle
            return idle;
        }
    };

    /** Counts to x + 10 from the start. */
    final State countTenFromX = new State() {

        /** current count */
        private int count;

        /** value of ext at enter. */
        private int x;

        @Override
        public void enter() {
            // Reset count and get the current x.
            count = 0;
            x = getX();
            System.out.println("countTenFromX:enter. count is " + (x + count));
        }

        @Override
        public void update() {
            // increment count.
            count++;
            System.out.println("countTenFromX:update. count is " + (x + count));
        }

        @Override
        public void exit() {
            System.out.println("countTenFromX:exit. count is " + (x + count));
        }

        @Override
        public State getNextState() {
            // if we counted to 10, go bacl to idle
            return count >= 10 ? idle : this;
        }
    };

    @Override
    protected State getStartState() {
        // always start at idle.
        return idle;
    }
}
