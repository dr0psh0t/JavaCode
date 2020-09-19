package com.daryll.multithreadparallel.threadcooperation;

/*
Suppose you use a buffer to store integers and that the buffer size is limited.
The buffer provides the method write(int) to add an int value to the buffer and the method read()
to read and delete an int value from the buffer. To synchronize the operations, use a lock
with two conditions: notEmpty (i.e., the buffer is not empty) and notFull (i.e., the buffer
is not full). When a task adds an int to the buffer, if the buffer is full, the task will wait for
the notFull condition. When a task reads an int from the buffer, if the buffer is empty, the
task will wait for the notEmpty condition. The interaction between the two tasks is shown in Figure 30.18.

Listing 30.7 presents the complete program. The program contains the Buffer class (lines
50–101) and two tasks for repeatedly adding and consuming numbers to and from the buffer
(lines 16–47). The write(int) method (lines 62–79) adds an integer to the buffer. The
read() method (lines 81–100) deletes and returns an integer from the buffer.

Task for adding an int          Task for deleting an int
            |                           |
            |                           |
            V                           V
while (count == CAPACITY)       while (count == 0)
    notFull.await();  <---+   +---> notEmpty.await();
            |             |   |          |
            V             |   |          V
Add an int to the buffer  |   | Delete and int from the buffer
            |             |   |          |
            V             |   |          V
notEmpty.signal();--------+---+-notFull.signal();

The conditions notFull and notEmpty are used to coordinate task interactions.

The buffer is actually a first-in, first-out queue (lines 52–53). The conditions notEmpty
and notFull on the lock are created in lines 59–60. The conditions are bound to a lock. A
lock must be acquired before a condition can be applied. If you use the wait() and notify()
methods to rewrite this example, you have to designate two objects as monitors.
 */

/*
SAMPLE OUTPUT:

Producer writes 1
			Consumer reads 1
			Wait for notEmpty condition
Producer writes 2
			Consumer reads 2
			Wait for notEmpty condition
Producer writes 3
			Consumer reads 3
			Wait for notEmpty condition
Producer writes 4
			Consumer reads 4
Producer writes 5
			Consumer reads 5
Producer writes 6
			Consumer reads 6
Producer writes 7
Producer writes 8
Wait for notFull condition
			Consumer reads 7
			Consumer reads 8
Producer writes 9
Producer writes 10
Wait for notFull condition
			Consumer reads 9
Producer writes 11
Wait for notFull condition
			Consumer reads 10
Producer writes 12
Wait for notFull condition
			Consumer reads 11
			Consumer reads 12
Producer writes 13
			Consumer reads 13
			Wait for notEmpty condition
Producer writes 14
			Consumer reads 14

 */

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {
        //  create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());

        executor.shutdown();
    }

    //  a task for adding an int to the buffer
    private static class ProducerTask implements Runnable {
        public void run() {
            try {
                int i = 1;

                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.write(i++);  //  add a value to the buffer

                    //  put the thread into sleep
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //  a task for reading and deleting an int from the buffer
    private static class ConsumerTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads "+buffer.read());

                    //  put the thread into sleep
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //  an inner class for buffer
    private static class Buffer {
        private static final int CAPACITY = 1;  //  buffer size

        private LinkedList<Integer> queue = new LinkedList<>();

        //  create a new lock
        private static Lock lock = new ReentrantLock();

        //  create two conditions
        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        public void write(int value) {
            lock.lock();    //  acquire the lock

            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("Wait for notFull condition");
                    notFull.await();
                }

                queue.offer(value);
                notEmpty.signal();  //  signal notEmpty condition
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();  //  release the lock
            }
        }

        public int read() {
            int value = 0;
            lock.lock();    //  acquire the lock

            try {
                while (queue.isEmpty()) {
                    System.out.println("\t\t\tWait for notEmpty condition");
                    notEmpty.await();
                }

                value = queue.remove();
                notFull.signal();   //  signal notFull condition
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            finally {
                lock.unlock();
                //return value;
            }

            return value;
        }
    }
}