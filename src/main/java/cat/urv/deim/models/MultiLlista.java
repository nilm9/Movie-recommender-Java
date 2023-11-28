package cat.urv.deim.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;

public class MultiLlista<A extends Comparable<A>, B extends Comparable<B>, C>  {
    private HashMapIndirecte<A, EdgeList<A, B, C>> conjuntA;
    private HashMapIndirecte<B, EdgeList<B, A, C>> conjuntB;

    public MultiLlista() {
        conjuntA = new HashMapIndirecte<>();
        conjuntB = new HashMapIndirecte<>();
    }


    public void inserir(A a, B b, C c) throws ElementRepetit, ElementNoTrobat {
        if (existeix(a, b)) {
            throw new ElementRepetit("La relació ja existeix.");
        }

        EdgeList<A, B, C> fila = conjuntA.element(a);
        if (fila == null) {
            fila = new EdgeList<>();
            conjuntA.inserir(a, fila);
        }

        Edge<A, B, C> shared = new Edge<>(a, b, c);

        fila.insertEdgeX(shared);

        EdgeList<B, A, C> columna = conjuntB.element(b);
        if (columna == null) {
            columna = new EdgeList<>();
            conjuntB.inserir(b, columna);
        }

        columna.insertEdgeY((Edge<B, A, C>) shared);
    }

    public void esborrar(A a, B b) throws ElementNoTrobat {
        if (!existeix(a, b)) {
            throw new ElementNoTrobat("La relació no existeix.");
        }

        EdgeList<A, B, C> fila = conjuntA.element(a);
        EdgeList<B, A, C> columna = conjuntB.element(b);

        fila.removeEdge(b);
        columna.removeEdge(a);

        if (fila.isEmpty()) {
            conjuntA.esborrar(a);
        }
        if (columna.isEmpty()) {
            conjuntB.esborrar(b);
        }
    }

    public List<B> fila(A a) throws ElementNoTrobat {
        if (!conjuntA.buscar(a)) {
            throw new ElementNoTrobat("Element " + a + " NoTrobat");
        }

        List<B> list = new ArrayList<>();
        EdgeList<A, B, C> valors = conjuntA.element(a);
        Edge<A, B, C> current = valors.getHeadX();
        while (current != null) {
            list.add(current.getB());
            current = current.getNextX();
        }
        return list;
    }

    public List<A> columna(B b) throws ElementNoTrobat {
        if (!conjuntB.buscar( b)) {
            throw new ElementNoTrobat("Element " + b + " NoTrobat");
        }

        List<A> list = new ArrayList<>();
        EdgeList<B, A, C> valors = conjuntB.element((B) b);
        Edge<B, A, C> current = valors.getHeadY();
        while (current != null) {
            list.add((A) current.getA());
            current = current.getNextY();
        }
        return list;
    }


    public boolean existeix(A a, B b) throws ElementNoTrobat {
        EdgeList<A, B, C> fila = conjuntA.element(a);
        EdgeList<B, A, C> columna = conjuntB.element(b);
        return fila != null && columna != null && fila.containsEdge(b);
    }

    public int numColumns() {
        return conjuntB.longitud();
    }

    public int numRows() {
        return conjuntA.longitud();
    }



    public C element(A a, B b) throws ElementNoTrobat {
        if (!existeix(a, b)) {
            throw new ElementNoTrobat("La relació no existeix.");
        }

        EdgeList<A, B, C> fila = conjuntA.element(a);
        Edge<A, B, C> current = fila.getHeadX();
        while (current != null) {
            if (current.getB().equals(b)) {
                return current.getValue(); // Retorna la relació si la troba.
            }
            current = current.getNextX();
        }

        throw new ElementNoTrobat("La relació no existeix.");
    }

    public Object[] getClausConjuntA() {
        return conjuntA.obtenirClaus();
    }

    public Object[] getClausConjuntB() {
        return conjuntB.obtenirClaus();
    }
    /**
    public List<A> getX(){
        LlistaDoblementEncadenada<A> elements = new LlistaDoblementEncadenada<>();
        for (A element: conjuntA.obtenirValors())

    } **/
    public  HashMapIndirecte getY(){
        return conjuntB;
    }


}
