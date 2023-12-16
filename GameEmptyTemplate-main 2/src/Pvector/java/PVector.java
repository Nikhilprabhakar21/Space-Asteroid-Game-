package Pvector.java;

public class PVector {
    public float x;
    public float y;

    public PVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(PVector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void subtract(PVector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mult(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public float mag() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        float m = mag();
        if (m != 0) {
            this.x /= m;
            this.y /= m;
        }
    }

    // Static method for addition of two vectors
    public static PVector add(PVector v1, PVector v2) {
        return new PVector(v1.x + v2.x, v1.y + v2.y);
    }

    // Static method for subtraction of two vectors
    public static PVector subtract(PVector v1, PVector v2) {
        return new PVector(v1.x - v2.x, v1.y - v2.y);
    }

    // Static method for scaling a vector
    public static PVector mult(PVector v, float scalar) {
        return new PVector(v.x * scalar, v.y * scalar);
    }
}
