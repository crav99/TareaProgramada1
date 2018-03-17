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
public class DoublyLinkedList<T> {
    
    private class Node<T> {

        //atributos

        private T element;
        private Node<T> next;
        private Node<T> previous;

        //Constructores
        public Node() {
            this.element = null;
            this.next = null;
            this.previous = null;
        }

        public Node(T element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        public Node(T element, Node<T> previous, Node<T> next) {
            this.element = element;
            this.previous = previous;
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

        public Node<T> getPrevious() {
            return this.previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }
    }

    //atributos LinkedList
    private Node<T> head;
    private Node<T> current;
    private Node<T> tail;
    private int position;
    private int size;

    public DoublyLinkedList(){
        this.head = new Node<T>();
        this.current = this.head;
        this.tail = this.head;
        this.size = 0;
        this.position = -1;
    }
    
    public void insert(T element) {
        Node<T> newNode = new Node(element, this.current, this.current.getNext());
        if (this.current != this.tail)
            this.current.getNext().setPrevious(newNode);
        this.current.setNext(newNode);
        if (this.current == this.tail) {
            this.tail = tail.getNext();
        }
        this.size++;
    }
    
    public void append(T element) {
        Node<T> newNode = new Node(element, this.tail, null);
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size++;
    }

    public void remove(){
        if ((this.head == this.current) && (this.head == this.tail)){
            System.out.println("Lista vacía, no se puede remover ningún elemento");
            return;
        }
        if (this.head == this.current){
            System.out.println("Nodo especial no se puede borrar");
            return;
        }
        
        if(this.current == this.tail){
            this.current.getPrevious().setNext(null);
            this.position--;
            this.tail = this.current.getPrevious();
        } else {
            this.current.getNext().setPrevious(this.current.getPrevious());
            this.current.getPrevious().setNext(this.current.getNext());

        }
        this.current = this.current.getPrevious();
        this.size--;
        
    }
    
    public void clear() {
        this.head = this.tail = this.current = new Node<>();
        this.position = -1;
        this.size = 0;
    }

    public T getElement(){
        return this.current.getElement();
    }

    public int getSize() {
        return this.size;
    }

    public boolean next() {
        if (this.current == this.tail) {
            System.out.println("Actualmente en último nodo, no se puede avanzar");
            return false;
        }
        this.current = this.current.getNext();
        this.position++;
        return true;
    }

    public boolean previous() {
        if (this.current == this.head) {
            System.out.println("Actualmente en primer nodo, no se puede retroceder");
            return false;
        }
        this.current = this.current.getPrevious();
        this.position--;
        return true;
    }

    public int getPosition() {
        return position;
    }
    
    public void goToStart(){
        this.current = this.head;
        this.position = -1;
    }
    
    public void goToEnd(){
        this.current = this.tail;
        this.position = this.size - 1;
    }
    
    public void goToPos(int pos) {
        if (pos < 0 || pos >= this.size) {
            System.out.println("Posición inválida");
            return;
        }
        if (pos > this.position) {
            while (pos > this.position) {
                this.next();
            }
        } else if (pos < this.position) {
            while (pos < this.position) {
                this.previous();
            }
        }
    }
    
    public int getPositionOfElement(T element) {
        Node tempNode = this.head;
        int position = -1;
        while (tempNode != null) {
            if (tempNode.getElement() != null && tempNode.getElement().equals(element)){
                return position;
            }
            tempNode = tempNode.getNext();
            position++;
        }
        return -1;
    }
    
    public String toString() {
        Node currentNode = this.head.getNext();

        StringBuffer result = new StringBuffer();

        for (int i = 0; currentNode != null; i++)
        {
            if (i > 0)
            {
                result.append(", ");
            }
            Object element = currentNode.getElement();

            if(currentNode != this.tail)
                result.append(element == null ? "" : element + "(P: " + currentNode.getPrevious().getElement() + ", N: " + currentNode.getNext().getElement() + ")");
            else
                result.append(element == null ? "" : element + "(P: " + currentNode.getPrevious().getElement() + ")");
            currentNode = currentNode.getNext();
        }
        result.append("\nHead: " + this.head.getElement() + ", Current: " + this.current.getElement() + ", Tail: " + this.tail.getElement() );
        return result.toString();
    }
    
}