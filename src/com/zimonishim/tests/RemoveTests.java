package com.zimonishim.tests;

import com.zimonishim.GUI.resultTables.resultTypes.ResultEntry;
import com.zimonishim.util.TestData;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class RemoveTests {

    public static Runnable removeRunnable(Collection<Integer> collection, ITestCallback callback) {
        return removeRunnable(collection, TestData.toRemove, callback);
    }

    public static Runnable removeRunnable(Collection<Integer> collection, int[] numbersToRemove, ITestCallback callback) {
        return () -> {
            removeTest(collection, Arrays.stream(numbersToRemove).boxed().collect(Collectors.toList()), callback);
            try {
                Thread.sleep(TestHandler.SLEEP_AMOUNT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done executing REMOVE for " + collection.getClass().getSimpleName());
        };
    }

    public static void removeTest(Collection<Integer> collection, Collection<Integer> numbersToRemove, ITestCallback callback) {

        int[] copyOfInitialList = collection.stream().mapToInt(i -> i).toArray();

        long startTime = System.nanoTime();
        numbersToRemove.forEach(collection::remove);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

        int[] copyOfRemovedList = collection.stream().mapToInt(i -> i).toArray();
        callback.returnRemoveResult(new ResultEntry(collection.getClass().getSimpleName(), "Remove Numbers", copyOfInitialList, totalTime, copyOfRemovedList));
    }
}
