package com.sujeet.Practise;

class StreamChecker {

    /** Initialize your data structure here. */

    class TrieNode {
        private int childrenCount = 26;
        private boolean isEnd = false;
        private TrieNode[] children;
        public TrieNode() {
            children = new TrieNode[childrenCount];
        }
        boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }
        TrieNode get(char ch) {
            return children[ch - 'a'];
        }
        void setEnd() {
            isEnd = true;
        }
        boolean isEnd() {
            return isEnd;
        }

        void put(char ch, TrieNode node) {
            children[ch-'a'] = node;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // search a prefix or whole key in trie and
        // returns the node where search ends
        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter)) {
                    node = node.get(curLetter);
                } else {
                    return null;
                }
            }
            return node;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = trie.root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, trie.root);
    }

    private boolean match(char[] chs, int k, TrieNode node) {
        if (node.isEnd) {
            return true;
        }
        if (k >= chs.length)
            return false;
        if (chs[k] != '.') {
            return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (match(chs, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Trie trie;
    StringBuilder sb;
    public StreamChecker(String[] words) {
        trie = new Trie();
        sb = new StringBuilder();
        for(String word : words) {
            addWord(reverse(word));
        }
    }

    public boolean query(char letter) {
        sb = new StringBuilder(letter + sb.toString());
        return search(sb.toString());
    }

    String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{
                "abaa", "abaab", "aabbb", "bab", "ab"
        });
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('b'));

        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('b'));
    }
}
