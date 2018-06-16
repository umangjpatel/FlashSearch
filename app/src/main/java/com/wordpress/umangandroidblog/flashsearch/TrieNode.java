package com.wordpress.umangandroidblog.flashsearch;

import java.util.LinkedList;

/**
 * Class representing a node in the trie data structure.
 */

public class TrieNode {

    //Represents the data in the node.
    char data;

    //Defines the end of the word.
    boolean isEnd;

    //Counts the position of node.
    int count;

    //Represents its child nodes
    LinkedList<TrieNode> childList;

    /**
     * Creates a node with appropriate details.
     *
     * @param c is the data of corresponding node.
     */
    public TrieNode(char c) {
        childList = new LinkedList<>();
        isEnd = false;
        data = c;
        count = 0;
    }

    /**
     * Retrieves the child node based on the data of the node
     *
     * @param c is the data of the node
     * @return the child node you requested.
     */
    public TrieNode getChild(char c) {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }
}
