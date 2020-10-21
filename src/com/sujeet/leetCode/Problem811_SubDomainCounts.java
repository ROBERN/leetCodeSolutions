package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem811_SubDomainCounts {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> visits = new HashMap<>();
        for (String coDomain: cpdomains) {
            String[] parts = coDomain.split(" ");
            int count = Integer.parseInt(parts[0]);

            String str = parts[1];
            while(true){
                visits.put(str, visits.getOrDefault(str, 0)+count);
                int idx = str.indexOf('.');
                if (idx == -1)
                    break;
                str = str.substring(idx+1);
            }
        }
        List<String> strs = new ArrayList<>();
        for (Map.Entry<String, Integer> mp : visits.entrySet()) {
            strs.add(mp.getValue() + " " + mp.getKey());
        }
        return strs;
    }

    public static void main(String[] args) {
        Problem811_SubDomainCounts obj = new Problem811_SubDomainCounts();
        System.out.println(obj.subdomainVisits(new String[]{"9001 discuss.leetcode.com"}));
    }
}
