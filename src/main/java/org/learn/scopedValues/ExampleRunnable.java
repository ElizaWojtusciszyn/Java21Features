package org.learn.scopedValues;

public class ExampleRunnable implements Runnable {

    private Long userId;

    private static ScopedValue<Long> userContext = ScopedValue.newInstance();

    public ExampleRunnable(Long userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        ScopedValue.where(userContext, userId)
                .run(() -> System.out.println("Thread: " + Thread.currentThread().getName() + " User: " + userContext.get()));
    }
}
