package com.wordpress.umangandroidblog.flashsearch;

/**
 * Class that implements trie data structure.
 */

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode(' ');
    }
    /**
     * Inserts a word in the trie.
     * @param word to be divided into characters and inserted in tree
     */
    public void insert(String word) {
        //If word already exists, return it
        if (search(word))
            return;
        //Make current node as root node of trie
        TrieNode current = root;
        //Iterate over each character of word
        for (char ch : word.toCharArray()) {
            //Get child trie nodes of the current node
            TrieNode child = current.getChild(ch);
            //If child node exists and is equal to character c then make it current node and increment the count
            if (child != null) {
                current = child;
            } else {
                //If child node doesn't exist, then create a new trie node with character c and add it to the current node childlist and change current node to newly created trie node.
                current.childList.add(new TrieNode(ch));
                current = current.getChild(ch);
            }
            current.count++;
        }
        //When end of word reached, mark isEnd true
        current.isEnd = true;
    }

    //TODO: Check whether the word is completed, possible words or found..
    /**
     * Method for searching a word
     * @param word is the word to be searched
     * @return whether the word exists or not
     */

    public boolean search(String word) {
        TrieNode current = root;
        //For each character of word, check if child node exists for it
        for (char ch : word.toCharArray())
        {
            //if child node doesn't exist, return false
            if (current.getChild(ch) == null)
                return false;
            else
                //If character exists, repeat above step.i.e check if its child node exists
                current = current.getChild(ch);
        }
        //When you reach the end of string and current.isEnd is true, return true else return false
        return current.isEnd;
    }

}
