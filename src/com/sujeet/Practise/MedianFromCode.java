package com.sujeet.Practise;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


interface Machine {
     List<Double> getPaginatedSortedList();
     int getMachineId();
}

interface Orchestrator {
    Machine getMachine(int id);
}


public class MedianFromCode {

    private static final int HALF_BILLION = 5_000_000;
    private Orchestrator orchestrator;
    Double getMedianFromMachine(List<Machine> machines) {
        int itemsRemaining = HALF_BILLION;
        List<List<Double>> sortedItemsFromMachines = new ArrayList<>();
        int currItemsToEvaluate = 0;
        PriorityQueue<Pair<Double, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingDouble(a -> a.fst));

        for(Machine currMachine : machines) {
            List<Double> sortedList = currMachine.getPaginatedSortedList();
            sortedItemsFromMachines.add( sortedList);
            currItemsToEvaluate += sortedList.size();
            // this minHeap take care of answering that out of all machines which machine's sortedList has the least maximum.
            if(sortedList.size() > 0) {
                minHeap.add(new Pair<>(sortedList.get(sortedList.size() - 1), currMachine.getMachineId()));
            }
        }

        while(currItemsToEvaluate < itemsRemaining && !minHeap.isEmpty()) {
             Pair<Double, Integer> minOfMaximumScannedItemSeenForMachine = minHeap.poll();
             int machineId = minOfMaximumScannedItemSeenForMachine.snd;
             Machine machine = orchestrator.getMachine(machineId);

             // now we replace the machine which had the lowest value with its next set of items. Pretty neat!!
             int itemsBeingRemovedNow = sortedItemsFromMachines.get(machineId).size();
             itemsRemaining -= itemsBeingRemovedNow; // seen this to be lowest.

             // insert its next batch from the lowest items shown
             List<Double> nextBatch = machine.getPaginatedSortedList(); // could be empty.
             sortedItemsFromMachines.set(machineId, nextBatch);
             currItemsToEvaluate += nextBatch.size() - itemsBeingRemovedNow; // add the next batchOfItems in the
             if (nextBatch.size() > 0)
                 minHeap.add(new Pair<>(nextBatch.get(nextBatch.size()-1), machineId));
        }
        // now we know that this sortedItemsFromMachines has all the items we need to find the itemsRemaining.
        return getNItems(sortedItemsFromMachines,itemsRemaining);
    }

    Double getNItems(List<List<Double>> sortedItemsFromMachines, int itemsRemaining) {
        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        for(List<Double> sortedItems : sortedItemsFromMachines ) {
            for (Double item : sortedItems) {
                minHeap.add(item);
            }
        }
        while(!minHeap.isEmpty() && itemsRemaining >0) {
            itemsRemaining--;
            minHeap.poll(); // no need to ADD more items here. Dont make network calls, its sub optimal.
        }
        return minHeap.peek();
    }
}
