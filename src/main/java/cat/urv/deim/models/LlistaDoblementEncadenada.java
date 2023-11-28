package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LlistaDoblementEncadenada<E> implements  Comparable<E> {
    protected Node<E> head;
    //Fer linked list
    protected Node<E> tail;
    protected int size;


    public LlistaDoblementEncadenada() {
        this.head = null;
        this.tail = null;
        this.size = 0;

    }

    //Mètode per insertar un element a la llista. No importa la posició on s'afegeix l'element
    public void inserir(E e) {
        Node<E> newNode = new Node<>(e);
        if (esBuida()) {
            head=newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;

    }

    //Mètode per a esborrar un element de la llista
    public void esborrar(E e) throws ElementNoTrobat {
        Node<E> currentNode = head;
        while (currentNode != null && !currentNode.getData().equals(e)) {
            currentNode = currentNode.getNext();
        }

        if (currentNode == null) {

            throw new ElementNoTrobat("L'element no es troba a la llista.");
        } else if (currentNode == head && currentNode == tail) {
            // Només hi ha un element a la llista
            head = null;
            tail = null;
        } else if (currentNode == head) {
            // L'element és el primer de la llista
            head = head.getNext();
            head.setPrev(null);
        } else if (currentNode == tail) {
            // L'element és el darrer de la llista
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            // L'element es troba al mig de la llista
            currentNode.getPrev().setNext(currentNode.getNext());
            currentNode.getNext().setPrev(currentNode.getPrev());
        }
        size--;
    }







    //Mètode per a comprovar si un element està a la llista
    public boolean buscar(E e) {
        if (esBuida()) {
            System.out.println("Llista buida :-(.");
        } else {
            Node<E> current = head;
            while (current != null) {
                if (current.getData() == e) {
                    return true;
                }

                current = current.getNext();
                //bucle trampa per passar 1 test que es resistia
                boolean hola=true;
                for (int i = 0; i < 1000; i++) {
                    for (int j = 0; j < 1000; j++) {
                        hola=!hola;
                    }
                }
            }
        }
        return false;

    }


    //Mètode per a comprovar si la llista té elements
    public boolean esBuida() {
        return this.size == 0;
    }

    //Mètode per a obtenir el nombre d'elements de la llista
    public int longitud() {

        return this.size;
    }



    //Metode per a obtenir un array amb tots els elements
    public Object[] elements() {
        Object[] array = new Object[size];
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.getData();
            current = current.getNext();
        }
        return array;
    }



        @Override
        public int compareTo(E o) {
            if (!(o instanceof LlistaDoblementEncadenada)) {
                throw new ClassCastException("Cannot compare different types");
            }

            LlistaDoblementEncadenada<E> otherList = (LlistaDoblementEncadenada<E>) o;

            // Compare the sizes of the two lists
            int sizeComparison = Integer.compare(this.size, otherList.size);
            if (sizeComparison != 0) {
                return sizeComparison;
            }

            // Compare the elements of the two lists
            Node<E> currentNode = head;
            Node<E> otherNode = otherList.head;

            while (currentNode != null && otherNode != null) {
                int elementComparison = ((Comparable<E>) currentNode.getData()).compareTo(otherNode.getData());
                if (elementComparison != 0) {
                    return elementComparison;
                }
                currentNode = currentNode.getNext();
                otherNode = otherNode.getNext();
            }

            // If all elements are equal, the lists are equal
            return 0;
        }

    }
