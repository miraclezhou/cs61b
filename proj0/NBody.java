public class NBody {
    /* Simulate a universe specified in one of the data files.
     */


    /* Given a file name, return a double corresponding to the radius of the universe in that file.
     */
    public static double readRadius(String file){
        In in = new In(file);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /* Given a file name, it should return an array of Planets
        corresponding to the planets in the file.
    */
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int numOfPlanets = in.readInt();
        Planet[] planets = new Planet[numOfPlanets];

        double radius = in.readDouble();

        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        String img;

        for(int i = 0; i < planets.length; i ++){
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return planets;
    }

    /* How to convert the Strings to doubles?
       @ source : https://www.javatpoint.com/java-string-to-double
     */
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        Planet[] planets = readPlanets(fileName);
        double radius = readRadius(fileName);

        /* Set the scale so that it matches the radius of the universe.  */
        StdDraw.setScale( - radius, radius);

        /* Clear the former window.  */
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();

        for(int t = 0; t < T; t += dt){
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];

            // Calculate net xForce and yForce of every planet.
            for(int i = 0; i < planets.length; i ++){
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // Update every planet.
            for(int i = 0; i < planets.length; i ++){
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            /* Draw the starfield and planets. */
            StdDraw.picture(0, 0, "images//starfield.jpg");

            for(Planet p : planets)
                p.draw();


            /* Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }

        /* Print out the final state of the universe. */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
