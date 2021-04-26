package PriorityQueue.TeachingCode;

//demonstrating immutable (final) stuff
public final class Vector {
    private final int N;
    private final double[] data;

    public Vector(double[] data) {
        this.N = data.length;
        this.data = new double[N];
        for (int i = 0; i < N; i++)
            this.data[i] = data[i];
    }

    // instance methods don't change instance variables
}