package passwordcheck;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Console version of the original password checker.
 * The password validation rules are now in PasswordAnalyzer.java.
 */
public class psswrdcheck {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Console console = System.console();

        System.out.println("=== Password Analyzer (Educational Demo) ===");
        System.out.println("Never enter a password you use for a real account.");
        System.out.println("The program does not save or transmit passwords.");
        System.out.println();

        System.out.print("Optional name/username for personalized advice (or Enter to skip): ");
        String username = input.nextLine();

        if (console == null) {
            System.out.println("NOTE: Your IDE does not provide a secure console.");
            System.out.println("Password input will be VISIBLE. Use made-up test passwords only.");
        }

        while (true) {
            System.out.println();
            System.out.println("Check a password (minimum 15 characters).");
            System.out.println("Tip: Phrases and spaces are allowed; symbols are optional.");

            char[] entered;
            if (console != null) {
                entered = console.readPassword("Proposed password (type q to quit): ");
                if (entered == null) {
                    break;
                }
            } else {
                System.out.print("Made-up password (type q to quit): ");
                if (!input.hasNextLine()) {
                    break;
                }
                entered = input.nextLine().toCharArray();
            }

            // Console.readPassword() offers masked input on a real terminal.
            // Java Strings cannot be reliably erased from memory, so this is
            // intentionally a local learning tool, NOT a production verifier.
            String password = new String(entered);
            Arrays.fill(entered, '\0');

            if (password.equalsIgnoreCase("q")) {
                break;
            }

            PasswordAnalyzer.Result result = PasswordAnalyzer.analyze(password, username);
            if (result.isAccepted()) {
                System.out.println("PASS: This password passes the demo's basic checks.");
            } else {
                System.out.println("NOT ACCEPTED. Here's why:");
                for (String error : result.getErrors()) {
                    System.out.println("- " + error);
                }
            }

            for (String tip : result.getAdvice()) {
                System.out.println("Tip: " + tip);
            }
            System.out.println("Passing is NOT proof of password strength or breach safety.");
        }

        System.out.println("Goodbye! No passwords were stored.");
        // We don't close input here because doing so closes System.in.
    }
}
