public class Planet {

    private final double g = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /* Calculate the distance between of planet p and this planet.
    @ param Planet p.
     */
    private double calcDistance(Planet p){
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double distance = Math.sqrt(dx * dx +dy * dy);
        return distance;
    }

    /* Takes in a planet, and returns a double describing the force
        exerted on this planet by the given planet.
    */
    private double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        double force = g * p.mass * this.mass / (r * r);
        return force;
    }

    /* Computes the x - components of the force exerted by Planet p.
     */
    private double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        double force = calcForceExertedBy(p);
        double r = calcDistance(p);
        double xForce = force * dx / r;
        return xForce;
    }

    /* Computes the y - components of the force exerted by Planet p.
     */
    private double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        double force = calcForceExertedBy(p);
        double r = calcDistance(p);
        double yForce = force * dy / r;
        return yForce;
    }

    /* Take in an array of Planets and calculate the net X  force
        exerted by all planets in that array upon the current Planet.
    */
    public double calcNetForceExertedByX(Planet[] planets){
        double xforce = 0;
        for (int i = 0; i < planets.length; i ++){
            if(planets[i].equals(this))
                continue;
            xforce += calcForceExertedByX(planets[i]);
        }
        return xforce;
    }

    /* Take in an array of Planets and calculate the net Y  force
    exerted by all planets in that array upon the current Planet.
    */
    public double calcNetForceExertedByY(Planet[] planets){
        double yforce = 0;
        for (int i = 0; i < planets.length; i ++){
            if(planets[i].equals(this))
                continue;
            yforce += calcForceExertedByY(planets[i]);
        }
        return yforce;
    }

    /* Determines how much the forces exerted on the planet will
       cause that planet to accelerate, and the resulting change in
       the planet’s velocity and position in a small period of time dt.
    */
    public void update(double dt, double xForce, double yForce){
        double ax = xForce / this.mass;
        double ay = yForce / this.mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    /* Draw the planet's picture at the planet's position.
     */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images//"+this.imgFileName);
    }
}
