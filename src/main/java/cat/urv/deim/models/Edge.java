package cat.urv.deim.models;

public class Edge<A, B, C> {
    private A a;
    private B b;
    private Edge<A, B, C> prevX;
    private Edge<A, B, C> nextX;
    private Edge<A, B, C> prevY;
    private Edge<A, B, C> nextY;
    private C value;

    public Edge(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.value = c;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public Edge<A, B, C> getPrevX() {
        return prevX;
    }

    public void setPrevX(Edge<A, B, C> prevX) {
        this.prevX = prevX;
    }

    public Edge<A, B, C> getNextX() {
        return nextX;
    }

    public void setNextX(Edge<A, B, C> nextX) {
        this.nextX = nextX;
    }

    public Edge<A, B, C> getPrevY() {
        return prevY;
    }

    public void setPrevY(Edge<A, B, C> prevY) {
        this.prevY = prevY;
    }

    public Edge<A, B, C> getNextY() {
        return nextY;
    }

    public void setNextY(Edge<A, B, C> nextY) {
        this.nextY = nextY;
    }

    public C getValue() {
        return value;
    }

    public void setValue(C value) {
        this.value = value;
    }
}
