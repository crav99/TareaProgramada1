/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author allanvz
 */
public class Stack<T> {
    
    public class Node<T> {

        //atributos

        private T element;
        private Node<T> next;

        //Constructores
        public Node() {
            this.element = null;
            this.next = null;
        }

        public Node(T element) {
            this.element = element;
            this.next = null;
        }

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }

        //métodos

        public T getElement() {
            return this.element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
        
    }
    
    private Node<T> top;
    private int size;
    
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T element) {
        this.top = new Node<>(element, this.top);
        this.size++;
    }

    public T pop(){
        if (this.top == null) {
            System.out.println("Stack is empty");
            return null;
        }
        T temp = this.top.getElement();
        this.top = this.top.getNext();
        this.size--;
        return temp;
    }

    public T top(){
        if (this.top == null) {
            System.out.println("Stack is empty");
            return null;
        }
        return this.top.getElement();
    }

    public int size(){
        return this.size;
    }

    public void clear(){
        this.top = null;
        this.size = 0;
    }

    public String toString(){
        String result = "**LStack**\n";
        Node<T>temp = top;
        while(temp != null){
            result+= temp.getElement() + "\n";
            temp = temp.getNext();
        }
        return result;
    }
    
}
