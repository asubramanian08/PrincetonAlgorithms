package PriorityQueue.TeachingCode.BallCollision;

public class Particle {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final double mass; // mass
    private int count; // number of collisions

    // not done
    public Particle() {
        // done wrong to stop error
        radius = 1;
        mass = 1;
    }

    // not done
    public void move(double dt) {
    }

    // not done
    public void draw() {
    }

    // predict collision with particle or wall -> physics
    public double timeToHit(Particle that) {
        if (this == that)
            return Double.POSITIVE_INFINITY;
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0)
            return Double.POSITIVE_INFINITY;
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0)
            return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    // not done
    public double timeToHitVerticalWall() {
        double findVal = 0;
        return findVal;
    }

    // not done
    public double timeToHitHorizontalWall() {
        double findVal = 0;
        return findVal;
    }

    // resolve collision with particle or wall -> physics
    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
    }

    // not done
    public void bounceOffVerticalWall() {
    }

    // not done
    public void bounceOffHorizontalWall() {
    }
}