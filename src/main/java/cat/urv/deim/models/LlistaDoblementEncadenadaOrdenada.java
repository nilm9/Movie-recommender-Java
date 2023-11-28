package cat.urv.deim.models;


import cat.urv.deim.exceptions.ElementNoTrobat;

public class LlistaDoblementEncadenadaOrdenada<E extends Comparable<E>> extends LlistaDoblementEncadenada<E> {

    protected Node<E> primer; //punter al primer node
    protected Node<E> ultim; //punter a l'últim node
    protected int longitud; //nombre d'elements de la llista

    //Constructor
    public LlistaDoblementEncadenadaOrdenada() {
        primer = null;
        ultim = null;
        longitud = 0;
    }

    //Mètode per insertar un element a la llista. La inserció és ordenada
    public void inserir(E e) {
        Node<E> nouNode = new Node<E>(e);

        if(primer == null) { //la llista és buida
            primer = nouNode;
            ultim = nouNode;
        }
        else if(e.compareTo(primer.getData()) < 0) { //el nou element va abans del primer element actual
            nouNode.setNext(primer);
            primer.setPrev(nouNode);
            primer = nouNode;
        }
        else if(e.compareTo(ultim.getData()) > 0) { //el nou element va després de l'últim element actual
            nouNode.setPrev(ultim);
            ultim.setNext(nouNode);
            ultim = nouNode;
        }
        else { //el nou element va enmig de dos elements
            Node<E> nodeActual = primer;

            while(e.compareTo(nodeActual.getData()) > 0) { //busquem el punt d'inserció
                nodeActual = nodeActual.getNext();
            }

            nouNode.setPrev(nodeActual.getPrev());
            nouNode.setNext(nodeActual);
            nodeActual.getPrev().setNext(nouNode);
            nodeActual.setPrev(nouNode);
        }

        longitud++; //incrementem la longitud de la llista
    }



    @Override
    public void esborrar(E e) throws ElementNoTrobat {
        Node<E> nodeActual = primer;

        while(nodeActual != null && e.compareTo(nodeActual.getData()) > 0) { //busquem l'element a esborrar
            nodeActual = nodeActual.getNext();
        }

        if(nodeActual == null || e.compareTo(nodeActual.getData()) != 0) { //l'element no existeix a la llista
            throw new ElementNoTrobat("L'element a esborrar no es troba a la llista.");
        }

        if(nodeActual == primer) { //l'element a esborrar és el primer de la llista
            primer = nodeActual.getNext();
            if(primer != null) {
                primer.setPrev(null);
            }
        }
        else if(nodeActual == ultim) { //l'element a esborrar és l'últim de la llista
            ultim = nodeActual.getPrev();
            if(ultim != null) {
                ultim.setNext(null);
            }
        }
        else { //l'element a esborrar és enmig de la llista
            nodeActual.getPrev().setNext(nodeActual.getNext());
            nodeActual.getNext().setPrev(nodeActual.getPrev());
        }

        longitud--; //decrementem la longitud de la llista
    }


    //Mètode per a comprovar si un element està a la llista
    public boolean buscar(E e) {
        int esquerra = 0;
        int dreta = longitud - 1;
        while (esquerra <= dreta) {
            int mig = esquerra + (dreta - esquerra) / 2;
            Node<E> nodeActual = getNode(mig);
            int comparacio = e.compareTo(nodeActual.getData());
            if (comparacio == 0) {
                return true;
            } else if (comparacio < 0) {
                dreta = mig - 1;
            } else {
                esquerra = mig + 1;
            }
        }
        return false;
    }

    private Node<E> getNode(int index) {
        Node<E> nodeActual = primer;
        for (int i = 0; i < index; i++) {
            nodeActual = nodeActual.getNext();
        }
        return nodeActual;
    }


    //Mètode per a comprovar si la llista té elements
    public boolean esBuida() {
        return longitud==0;
    }

    //Mètode per a obtenir el nombre d'elements de la llista
    public int longitud() {
        return longitud;
    }


    //Metode per a obtenir un array amb tots els elements
    public Object[] elements() {
        Object[] elements = new Object[longitud];
        Node<E> nodeActual = primer;
        int i = 0;

        while(nodeActual != null) {
            elements[i++] = nodeActual.getData();
            nodeActual = nodeActual.getNext();
        }

        return elements;    }



}