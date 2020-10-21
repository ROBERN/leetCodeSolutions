package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Problem929_UniqueEmails {
    public int numUniqueEmails(String[] emails) {
        Set<Pair<String, String>> emailIds = new HashSet<>();

        for (String email : emails) {
            String[] pair = email.split("@");
            if (pair.length != 2)
                throw new IllegalArgumentException();
            String name = pair[0].split("\\+")[0];
            StringBuilder nameBuilder = new StringBuilder();
            for (char c : name.toCharArray()) {
                if (c == '.')
                    continue;
                nameBuilder.append(c);
            }
            String domain = pair[1];
            emailIds.add(new Pair<>(nameBuilder.toString(), domain));
        }
        return emailIds.size();
    }
}
