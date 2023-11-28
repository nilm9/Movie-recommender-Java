package cat.urv.deim.models;

import java.util.*;

public class Element<T extends Comparable<T>> implements Comparable<Element<T>> {
        protected T valor;
        protected List<T> relacions;

        public Element(T valor) {
            this.valor = valor;
            this.relacions = new LinkedList<>();
        }

        @Override
        public int compareTo(Element<T> o) {
            return valor.compareTo(o.valor);
        }


}
