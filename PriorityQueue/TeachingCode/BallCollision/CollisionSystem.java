package PriorityQueue.TeachingCode.BallCollision;

import PriorityQueue.TeachingCode.MinPQ;

public class CollisionSystem {
    // not done
    private class Event implements Comparable<Event> {
        private double time; // time of event
        private Particle a, b; // particles involved in event
        private int countA, countB; // collision counts for a and b

        // create event -> not done
        public Event(double t, Particle a, Particle b) {
        }

        // ordered by time
        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        // invalid if intervening collision -> not done
        public boolean isValid() {
            boolean findVal = true;
            return findVal;
        }
    }

    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particles

    // not done
    public CollisionSystem(Particle[] particles) {
    }

    private void predict(Particle a) {
        if (a == null)
            return;
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }
        pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

    // not done
    private void redraw() {
    }

    public void simulate() {
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++)
            predict(particles[i]);
        pq.insert(new Event(0, null, null));

        while (!pq.isEmpty()) {
            // get new event
            Event event = pq.delMin();
            if (!event.isValid())
                continue;
            Particle a = event.a;
            Particle b = event.b;

            // update positions and time
            for (int i = 0; i < particles.length; i++)
                particles[i].move(event.time - t);
            t = event.time;

            // process event
            if (a != null && b != null)
                a.bounceOff(b);
            else if (a != null && b == null)
                a.bounceOffVerticalWall();
            else if (a == null && b != null)

                b.bounceOffHorizontalWall();
            else if (a == null && b == null)
                redraw();

            // predict new events baced on change
            predict(a);
            predict(b);
        }
    }
}