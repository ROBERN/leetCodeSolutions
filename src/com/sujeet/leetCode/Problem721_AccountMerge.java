package com.sujeet.leetCode;

import java.util.*;

public class Problem721_AccountMerge {
    private static class UnionSet {
        int parent;
        int rank;
        String userName;
        UnionSet(String name, int p, int r) {
            parent = p;
            userName = name;
            rank = r;
        }

    }

    Map<String, Integer> emailIdx;

    private static class Account {
        String userName;
        Set<String> emails;
        Account(String userName, List<String> emailIds) {
            this.userName = userName;
            emails = new HashSet<>(emailIds);
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionSet[] sets = createUnionSet(accounts);

        int index = 0;
        emailIdx = new HashMap<>();
        for(List<String> account : accounts) {
            for(int idx = 1; idx < account.size(); idx++) {
                String email = account.get(idx);
                if(emailIdx.containsKey(email) && idx != emailIdx.get(email)) {
                    union(sets, index, emailIdx.get(email));
                }
                emailIdx.put(account.get(idx), index);
            }
            index++;
        }

        flatten(sets);
        Account[] tempResult = new Account[accounts.size()];
        for(int idx = 0; idx < sets.length; idx++) {
            int indexToAdd = sets[idx].parent;
            List<String> account = accounts.get(idx);
            if(tempResult[indexToAdd]  == null) {
                tempResult[indexToAdd] = new Account(account.get(0), account.subList(1, account.size()));
            } else {
                tempResult[indexToAdd].emails.addAll(account.subList(1, account.size()));
            }
        }

        List<List<String>> result = new ArrayList<>();
        for(Account acc : tempResult) {
            if(acc == null) {
                continue;
            }
            List<String> item = new ArrayList<>();
            item.add(acc.userName);
            List<String> emails = new ArrayList<>(acc.emails);
            Collections.sort(emails);
            item.addAll(emails);
            result.add(item);
        }
        return result;
    }

    private void flatten(UnionSet[] sets) {
        for(int idx = 0; idx < sets.length; idx++) {
            find(sets, idx);
        }
    }

    private int find(UnionSet[] sets, int i) {
        if(sets[i].parent != i) {
            sets[i].parent = find(sets, sets[i].parent);
        }
        return sets[i].parent;
    }

    private void union(UnionSet[] sets, int x, int y) {
        int xRoot = find(sets, x);
        int yRoot = find(sets, y);

        if(sets[xRoot].rank < sets[yRoot].rank) {
            sets[xRoot].parent = yRoot;
        } else if(sets[yRoot].rank < sets[xRoot].rank) {
            sets[yRoot].parent = xRoot;
        } else {
            sets[xRoot].parent = yRoot;
            sets[yRoot].rank++;
        }
    }


    private UnionSet[] createUnionSet(List<List<String>> accounts) {
        UnionSet[] sets = new UnionSet[accounts.size()];
        int idx = 0;
        for(List<String> account : accounts) {
            sets[idx] = new UnionSet(account.get(0), idx, 0);
            idx++;
        }
        return sets;
    }

    public static void main(String[] args) {
        Problem721_AccountMerge obj = new Problem721_AccountMerge();
        System.out.println(obj.accountsMerge(Arrays.asList(
                Arrays.asList("Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"),
                Arrays.asList("Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"),
                Arrays.asList("Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"),
                Arrays.asList("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"),
                Arrays.asList("Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co")
        )));
    }
}
