package com.zimonishim.util;

import com.zimonishim.GUI.resultTables.resultTypes.ResultEntry;

import java.util.*;

public class StatisticsHelper {

    //TODO: Implement a correction factor, which determines what entries strafe away too far from the average, and should thus not be included.
    public static Map<String, Long> getAverageOfAllCollections(Collection<ResultEntry> results) {
        Map<String, Long> map = new HashMap<>();

        Map<String, Set<Long>> allTimes = getCollectionMap(results);

        allTimes.forEach((s, longs) -> {
            long totalTime = 0;
            for (Long aLong : longs) {
                totalTime += aLong;
            }

            int amountOfEntries = longs.size();

            map.put(s, totalTime / amountOfEntries);
        });

        return map;
    }

    public static Map<String, Set<Long>> getCollectionMap(Collection<ResultEntry> results) {
        Map<String, Set<Long>> allTimes = new HashMap<>();

        for (ResultEntry result : results) {
            if (allTimes.containsKey(result.getCollectionClassName())) {
                allTimes.get(result.getCollectionClassName()).add(result.getTotalTime());
            } else {
                allTimes.put(result.getCollectionClassName(), new HashSet<>(Collections.singleton(result.getTotalTime())));
            }
        }

        allTimes.forEach((s, longs) -> {
            System.out.println("Now processing " + s + ".");
            double standardDeviation = processStandardDeviation(longs);
        });

        return allTimes;
    }

    private static double processStandardDeviation(Set<Long> results) {
        List<Long> list = new ArrayList<>(results);

        double[] doubles = new double[results.size()];
        for (int i = 0; i < results.size(); i++) {
            doubles[i] = list.get(i);
        }

        double standardDeviation = calculateStandardDeviation(doubles);
        System.out.println("Standard deviation: " + standardDeviation);
        return standardDeviation;
    }

    public static double calculateStandardDeviation(double[] input) {
        double n = input.length;
        double sum = 0;
        double mean;

        for (double v : input) {
            sum = sum + v;
        }

        mean = sum / n;
        System.out.println("Mean: " + mean);
        sum = 0;

        for (double v : input) {
            sum += Math.pow((v - mean), 2);
        }

        mean = sum / (n - 1);
        double deviation = Math.sqrt(mean);
        System.out.println("Standard Deviation: " + deviation);

        return deviation;
    }
}
