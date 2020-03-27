package com.sujeet.Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// "static void main" must be defined in a public class.
class BinaryTrieNode {
    private char ch;

    // as we only have binary 0 and 1, we can restrict our trie node to have only two children
    BinaryTrieNode left;
    BinaryTrieNode right;

    public BinaryTrieNode(char c) {
        ch = c;
    }

    public void set(char ch, BinaryTrieNode node) {
        if (ch == '0') {
            left = node;
        } else if (ch == '1') {
            right = node;
        }
    }

    public BinaryTrieNode get(char ch) {
        if (ch == '0') {
            return left;
        } else if (ch == '1') {
            return right;
        }
        return null;
    }
}

class BinaryTrie {
    private BinaryTrieNode root;
    private int maxDiff;

    public BinaryTrie(List<String> binaries) {
        root = new BinaryTrieNode('\0');
        maxDiff = 0;

        // insert each binary string into Trie
        for (String str : binaries) {
            BinaryTrieNode curr = root;

            for (char ch : str.toCharArray()) {
                BinaryTrieNode child = curr.get(ch);

                if (child == null) {
                    child = new BinaryTrieNode(ch);
                    curr.set(ch, child);
                }

                curr = child;
            }
        }
    }

    public int getMaxDiff() {
        getMaxDepth(root);

        return maxDiff;
    }

    // helper method to calculate depth of a trie node.
    private int getMaxDepth(BinaryTrieNode root) {
        if (root == null)
            return 0;

        // calculate left child depth
        int leftDepth = getMaxDepth(root.left);

        // calculate rightt child depth
        int rightDepth = getMaxDepth(root.right);

        // this check is imp here
        // we only need to update the max, iff both left AND right depths are greater than 0.
        // if we dont do this, here's is an example where it would fail:
        // two strings are 10100, 10111. The actual ans here is 4.
        // but at root node, the left depth is 0 and right depth is 5. left depth is 0 as we dont have a left child (node with char 0 at root as both strings start with 1)
        // right depth is 5. so max would be left + right =5 which is incorrect.
        // so we only need to update max if both left and right depths are > 0.
        // if both are > 0, it means that the curr node splits two diff binary strings and this is the case where we want to add the lengths and update the max.
        // in the above example, the strings split at 3rd index 1.

        if (leftDepth > 0 && rightDepth > 0) {
            maxDiff = Math.max(maxDiff, leftDepth + rightDepth);
        }

        // send max depth between left and right to upper recursive level
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

class Main {
    public static void main(String[] args) {
        List<String> binaries = Arrays.asList("1011100", "1011011","1001111");

        BinaryTrie trie = new BinaryTrie(binaries);

        System.out.println(trie.getMaxDiff()); // gives 10 (1011100, 1001111) differ by 10.

        binaries = Arrays.asList("101", "111","000");

        trie = new BinaryTrie(binaries);

        System.out.println(trie.getMaxDiff()); // return 6 (101, 000)
    }
}