package com.example.demo;
public class DynArray<T> {
    private Node<T> firstNode = null;
    private Node<T> lastNode = null;
    
    
    
    public boolean isEmpty() {
        return firstNode == null;
    }
    
    
    public int getLength() {
        Node currNode = firstNode;
        int counter = 0;
        while(currNode != null) {
            counter++;
            currNode = currNode.getNext();
        }
        return counter;
    }
    
    
    public void append(T element) {
        Node<T> newNode = new Node<T>(element);
        if(isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
            return;
        }
        newNode.setPrevious(lastNode);
        lastNode.setNext(newNode);
        lastNode = newNode;
    }

    public T getItem(int index) {
        if (!isEmpty() && index >= 0 && index < getLength()) {
            return getNode(index).getContent();
        }
        return null;
    }
    
    private Node<T> getNode(int index) {
        Node<T> currNode = firstNode;
        
        for(int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }
        return currNode;
    }
    
    public void setItem(int index, T content) {
        if (!isEmpty() || (index >= 0 && index < getLength())) {
            getNode(index).setContent(content);    
        }
    }
    
    public void insertAt(int index, T content) {
        Node<T> newNode = new Node<T>(content);
        
        if(isEmpty() || index >= getLength()) {
            append(content);
            return;
        }
        
        if(index == 0) {
            newNode.setNext(firstNode);
            firstNode.setPrevious(newNode);
            firstNode = newNode;
            return;
        }
        
        Node<T> leftNode = getNode(index - 1);
        Node<T> rightNode = getNode(index);
        
        newNode.setNext(rightNode);
        newNode.setPrevious(leftNode);
        leftNode.setNext(newNode);
        rightNode.setPrevious(newNode);
    }
    
    public void delete(int index) {
        if(isEmpty() || getLength() == 1) {
            firstNode = null;
            lastNode = null;
            return;
        }
        
        if(index == 0) {
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);
            return;
        }
        
        if(index == getLength() - 1) {
            lastNode = getNode(index - 1);
            lastNode.setNext(null);
            return;
        }
        
        Node<T> leftNode = getNode(index - 1);
        Node<T> rightNode = getNode(index + 1);
        
        leftNode.setNext(rightNode);
        rightNode.setPrevious(leftNode);
    }
}
