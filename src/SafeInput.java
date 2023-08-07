import java.util.Scanner;

public class SafeInput {
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }
    /**
     * Get an int value with no constraints
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - unconstrained int value
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get an unconstrained double value
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - an unconstrained double value
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get an int value within a specific numeric range
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low - low end of inclusive range
     * @param high - high end of inclusive range
     * @return - int value within the inclusive range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.print("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get a double value within an inclusive range
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low -Low end of inclusive range
     * @param high -High end of inclusive range
     * @return - double value within the inclusive range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.print("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get a [Y/N] confirmation from the user
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - true for yes and false for no
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retVal = true;
        String response = "";
        boolean gotAVal = false;
        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                gotAVal = true;
                retVal = false;
            } else {
                System.out.println("You must answer [Y/N]! " + response);
            }
        } while (!gotAVal);

        return retVal;
    }
    /**
     * Get a string that matches a RegEx pattern!
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regExPattern - java style RegEx pattern to constrain the input
     * @return - a string that matches the RegEx pattern supplied
     */
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern) {
        String response = "";
        boolean gotAVal = false;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regExPattern)) {
                gotAVal = true;
            } else {
                System.out.println("\n" + response + " must match the pattern " + regExPattern);
                System.out.println("Try again!");
            }
        } while (!gotAVal);

        return response;
    }
    /**
     * Get a string and produce a Pretty Header
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - a three line sting with a centered message
     */
    public static String prettyHeader(Scanner pipe, String prompt) {
        int blank;
        String msg = "";
        String retString = "";
        System.out.print("\n" + prompt + ": ");
        msg = pipe.nextLine();
        for (int row = 1; row <= 1; row ++) {
            for (int col = 1; col <= 60; col++) {
                System.out.printf("*", row * col);
            }
            System.out.println();
        }
        for (int row = 1; row <= 1; row ++) {
            for (int col = 1; col <= 3; col++) {
                System.out.printf("*", row * col);
            }
            blank = (54 - msg.length()) / 2;
            for (int col = 1; col <= blank; col++) {
                System.out.printf(" ", row * col);
            }
            System.out.printf(msg);
            if (msg.length() % 2 == 0) {
                for (int col = 1; col <= blank; col++) {
                    System.out.printf(" ", row * col);
                }
            }
            else {
                for (int col = 0; col <= blank; col++) {
                    System.out.printf(" ", row * col);
                }
            }
            for (int col = 58; col <= 60; col++) {
                System.out.printf("*", row * col);
            }
            System.out.println();
        }
        for (int row = 1; row <= 1; row ++) {
            for (int col = 1; col <= 60; col++) {
                System.out.printf("*", row * col);
            }
            System.out.println();
        }
        return retString;
    }
}