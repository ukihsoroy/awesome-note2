package org.ko.structure.tree;

public class BinarySearchTree<K, V> {

    private int count;

    private Node root;

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    int size () {
        return count;
    }

    boolean isEmpty () {
        return count == 0;
    }

    private class Node {

        private int count;

        private K key;

        private V value;

        private Node node;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
