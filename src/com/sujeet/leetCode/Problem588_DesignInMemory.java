package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Problem588_DesignInMemory {

        private static class FileNode {
            String name;
            boolean isFile;
            String content;

            TreeMap<String, FileNode> children;

            FileNode(String name, boolean isFile, String content) {
                this.name = name;
                this.isFile = isFile;
                this.content = content;
                children = new TreeMap<>();
            }

        }

        private final FileNode root;
        public Problem588_DesignInMemory() {
            root = new FileNode("", false, "");
        }

        public List<String> ls(String path) {
            String[] paths = path.split("/");
            FileNode node = root;
            int idx = 1;
            while(node != null && idx < paths.length) {
                String dir = paths[idx++];
                node = node.children.get(dir);
            }

            if(node == null)
                return Collections.emptyList();

            return new ArrayList<>(node.children.keySet());
        }

        public void mkdir(String path) {
            String[] paths = path.split("/");
            FileNode node = root;
            int idx = 1;
            while(idx < paths.length) {
                String dir = paths[idx++];
                node = node.children.computeIfAbsent(dir, empty -> new FileNode(dir, false, ""));

            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] paths = filePath.split("/");
            FileNode node = root;
            int idx = 1;
            while(idx < paths.length-1) {
                String dir = paths[idx++];
                node = node.children.computeIfAbsent(dir, empty -> new FileNode(dir, false, ""));
            }
            String fileName = paths[paths.length-1];
            String existingContent = node.children.getOrDefault(fileName, new FileNode(fileName, true, "")).content;
            node.children.put(fileName, new FileNode(fileName, true, existingContent+content));
        }

        public String readContentFromFile(String filePath) {
            String[] paths = filePath.split("/");
            FileNode node = root;
            int idx = 1;
            while(node != null && idx < paths.length) {
                String dir = paths[idx++];
                node = node.children.get(dir);
            }
            if(node != null && node.isFile) {
                return node.content;
            }
            return "";
        }

        public static void main(String[] args) {
            Problem588_DesignInMemory obj = new Problem588_DesignInMemory();
            obj.mkdir("/goowmfn");
            System.out.println(obj.ls("/goowmfn"));
            System.out.println(obj.ls("/"));
            obj.mkdir("/z");
            System.out.println(obj.ls("/"));
            System.out.println(obj.ls("/"));
            obj.addContentToFile("/goowmfn/c", "shetopcy");
            System.out.println(obj.ls("/z"));
            System.out.println(obj.ls("/goowmfn/c"));
            System.out.println(obj.ls("/goowmfn"));
        }
    }



/**
 * ["FileSystem","mkdir",     "ls",         "ls","mkdir","ls",  "ls",  "addContentToFile",      "ls","ls","ls"]
 * [[],          ["/goowmfn"],["/goowmfn"],["/"],["/z"],  ["/"],["/"],["/goowmfn/c","shetopcy"],["/z"],["/goowmfn/c"],["/goowmfn"]]
 *
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 *
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
