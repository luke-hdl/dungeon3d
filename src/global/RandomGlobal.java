package global;

import java.util.Random;

public class RandomGlobal {
    protected static Random random;

    public static double getRandom(){
        if ( random == null ) {
            random = new Random();
        }

        return random.nextDouble();
    }
}
