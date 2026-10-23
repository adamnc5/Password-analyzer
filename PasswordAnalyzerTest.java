package passwordcheck;

/**
 * Simple dependency-free regression tests using plain Java.
 * Run this class and confirm every test passes before publishing changes.
 */
public class PasswordAnalyzerTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        expect(false, null, null, "null password rejected");
        expect(false, "", "", "empty password rejected");
        expect(false, "abc", "", "short password rejected");
        expect(false, "abcdefghijklmn", "", "14-character boundary rejected");
        expect(true, "abcdefghijklmnop", "", "16-character lowercase-only password accepted");
        expect(true, "abcdefghijklmno", "", "15-character boundary accepted");
        expect(true, "This is a longer passphrase with spaces", "", "passphrase accepted");
        expect(true, "abcdefghijklmnopqrst", "", "no uppercase, digits or symbols needed");
        expect(true, "a long sentence with !# in it", "", "punctuation accepted");
        expect(true, "🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉", "", "Unicode emoji code points counted properly");
        expect(true, "ééééééééééééééé", "", "Unicode accented letters accepted");
        expect(true, "x".repeat(64), "", "64-character password accepted");
        expect(true, "x".repeat(1024), "", "1024-character demo maximum accepted");
        expect(false, "x".repeat(1025), "", "over-demo-limit password rejected");
        expect(false, "PASSWORDPASSWORD", "", "case-insensitive blocklist");
        expect(false, "123456789012345", "", "blocklist catches long predictable sequence");
        expect(false, "This is bad\nokay", "", "control character rejected");
        expect(false, "correcthorsebatterystaple", "", "famous predictable example blocked");
        expect(true, "my unusual long password phrase", null, "null username supported");
        expect(true, "my unusual long password phrase", "", "empty username does not reject everything");
        expect(true, "my unusual long password phrase", "A", "one-letter username does not reject everything");
        expect(true, "a different 15 char phrase!", "Adam", "a password without username passes");
        expect(true, "a long sentence with password inside", "", "only whole-word-list entries blocked");

        PasswordAnalyzer.Result nameResult =
                PasswordAnalyzer.analyze("my name is adam and I remember this", "Adam");
        check(nameResult.isAccepted() && !nameResult.getAdvice().isEmpty(),
                "username inclusion generates advice without rejecting passphrase");

        PasswordAnalyzer.Result blocked = PasswordAnalyzer.analyze("PASSWORDPASSWORD", "");
        check(!blocked.getErrors().isEmpty(), "blocked password has a reason");
        check(!blocked.getErrors().toString().contains("PASSWORDPASSWORD"),
                "error messages do not echo the password");
        check(!PasswordAnalyzer.analyze("a".repeat(15), "").getErrors().isEmpty(),
                "long repeated-character password is blocked by the sample list");

        System.out.println("\nTests passed: " + passed + " / " + (passed + failed));
        if (failed > 0) {
            throw new AssertionError(failed + " test(s) failed");
        }
    }

    private static void expect(boolean accepted, String password, String username, String label) {
        boolean actual = PasswordAnalyzer.analyze(password, username).isAccepted();
        check(accepted == actual, label);
    }

    private static void check(boolean condition, String label) {
        if (condition) {
            passed++;
            System.out.println("PASS - " + label);
        } else {
            failed++;
            System.out.println("FAIL - " + label);
        }
    }
}
