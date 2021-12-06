import api.GeoLocation;

public class Point_3D implements GeoLocation {

    private double x;
    private double y;
    private double z;

    public Point_3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point_3D(GeoLocation other) {
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();

    }

    @Override
    public double x() {
        // TODO Auto-generated method stub
        return this.x;
    }

    @Override
    public double y() {
        // TODO Auto-generated method stub
        return this.y;
    }

    @Override
    public double z() {
        // TODO Auto-generated method stub
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double distance(GeoLocation g) {
        // TODO Auto-generated method stub
        // (x2-x1)^2+(y2-y1)^2+(z2-z1)^2
        double ans = Math
                .sqrt((Math.pow(this.x - g.x(), 2)) + (Math.pow(this.y - g.y(), 2)) + (Math.pow(this.z - g.z(), 2)));
        return ans;
    }

}
