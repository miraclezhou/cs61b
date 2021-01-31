import synthesizer.GuitarString;

public class GuitarHero {

    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static GuitarString[] keyStrings = new GuitarString[37];


    public static void main(String[] args) {

        /** create all keys' guitar strings. */
        for (int i = 0; i < 37; i += 1) {
            double freq = freqOfKey(i);
            keyStrings[i] = new synthesizer.GuitarString(freq);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                keyStrings[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString gs : keyStrings) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString gs : keyStrings) {
                gs.tic();
            }
        }
    }

    /** Compute the freqency of given index. */
    private static double freqOfKey(int index) {
        return 440 * Math.pow(2, (double) (index - 12) / 24);
    }
}
