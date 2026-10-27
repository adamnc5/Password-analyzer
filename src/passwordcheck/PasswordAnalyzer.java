package passwordcheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Checks a proposed password using a simple educational password policy.
 * Does not store passwords, authenticate anyone, or contact the internet.
 */
public class PasswordAnalyzer {

    // NIST SP 800-63B-4: 15 minimum for a single-factor password.
    public static final int MIN_LENGTH = 15;

    // Generous demo input cap to prevent accidental very large inputs.
    // This is NOT a requirement to stop at 64 characters.
    public static final int MAX_DEMO_LENGTH = 1024;

    // Educational SAMPLE blocklist. This is NOT a comprehensive breach database.
    // We can replace it with a larger maintained local dataset later.
    private static final String[] COMMON_PASSWORDS = {
        "password", "password1", "password123", "password123@",
        "123456", "12345678", "123456789", "1234567890",
        "123456789012345", "1234567890123456", "12345678901234567890",
        "admin", "admin123", "admin123@", "administrator",
        "qwerty", "qwerty123", "qwertyuiop", "qwertyuiop123@",
        "letmein", "welcome", "welcome123", "iloveyou", "iloveyou2@",
        "monkey", "dragon", "football", "sunshine", "princess",
        "aaaaaaaaaaaaaaa", "passwordpassword", "correcthorsebatterystaple",
        "thisismypassword", "supersecretpassword", "mysecretpassword"
    };

    /** Analyze a password without printing or saving it. */
    public static Result analyze(String password, String username) {
        List<String> errors = new ArrayList<>();
        List<String> advice = new ArrayList<>();

        if (password == null || password.isEmpty()) {
            errors.add("Enter a password to check.");
            return new Result(errors, advice);
        }

        // Count Unicode characters, not Java UTF-16 char units.
        int length = password.codePointCount(0, password.length());
        if (length < MIN_LENGTH) {
            errors.add("Use at least " + MIN_LENGTH + " characters (currently " + length + ").");
        }
        if (length > MAX_DEMO_LENGTH) {
            errors.add("This demo accepts at most " + MAX_DEMO_LENGTH + " characters per check.");
        }

        // Spaces and Unicode are welcome. Control characters are not useful here.
        for (int i = 0; i < password.length(); i++) {
            if (Character.isISOControl(password.charAt(i))) {
                errors.add("Remove non-printing control characters from the password.");
                break;
            }
        }

        // Whole-password, case-insensitive blocklist matching.
        // We do NOT reject a whole passphrase merely because it contains "password".
        for (int i = 0; i < COMMON_PASSWORDS.length; i++) {
            if (password.equalsIgnoreCase(COMMON_PASSWORDS[i])) {
                errors.add("This password matches an entry in the sample common-password blocklist.");
                break;
            }
        }

        // Personal information is a useful warning, not an extra composition rule.
        // Username is optional so people do not need to disclose personal details.
        if (username != null) {
            String cleanName = username.trim().toLowerCase(Locale.ROOT);
            if (cleanName.length() >= 3 &&
                    password.toLowerCase(Locale.ROOT).contains(cleanName)) {
                advice.add("Avoid including your name or username in a password.");
            }
        }

        if (length >= MIN_LENGTH && length < 20) {
            advice.add("A longer, unique passphrase may be easier to remember and harder to guess.");
        }

        return new Result(errors, advice);
    }

    /** Small result object to make a future user interface easier to build. */
    public static class Result {
        private final List<String> errors;
        private final List<String> advice;

        public Result(List<String> errors, List<String> advice) {
            this.errors = new ArrayList<>(errors);
            this.advice = new ArrayList<>(advice);
        }

        public boolean isAccepted() {
            return errors.isEmpty();
        }

        public List<String> getErrors() {
            return new ArrayList<>(errors);
        }

        public List<String> getAdvice() {
            return new ArrayList<>(advice);
        }
    }
}
