package com.company;

public class BPTree {

    //Points to root node whether or not.
    private Node root;

    //The maximum number of keys in a leaf node.
    private static int M;

    //The maximum number of keys in an interior node.
    private static int N;

    //The constructor called to created the tree
    public BPTree(int n) {
        this(n, n);
    }

    //Where the tree is created
    private BPTree(int m, int n){
        System.out.println("Making tree");
        M = m;
        N = n;
        root = new LeafNode();
    }

    /*Inserting an element into the tree.
    * The tree takes the patent id as well as the index of the first row of the double array that the scan id is stored in.*/
    public void insert(int key, int value) {
        System.out.println("inserting key = " + key);
        Split result = root.insert(key, value);
        if(result != null){
            InterNode newRoot = new InterNode();
            newRoot.numKey = 1;
            newRoot.keys[0] = result.key;
            newRoot.children[0] = result.left;
            newRoot.children[1] = result.right;
            root = newRoot;
        }
    }

    /*Looking through the tree for a element that may be in the tree.
    * Takes the patent id that serves as the key and returns the index of where the scan id is stored in the double
    * array. The method returns -1 if the key is not in the tree*/
    public int search(int key) {
        Node node = root;
        while (node instanceof BPTree.InterNode) {
            InterNode inter = (InterNode) node;
            int index = inter.getLocation(key);
            node = inter.children[index];
        }

        LeafNode leaf = (LeafNode) node;
        int index = leaf.getLocation(key);
        if(index < leaf.numKey && leaf.keys[index] == key) {
            return leaf.values[index];
        }
        return -1;
    }

    //Print the keys that are in the tree
    public void print() {
        root.print();
    }




    abstract class Node {

        protected int numKey; //number of keys

        protected int[] keys; //The array of keys

        //Method that returns where the key of an inserted element is
        abstract public int getLocation(int key);

        // if there is a split the method returns the split info, else it returns null.
        abstract public Split insert(int key, int value);

        abstract public void print();
    }

    class LeafNode extends Node {

        final int[] values = new int[N];
        public LeafNode() {
            keys = new int[N];
        }

        @Override
        public int getLocation(int key) {
            for (int i = 0; i < numKey; i++) {
                if(keys[i] >= key) {
                    return i;
                }
            }
            return numKey;
        }

        @Override
        public Split insert(int key, int value) {
            int i = getLocation(key);
            //If the node if full then split it
            if (this.numKey == M) {
                int mid = (M+1)/2;
                int sNum = this.numKey - mid;
                LeafNode sibling = new LeafNode();
                sibling.numKey = sNum;
                System.arraycopy(this.keys, mid, sibling.keys, 0, sNum);
                System.arraycopy(this.values, mid, sibling.values, 0, sNum);
                this.numKey = mid;
                if (i < mid) {
                    // The nodes that go to the left child
                    this.insertNonFull(key, value, i);
                } else {
                    // The nodes that go to the right child
                    sibling.insertNonFull(key, value, i-mid);
                }
                // Notify the parent about the split and make the right's key >= result.key
                Split result = new Split(sibling.keys[0],this, sibling);
                return result;
            } else {
                // Node was not full so there was no split
                this.insertNonFull(key, value, i);
                return null;
            }
        }

        //Used if a split is has not occured or after it has
        private void insertNonFull(int key, int value, int idx) {

            if (idx < numKey && keys[idx] == key) {
                // This is used if there is a duplicate... just replaces the existing value
                values[idx] = value;
            } else {
                // A unique key was entered.
                System.arraycopy(keys, idx, keys, idx+1, numKey-idx);
                System.arraycopy(values, idx, values, idx+1, numKey-idx);

                keys[idx] = key;
                values[idx] = value;
                numKey++;
            }
        }

        @Override
        public void print() {
            System.out.println("LeafNode h==0");
            for (int i = 0; i < numKey; i++){
                System.out.println(keys[i]);
            }
        }
    }

    class InterNode extends Node {

        final Node[] children = new Node[N+1];

        public InterNode(){
            keys = new int[N];
        }
        @Override
        public int getLocation(int key) {
            for (int i = 0; i < numKey; i++) {
                if (keys[i] > key) {
                    return i;
                }
            }
            return numKey;
        }

        @Override
        public Split insert(int key, int value) {

            //If the node is full, split it, if not just insert it into the node
            if (this.numKey == N) {
            int mid = (N+1)/2;
            int sNum = this.numKey - mid;
            InterNode sibling = new InterNode();
            sibling.numKey = sNum;
            System.arraycopy(this.keys, mid, sibling.keys, 0, sNum);
            System.arraycopy(this.children, mid, sibling.children, 0, sNum+1);

            //take the middle key to be in the new node
            this.numKey = mid-1;


            Split result = new Split(this.keys[mid-1],this, sibling);

            // Inserting the elements into the correct child
            if (key < result.key ) {
                this.insertNonFull(key, value);
            } else {
                sibling.insertNonFull(key, value);
            }
            return result;

        } else {
            this.insertNonFull(key, value);
            return null;
        }
        }

        private void insertNonFull(int key, int value) {

            int idx = getLocation(key);
            Split result = children[idx].insert(key, value);

            if (result != null) {
                if (idx == numKey) {
                    // Insertion at the rightmost key
                    keys[idx] = result.key;
                    children[idx] = result.left;
                    children[idx+1] = result.right;
                    numKey++;
                } else {
                    // Insertion not at the rightmost key
                    //shift i>idx to the right
                    System.arraycopy(keys, idx, keys, idx+1, numKey-idx);
                    System.arraycopy(children, idx, children, idx+1, numKey-idx+1);

                    children[idx] = result.left;
                    children[idx+1] = result.right;
                    keys[idx] = result.key;
                    numKey++;
                }
            }

        }

        @Override
        public void print() {

            System.out.println("InertiorNode h==?");
            for (int i = 0; i < numKey; i++){
                children[i].print();
                System.out.print('>');
                System.out.println(keys[i]);
            }
            children[numKey].print();
        }
    }


    class Split {
        public final int key;
        public final BPTree.Node left;
        public final BPTree.Node right;

        public Split(int k, BPTree.Node l, BPTree.Node r) {
            key = k; left = l; right = r;
        }
    }
}
