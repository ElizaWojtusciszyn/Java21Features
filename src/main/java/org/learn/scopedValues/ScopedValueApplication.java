package org.learn.scopedValues;

public class ScopedValueApplication {

    public static void main(String[] args) {
        var exampleRunnable1 = new ExampleRunnable(1l);
        var thread1 = new Thread(exampleRunnable1);
        var exampleRunnable2 = new ExampleRunnable(2l);
        var thread2 = new Thread(exampleRunnable2);
        thread1.start();
        thread2.start();
    }

}
