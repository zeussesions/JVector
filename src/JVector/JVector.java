package JVector;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JVector {
    public double x;
    public double y;
    public double z;
    private boolean zAxis;

    public JVector(double x, double y) {
        this(x, y, 0);
        zAxis = false;
    }

    public JVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        zAxis = true;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public JVector duplicate() {
        JVector v = null;
        if (this.zAxis) {
            v = new JVector(this.x, this.y, this.z);
        }
        if (!this.zAxis) {
            v = new JVector(this.x, this.y);
        }
        return v;
    }

    public void add(double vx, double vy) {
        this.x += vx;
        this.y += vy;
    }

    public JVector add(double vx, double vy, double vz) {
        this.x += vx;
        this.y += vy;
        this.z += vz;
        return new JVector(this.x, this.y, this.z);
    }

    public void mod(JVector v1) {
        if (zAxis) {
            this.x %= v1.x;
            this.y %= v1.y;
            this.z %= v1.z;
        } else {
            this.x %= v1.x;
            this.y %= v1.y;
        }
    }

    public void sub(JVector v1) {
        if (zAxis) {
            this.x -= v1.x;
            this.y -= v1.y;
            this.z -= v1.z;
        } else {
            this.x -= v1.x;
            this.y -= v1.y;
        }
    }

    public void mult(JVector v1) {
        if (zAxis) {
            this.x *= v1.x;
            this.y *= v1.y;
            this.z *= v1.z;
        } else {
            this.x *= v1.x;
            this.y *= v1.y;
        }
    }

    public void div(JVector v1) {
        if (zAxis) {
            this.x /= v1.x;
            this.y /= v1.y;
            this.z /= v1.z;
        } else {
            this.x /= v1.x;
            this.y /= v1.y;
        }
    }

    public double mag() {
        if (zAxis) {
            double x = this.x;
            double y = this.y;
            return Math.sqrt((x * x) + (y * y));
        } else {
            return Math.sqrt((x * x) + (y * y) + (z * z));
        }
    }

    public double magSq() {
        if (zAxis) {
            double x = this.x;
            double y = this.y;
            return (x * x) + (y * y);
        } else {
            return (x * x) + (y * y) + (z * z);
        }
    }

    public double dot(JVector v1) {
        if (zAxis) {
            return this.x * v1.x + this.y * v1.y + this.z * v1.z;
        } else {
            return this.x * v1.x + this.y * v1.y;
        }
    }

    public JVector cross(JVector v) {
        double x = this.y * v.z - this.z * v.y;
        double y = this.z * v.x - this.x * v.z;
        double z = this.x * v.y - this.y * v.x;
        return new JVector(x, y, z);
    }


    public double dist(JVector v1) {
        if (zAxis) {
            return Math.sqrt(Math.pow((v1.x - this.x), 2) + Math.pow((v1.y - this.y), 2) + Math.pow((v1.z - this.z), 2));
        } else {
            return Math.sqrt(Math.pow((v1.x - this.x), 2) + Math.pow((v1.y - this.y), 2));
        }
    }

    public void normalize(double value) {
        if (zAxis) {
            this.x *= (value / mag());
            this.y *= (value / mag());
            this.z *= (value / mag());
        } else {
            this.x *= (value / mag());
            this.y *= (value / mag());
        }
    }

    public void limit(double limit) {
        if (this.mag() > limit) {
            this.normalize(limit);
        }
    }

    public void setMag(double mag) {
        this.normalize(mag);
    }

    public double heading() {
        double h = 0;
        if (!zAxis) {
            h = Math.toDegrees(Math.atan2(this.x, this.y));
        }
        return h;
    }

    public void setHeading(double angle) {
        double m = this.mag();
        this.x = m * Math.cos(angle);
        this.y = m * Math.sin(angle);
    }

    public void rotate(double angle) {
        double heading = this.heading() + angle;
        this.x = Math.cos(heading) * this.mag();
        this.y = Math.sin(heading) * this.mag();
    }

    public ArrayList<Double> array(JVector v) {
        ArrayList<Double> a = new ArrayList<>();
        if (zAxis) {
            a.add(v.x);
            a.add(v.y);
            a.add(v.z);
        } else {
            a.add(v.x);
            a.add(v.y);
        }
        return a;
    }

    public boolean equals(JVector v2) {
        boolean eq;
        if (zAxis) {
            double x1, x2, y1, y2, z1, z2;
            x1 = this.x;
            y1 = this.y;
            z1 = this.z;
            x2 = v2.x;
            y2 = v2.y;
            z2 = v2.z;
            eq = x1 == x2 && y1 == y2 && z1 == z2;
        } else {
            double x1, x2, y1, y2;
            x1 = this.x;
            y1 = this.y;
            x2 = v2.x;
            y2 = v2.y;
            eq = x1 == x2 && y1 == y2;
        }
        return eq;
    }

    public JVector random2D(int minX, int maxX, int minY, int maxY) {
        int randomX = ThreadLocalRandom.current().nextInt(minX, maxX + 1);
        int randomY = ThreadLocalRandom.current().nextInt(minY, maxY + 1);
        return new JVector(randomX, randomY);
    }

    public JVector random3D(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        int randomX = ThreadLocalRandom.current().nextInt(minX, maxX + 1);
        int randomY = ThreadLocalRandom.current().nextInt(minY, maxY + 1);
        int randomZ = ThreadLocalRandom.current().nextInt(minZ, maxZ + 1);
        return new JVector(randomX, randomY, randomZ);
    }

    public String toString() {
        String str;
        if (zAxis) {
            str = String.format(this.x + ", " + this.y + ", " + this.z);
        } else {
            str = String.format(this.x + ", " + this.y);
        }
        return str;
    }

    public JVector fromAngle(double angle, double length) {
        return new JVector(length * Math.cos(angle), length * Math.sin(angle));
    }

    public JVector fromAngle(double angle) {
        return new JVector(Math.cos(angle), Math.sin(angle));
    }

    public JVector fromAngles(double theta, double phi) {
        double length = 1;
        double cosPhi = Math.cos(phi);
        double sinPhi = Math.sin(phi);
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        return new JVector(
                length * sinTheta * sinPhi,
                -length * cosTheta,
                length * sinTheta * cosPhi
        );
    }

    public JVector fromAngles(double theta, double phi, double length) {
        double cosPhi = Math.cos(phi);
        double sinPhi = Math.sin(phi);
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        return new JVector(
                length * sinTheta * sinPhi,
                -length * cosTheta,
                length * sinTheta * cosPhi
        );
    }

    private int sign(double num) {
        return Integer.signum((int) num);
    }

    public double angleBetween(JVector v) {
        double dotmag = this.dot(v) / (this.mag() * v.mag());
        double angle;
        angle = Math.acos(Math.min(1, Math.max(-1, dotmag)));
        angle = angle * Math.signum(this.cross(v).z) < 1 ? 1 : -1;
        return angle;
    }

    public void lerp(JVector v, double amt) {
        this.x += (v.x - this.x) * amt;
        this.y += (v.y - this.y) * amt;
        this.z += (v.z - this.z) * amt;
    }
}