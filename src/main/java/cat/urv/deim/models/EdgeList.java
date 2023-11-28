package cat.urv.deim.models;

public class EdgeList<V, E, C> {
    private Edge<V, E, C> headX;
    private Edge<V, E, C> headY;
    private Edge<V, E, C> tailX;
    private Edge<V, E, C> tailY;
    private int numEdges;

    public EdgeList() {
        numEdges = 0;
        headX = null;
        headY = null;
        tailX = null;
        tailY = null;
    }

    public void insertEdgeX(Edge<V, E, C> shared) {
        if (headX == null) {
            headX = tailX = shared;
            headX.setPrevX(null);
            tailX.setNextX(null);
            numEdges++;
        } else {
            tailX.setNextX(shared);
            shared.setPrevX(tailX);
            tailX = shared;
            tailX.setNextX(null);
            numEdges++;
        }
    }

    public void insertEdgeY(Edge<V, E, C> shared) {
        if (headY == null) {
            headY = tailY = shared;
            headY.setPrevY(null);
            tailY.setNextY(null);
            numEdges++;
        } else {
            tailY.setNextY((Edge<V, E, C>) shared);
            shared.setPrevY((Edge<V, E, C>) tailY);
            tailY = shared;
            tailY.setNextY(null);
            numEdges++;
        }
    }

    public Edge<V, E, C> getHeadX() {
        return headX;
    }

    public void setHeadX(Edge<V, E, C> headX) {
        this.headX = headX;
    }

    public Edge<V, E, C> getHeadY() {
        return headY;
    }

    public void setHeadY(Edge<V, E, C> headY) {
        this.headY = headY;
    }

    public Edge<V, E, C> getTailX() {
        return tailX;
    }

    public void setTailX(Edge<V, E, C> tailX) {
        this.tailX = tailX;
    }

    public Edge<V, E, C> getTailY() {
        return tailY;
    }

    public void setTailY(Edge<V, E, C> tailY) {
        this.tailY = tailY;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public boolean containsEdge(E e) {
        Edge<V, E, C> current = headX;
        while (current != null) {
            if (current.getB().equals(e)) {
                return true;
            }
            current = current.getNextX();
        }
        return false;
    }

    public boolean containsEdgeValue(String e) {
        Edge<V, E, C> current = headX;
        while (current != null) {
            if (current.getValue().equals(e)) {
                return true;
            }
            current = current.getNextX();
        }
        return false;
    }

    public void removeEdge(E b) {
        Edge<V, E, C> current = headX;
        while (current != null) {
            if (current.getB().equals(b)) {
                Edge<V, E, C> prevX = current.getPrevX();
                Edge<V, E, C> nextX = current.getNextX();
                if (prevX != null) {
                    prevX.setNextX(nextX);
                } else {
                    headX = nextX;
                }
                if (nextX != null) {
                    nextX.setPrevX(prevX);
                } else {
                    tailX = prevX;
                }
                numEdges--;
                break;
            }
            current = current.getNextX();
        }
    }

    public boolean isEmpty() {
        return numEdges == 0;
    }
}
