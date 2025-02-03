package com.example.demo;
public class Node<T>
{
    
    // Attribute
    private Node<T> nextNode = null;
    private Node<T> previousNode = null;
    private T content;
    
    
    // Konstruktor  
    public Node(T content) {
        this.content = content;
    }

    // Methoden
    public Node<T> getNext() {
        return nextNode;
    }
    
    public void setNext(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
    
    public Node<T> getPrevious() {
        return previousNode;
    }
    
    public void setPrevious(Node<T> previousNode) {
        this.previousNode = previousNode;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }
}
