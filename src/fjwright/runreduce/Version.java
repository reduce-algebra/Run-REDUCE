package fjwright.runreduce;

/**
 * Version numbering compatible with installers.
 * Normal release versions have have revision = 0, which is omitted.
 * Release date as month and year, and copyright string.
 */
public class Version {
    private final static int MAJOR = 2;
    private final static int MINOR = 7;
    private final static int REVISION = 1;
    private final static String MONTH = "January";
    private final static int YEAR = 2021;

    // Excess format arguments are ignored.
    static final String VERSION = String.format(REVISION == 0 ? "%d.%d" : "%d.%d.%d",
            MAJOR, MINOR, REVISION);
    static final String DATE = String.format("%s %d", MONTH, YEAR);
    static final String COPYRIGHT = String.format("© 2020‒%d, Francis Wright", YEAR);

    static final String JAVA = String.format(
            "Compiled using: Java 14.0.2; JavaFX 15.0.1.\n" +
            "Run using: Java %s; JavaFX %s.",
            System.getProperty("java.version"),
            System.getProperty("javafx.version"));

//    static {
//        System.err.println(System.getProperties());
//    }
}
