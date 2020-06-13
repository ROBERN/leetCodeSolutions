package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem315_CountSmallerToRight {
    private List<Integer> list= new ArrayList<>();
    public List<Integer> countSmaller(int[] nums) {
        int[] arr=new int[nums.length];
        for(int i=nums.length-1;i>=0;i--){
            arr[i]= binarySearch(nums[i],0,list.size());
        }
        List<Integer> ans= new ArrayList<>();
        for (int value : arr) {
            ans.add(value);
        }

        return ans;
    }
    private int binarySearch(int x, int s, int h){
        //binary search to find out sorted position of element
        int mid= s+(h-s)/2;
        if(s>=h){
            list.add(mid,x);
            return mid;
        }
        if(x<=list.get(mid)){
            return binarySearch(x,s,mid);
        }else{
            return binarySearch(x,mid+1,h);
        }

    }
}
