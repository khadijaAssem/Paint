package eg.edu.alexu.csd.oop.draw;

public class Operation {
    private final String operation;
    private final Shape oldShape;
    private final Shape newShape;
    private final int deletedIndex;

    public String getOperation() {
        return operation;
    }

    public Shape getOldShape() {
        return oldShape;
    }

    public Shape getNewShape() {
        return newShape;
    }

    public int getIndex() {
        return this.deletedIndex;
    }

    public Operation(String operation, Shape oldShape, Shape newShape, int deletedIndex) {
        this.operation = operation;
        this.oldShape = oldShape;
        this.newShape = newShape;
        this.deletedIndex = deletedIndex;
    }

}