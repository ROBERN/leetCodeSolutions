package com.sujeet.Interview;

import java.util.*;

public class DoorDash {


// Examples:
//P1, D1 -> valid
    //P1, P2, D1, D2 -> valid
    //P1, D1, P2, D2 -> valid
    //P2, D2, P1, D2 -> invalid
    //P1, D2, P2, D1 -> invalid
    //P1, D2 -> invalid
    //P1, P2, D2 -> invalid
    //P1, D1, P1, D1 -> invalid
    //P1, P2, D2, D1 -> valid

    //Examples: N = 1 -> [p1, d1]
    //N = 2 -> [p1, d1, p2, d2], [p1, p2, d1, d2], [p1, p2, d2, d1], [p2, d2, p1, d1], [p2, p1, d1, d2], [p2, p1, d2, d1]
    // (P1, P2). List<Items>
    //pick P1
    // -> rec(set(P2), Set(D1), List(P1),) ..
    // -> rec()
    //


    // SET OF DROPPED, SET OF PICKED ITEMS

    //MAP :PICKED ITEMS WITH INDEX
    // MAP : DROPPED ITEM WITH INDEX

    public static class Solution {

        private List<List<String>> validSequences = new ArrayList<>();

        public List<List<String>> getAllValidActivity(int N) {
            Set<String> pickups = new HashSet<>();
            for(int idx = 1; idx <= N; idx++) {
                pickups.add("P" + Integer.toString(idx));
            }
            Set<String> dropoffs = new HashSet<>();
            generateValidActivities(pickups, dropoffs, new ArrayList<>());

            return validSequences;

        }

        private void generateValidActivities(Set<String> pickups, Set<String> dropoffs, List<String> activities) {
            if(pickups.size() == 0 && dropoffs.size() == 0) {
                // copy activities
                validSequences.add(new ArrayList<>(activities));
            }

            List<String> activityToDo = new ArrayList<>();
            for(String pickup  : pickups) {
                activityToDo.add(pickup);
            }
            for(String dropoff  : dropoffs) {
                activityToDo.add(dropoff);
            }

            for(String activity : activityToDo) {
                pickups.remove(activity);
                dropoffs.remove(activity); // could be no-op.

                if(activity.startsWith("P")) {
                    dropoffs.add(("D" + activity.substring(1)));
                }

                activities.add(activity);

                generateValidActivities(pickups, dropoffs, activities);

                activities.remove(activity);

                if(activity.startsWith("P")) {
                    dropoffs.remove(("D" + activity.substring(1)));
                    pickups.add(activity);
                } else {
                    dropoffs.add(activity);
                }
            }

        }

        public static boolean isValidActivityList(List<String> activityItems) {
            int lengthOfItems = activityItems.size();
            if(lengthOfItems %2 ==1)  {
                return false;
            }
            Map<String, Integer> pickUpToIndexMap = new HashMap<>();
            Map<String, Integer> droppedToIndexMap = new HashMap<>();

            int idx = 0;
            for(String  activity : activityItems) {
                if(pickUpToIndexMap.containsKey(activity) || droppedToIndexMap.containsKey(activity)) {
                    return false;
                }
                if(activity.startsWith("P")) {
                    pickUpToIndexMap.put(activity, idx);
                } else {
                    droppedToIndexMap.put(activity, idx);
                }
                idx++;
            }
            if(pickUpToIndexMap.size() != droppedToIndexMap.size()) {
                return false;
            }

            for(Map.Entry<String, Integer> pickedUpItem : pickUpToIndexMap.entrySet()) {
                String activity = pickedUpItem.getKey();
                int pickedUpIndex = pickedUpItem.getValue();
                String deliveryActivity = "D" + activity.substring(1);
                if(!droppedToIndexMap.containsKey(deliveryActivity) || droppedToIndexMap.get(deliveryActivity) < pickedUpIndex) {
                    return false;
                }
            }
            return true;
        }

    }
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.getAllValidActivity(3).size());
        //System.out.println(Solution.isValidActivityList(Arrays.asList("P1")));
    }

}
