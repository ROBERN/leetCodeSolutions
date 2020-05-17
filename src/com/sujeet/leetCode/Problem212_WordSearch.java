package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.*;

public class Problem212_WordSearch {
    private static final int SIZE = 27;
    class TrieNode {
        char ch;
        TrieNode parent;
        TrieNode[] links;
        boolean isEnd;
        TrieNode(TrieNode parent, char c) {
            this.parent = parent;
            ch = c;
            links = new TrieNode[SIZE];
            isEnd = false;
        }

        boolean isEnd() {
            return isEnd;
        }

        void setEndChar() {
            isEnd = true;
        }

        boolean hasLink(char c) {
            return links[c-'a'] != null;
        }

        char getChar() {
            return ch;
        }

        void addLink(TrieNode node) {
            if(node == null)
                return;
            int idx = node.getChar() -'a';
            links[idx] = node;
            if(node.getString().equals("abc")) {
                throw new RuntimeException();
            }
        }
        TrieNode getLink(char c) {
            return links[c-'a'];
        }

        TrieNode getParent() {
            return parent;
        }

        String getString() {
            StringBuilder sb = new StringBuilder();
            TrieNode curr = this;
            while(curr != null) {
                if (curr.getChar() != '#')
                    sb.append(curr.getChar());
                curr = curr.getParent();
            }
            sb.reverse();
            return sb.toString();
        }

    }

    class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode(null, '#');
        }

        TrieNode getRoot() {
            return root;
        }

        void insertWord(String str) {
            TrieNode curr = root;
            for(char c : str.toCharArray()) {
                if(curr.hasLink(c)) {
                    curr = curr.getLink(c);
                } else {
                    TrieNode newNode = new TrieNode(curr, c);
                    curr.addLink(newNode);
                    curr = newNode;
                }
            }
            curr.setEndChar();
        }

    }

    Trie trie = new Trie();
    char[][] board;
    int rowSz;
    int colSz;
    public List<String> findWords(char[][] board, String[] words) {
        rowSz = board.length;
        if(rowSz == 0) return Collections.emptyList();
        colSz = board[0].length;

        for(String word : words) {
            trie.insertWord(word);
        }
        this.board = board;
        return getMatches();
    }

    private List<String> getMatches() {
        Set<String> results = new HashSet<>();
        for(int r = 0; r < rowSz; r++) {
            for(int c = 0; c < colSz; c++) {
                TrieNode node = trie.getRoot();
                results.addAll(search(node, r, c, new HashSet<>(), ""));
            }
        }
        return new ArrayList<>(results);
    }

    private Set<String> search(TrieNode curr, int r, int c, Set<Pair<Integer, Integer>> visited, String str) {
        Set<String> results = new HashSet<>();
        if(curr.isEnd()) {
            results.add(str);
        }
        Pair<Integer, Integer> pair = new Pair<>(r, c);
        if(r < 0 || r >= rowSz || c < 0 || c >= colSz || visited.contains(pair))
            return results;
        visited.add(pair);
        char needed = board[r][c];

        if(curr.hasLink(needed)) {
            String newString = str + needed;
            curr = curr.getLink(needed);

            results.addAll(search(curr, r, c+1, visited, newString));
            results.addAll(search(curr, r+1, c, visited, newString));
            results.addAll(search(curr, r-1, c, visited, newString));
            results.addAll(search(curr, r, c-1, visited, newString));
        }

        // backtrack
        visited.remove(pair);
        return results;
    }

    public static void main(String[] args) {
        Problem212_WordSearch obj = new Problem212_WordSearch();
        System.out.println(obj.findWords(new char[][]
                {{'a','b'},{'c','d'}},
                new String[]{"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"}
        ));
    }
}
