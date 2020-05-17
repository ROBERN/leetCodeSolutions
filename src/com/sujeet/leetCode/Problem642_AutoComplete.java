package com.sujeet.leetCode;

import java.util.*;

public class Problem642_AutoComplete {
    private static final int SIZE = 27;
    class TrieNode {
        TrieNode[] links;
        private boolean isEnd = false;
        int weight = 0;
        int endWeight = 0;

        public char getChar() {
            return c;
        }

        private char c;
        private TrieNode parent = null;
        TrieNode(char ch) {
            links = new TrieNode[SIZE];
            c = ch;
        }

        void setEnd() {
            isEnd = true;
        }

        boolean getEnd() {
            return isEnd;
        }

        public TrieNode getParent() {
            return parent;
        }
        public void setParent(TrieNode parent) {
            this.parent = parent;
        }
        TrieNode get(char c) {
            if(c == '#')
                return null;
            if(c == ' ')
                return links[26];
            return links[c-'a'];
        }

        void put(char c, TrieNode node) {
            if(c == ' ') {
                links[26] = node;
                return;
            }
            links[c - 'a'] = node;
        }

        int getWeight() {
            return weight;
        }
        void incrementWeight() {
            weight++;
        }

        String getString() {
            StringBuilder sb = new StringBuilder();
            TrieNode currentNode = this;
            while(currentNode != null) {
                if (currentNode.getChar() != '#')
                    sb.append(currentNode.getChar());
                currentNode = currentNode.getParent();
            }
            return sb.reverse().toString();
        }
    }

    class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode('#');
        }

        void insert(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                TrieNode link  = node.get(c);
                if(link == null) {
                    link = new TrieNode(c);
                    link.weight = 1;
                    link.setParent(node);
                    node.put(c, link);
                } else {
                    link.incrementWeight();
                }
                node = node.get(c);
            }
            node.setEnd();
            node.endWeight++;
        }

        public List<TrieNode> getEndNodesWithPrefix(String prefix, int max) {
            TrieNode node = getMatchingPrefixNode(prefix);
            if(node == null) {
                return Collections.emptyList();
            }
            PriorityQueue<TrieNode> maxHeap = new PriorityQueue<>((a,b) -> {
                if (a.endWeight != b.endWeight)
                    return b.endWeight - a.endWeight;
                else
                    return a.getString().compareTo(b.getString());
            });
            addLeavesWithMaxCount(node, max, maxHeap);
            List<TrieNode> result = new ArrayList<>();
            for (int i = 0; i < max && !maxHeap.isEmpty(); i++)
                result.add(maxHeap.poll());
//            List<String> hotStrings = new ArrayList<>();
//            for (TrieNode node1 : result) {
//                hotStrings.add(node1.getString());
//            }
            return result;
        }

        public List<String> getFrequentStringsWithPrefix(String prefix, int max) {
            List<TrieNode> frequentNodes = getEndNodesWithPrefix(prefix, max);
            List<String> hotStrings = new ArrayList<>();
            for (TrieNode node : frequentNodes) {
                hotStrings.add(node.getString());
            }
            return hotStrings;
        }

        private void addLeavesWithMaxCount(TrieNode node, int max, PriorityQueue<TrieNode> maxHeap) {
            if (node == null)
                return;
            int seen = 0;
            if (node.isEnd) {
                maxHeap.add(node);
                seen++;
            }
            TrieNode[] children = Arrays.copyOf(node.links, node.links.length);
            Arrays.sort(children, (a, b) -> {
                if (a == null || b == null) {
                    if (a== null)
                        return 1;
                    else
                        return -1;
                }
                if(b.weight != a.weight)
                    return b.weight - a.weight;
                return b.getString().compareTo(a.getString());
            });
            for(TrieNode child : children) {
                if (child != null && child.weight > 0) {
                    addLeavesWithMaxCount(child, max - seen, maxHeap);
                }
            }
        }

        private TrieNode getMatchingPrefixNode(String prefix) {

            TrieNode node = root;
            for(char c : prefix.toCharArray()) {
                if(node.get(c) == null) {
                    return null;
                }
                node = node.get(c);
            }
            return node;
        }
    }

    private Trie trie;
    private StringBuilder currentString;
    public Problem642_AutoComplete(String[] sentences, int[] times) {
        trie = new Trie();
        for (int i = 0 ; i < sentences.length; i++) {
            for (int freq = 0 ; freq < times[i]; freq++)
                trie.insert(sentences[i]);
        }
        currentString = new StringBuilder();
    }


    public List<String> input(char c) {
        if (c!= '#')
            currentString.append(c);
        else {
            trie.insert(currentString.toString());
            currentString = new StringBuilder();
            return Collections.emptyList();
        }
        return trie.getFrequentStringsWithPrefix(currentString.toString(), 3);
    }

//    public static void main(String[] args) {
//        Problem642_AutoComplete obj = new Problem642_AutoComplete(
//                new String[] {"i love you", "island", "ironman", "i love leetcode"},
//                new int[]{5,3,2,2}
//        );
//        System.out.println(obj.input('i'));
//        System.out.println(obj.input(' '));
//        System.out.println(obj.input('a'));
//        System.out.println(obj.input('#'));
//    }

    public static void main(String[] args) {
        Problem642_AutoComplete obj = new Problem642_AutoComplete(
                new String[] {"abc", "abbc", "a"},
                new int[]{3,3,3}
        );
        System.out.println(obj.input('b'));
        System.out.println(obj.input('c'));
        System.out.println(obj.input('#'));
        System.out.println(obj.input('b'));
        System.out.println(obj.input('c'));
        System.out.println(obj.input('#'));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('b'));
        System.out.println(obj.input('c'));
        System.out.println(obj.input('#'));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('b'));
        System.out.println(obj.input('c'));
        System.out.println(obj.input('#'));

    }
}
