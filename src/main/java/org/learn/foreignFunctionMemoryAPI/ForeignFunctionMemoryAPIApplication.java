package org.learn.foreignFunctionMemoryAPI;

import java.lang.foreign.*;

public class ForeignFunctionMemoryAPIApplication {

    public static void main(String[] args) throws Throwable {

        //Arena is new Interface and represents a region od memory
        try (Arena arena = Arena.ofConfined()) {

            //call function outside of JVM
            final var linker = Linker.nativeLinker();

            //symbol lookup gets the address of a symbol in the library
            //eg. C function that gets the length of a string
            final var symbolLookup = linker.defaultLookup();

            final var memorySegment = symbolLookup.find("atoi").orElseThrow();

            final var functionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);

            //call foreign function
            final var methodHandler = linker.downcallHandle(memorySegment, functionDescriptor);

            final var segmentAllocator = arena.allocateUtf8String("25");

            final var result = (long) methodHandler.invokeExact(segmentAllocator);

            System.out.println("Objects value: " + result);

            System.out.println("Objects type: " + ((Object)result).getClass().getSimpleName());
        }

    }

}
