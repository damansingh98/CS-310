/*
Name: Damandeep Singh
course: CS 310
Account - cssc3255
 */

// Implement a red black tree with all functionalities


package data_structures;

public class RedBlackTree implements RedBlackTreeInterface {
//Node Class
    public class Node implements NodeInterface {
        //instance variables
        int data;
        NodeInterface left;
        NodeInterface right;
        NodeInterface parent;
        int NodeColor; //color of Node

        public Node(int data) {
            this.data = data;
            parent = null;
            left = null;
            right = null;
            NodeColor = RED;
        }

        //returns the color number
        public int getColor() {
            return NodeColor;
        }
        //set's the color of node
        public void setColor(int color) {
            NodeColor = color;
        }
        //searches the node with value
        @Override
        public boolean contains(int value) {
            NodeInterface currNode = parent;
            while (currNode != null) {
                if (currNode.getValue() == value) {
                    return true;

                } else if (value < currNode.getValue()) {
                    currNode.setLeftChild(currNode);

                } else {
                    currNode.setRightChild(currNode);

                }

            }

            return false;
        }
    //adds the node to the correct position
        @Override
        public void add(int value) {
            Node newNode = new Node(value);
            if (root == null) {
                root = newNode;
            } else {
                NodeInterface currNode = root;
                while (true) {
                    if (value < currNode.getValue()) {
                        if (currNode.getLeftChild() == null) {
                            currNode.setLeftChild(newNode);
                            break;

                        } else {
                            currNode = currNode.getLeftChild();
                        }

                    } else {
                        if (currNode.getRightChild() == null) {
                            currNode.setRightChild(newNode);
                            break;

                        } else {
                            currNode = currNode.getRightChild();
                        }
                    }
                }
            }
        }

        @Override
        public int getValue() {
            return data;
        }

        @Override
        public void setLeftChild(NodeInterface n) {
            left =  n;

        }

        @Override
        public NodeInterface getLeftChild() {
            return left;
        }

        @Override
        public void setRightChild(NodeInterface n) {
            right = n;
        }

        @Override
        public NodeInterface getRightChild() {
            return right;
        }

        @Override
        public NodeInterface getParent() {
            return parent;
        }

        @Override
        public void setParent(NodeInterface n) {
            parent =  n;
        }
    }


    private NodeInterface root;

    public RedBlackTree() {
        root = null;
    }

//adds the node to the tree
    @Override
    public void add(int value) {
        NodeInterface previous;
        Node newNode = new Node(value);
//uses private search method for position
        previous = search(value, newNode);
        newNode.add(value);
        newNode.setParent(previous);

        if (previous == null) {
            root = newNode;
        } else if (value < previous.getValue()){
            previous.setLeftChild(newNode);}
        else {
            previous.setRightChild(newNode);
        }
        root.setColor(BLACK);

    }


    @Override
    public void remove(int value) {
        //search for node to be deleted
        NodeInterface temp = search(value, root);

    }

//private search method which returns the specified node
    private NodeInterface search(int value, NodeInterface node) {
        if (node == null)
            return null;
        if (value == node.getValue())
            return  node;
        else if (value < node.getValue()) {
            contains(value, node.getLeftChild());
        }
        else {
            contains(value, node.getRightChild());
        }
        return  node;
    }


//contains methods
    @Override
    public boolean contains(int value) {
        boolean flag = false;
        if(root != null)
            flag = contains(value, root);

        return flag;
    }

    private boolean contains(int value, NodeInterface node){
        if(node == null)
            return false;
        if(value == node.getValue())
            return true;
        if(value < node.getValue())
            return contains(value, node.getLeftChild());
        return contains(value,node.getRightChild());
    }

//retrieves the value
    @Override
    public NodeInterface get(int value) {
        NodeInterface currNode = root;

        while(currNode != null){
            if(currNode.getValue() == value) {
                return currNode;
            }
            else if(value < currNode.getValue()) {
                currNode = currNode.getLeftChild();
            }
            else {
                currNode = currNode.getRightChild();
            }
        }
        return null;
    }

}
