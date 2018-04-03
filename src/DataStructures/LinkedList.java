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
public class LinkedList<T> {
    
    private class Node<T> {

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

    //atributos LinkedList
    private Node<T> head;
    private Node<T> current;
    private Node<T> tail;
    private int position;
    private int size;

    //constructores LinkedList
    
    public LinkedList() {
        this.head = new Node<>();
        this.current = this.head;
        this.tail = this.head;
        this.size = 0;
        this.position = -1;
    }
    
    public LinkedList (LinkedList<T> elemento) {
        this.head = elemento.head;
        this.current = elemento.head;
        this.tail = elemento.head;
        this.size = elemento.size;
        this.position = elemento.position;
    }
    
    public void insert(T element) {
        Node<T> newNode = new Node(element, this.current.getNext());
        this.current.setNext(newNode);
        if (this.current == this.tail) {
            this.tail = tail.getNext();
        }
        this.size++;

    }

    public void append(T element) {
        Node<T> newNode = new Node(element);
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size++;
    }

    public void remove() {
        
        if ((this.head == this.current) && (this.head == this.tail)){
            System.out.println("Lista vacía, no se puede remover ningún elemento");
            return;
        }
        
        Node<T> temp = head;
        while (temp.getNext() != this.current) {
            temp = temp.getNext();
        }
        
        temp.setNext(this.current.getNext());
        
        if (this.current == this.tail) {
            this.current = this.tail = temp;
            this.position--;
        }else {
            this.current = this.current.getNext();
        }
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
            return false;
        }
        this.current = this.current.getNext();
        this.position++;
        return true;
    }

    public boolean previous() {
        if (this.current == this.head) {
            return false;
        }
        Node temp = head;
        this.position = -1;
        while (temp.getNext() != this.current) {
            temp = temp.getNext();
            this.position++;
        }
        this.current = temp;
        return true;
    }

    public int getPosition() {
        return this.position;
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
    
    @Override
    public String toString() {
        Node currentNode = this.head.getNext();

        StringBuffer result = new StringBuffer();

        for (int i = 0; currentNode != null; i++)
        {
            if (i > 0)
            {
                result.append(",");
            }
            Object element = currentNode.getElement();

            result.append(element == null ? "" : element);
            currentNode = currentNode.getNext();
        }
        return result.toString();
    }

}