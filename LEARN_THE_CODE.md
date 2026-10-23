# Learn the upgrade (no advanced Java needed)

Start with **`src/passwordcheck/PasswordAnalyzer.java`** and understand each piece before pushing to GitHub.

## What stays familiar from your original program?

- `if` checks now validate minimum length, blocked passwords, and control characters.
- `for` loops go through the small `COMMON_PASSWORDS` array and each character in a password.
- `while` in `psswrdcheck.java` lets you check multiple sample passwords.
- `break` stops a loop early, just like in your original code.
- Strings, booleans, and `System.out.println` are still used.

## Only three new ideas

1. **A reusable method**: `analyze(password, username)` separates the rules from user input. The eventual interface can ask the method for results without re-writing all the rules.
2. **An ArrayList of messages**: You can show multiple problems at once, instead of exiting after the first problem. `List<String>` is simply a list of sentences.
3. **A result object**: `Result` groups acceptance, errors, and advice in one place; `result.isAccepted()` is a boolean test.

## Why the 15-character minimum?

This is a simple **single-factor** educational policy inspired by the final NIST SP 800-63B-4. It no longer forces uppercase, digits, or punctuation. It supports passphrases and long secrets. For real production verifiers, many more controls are required. We do not claim full NIST compliance.

## Why codePointCount?

In Java, `password.length()` counts UTF-16 code units. Some symbols (for example emoji) take two code units. `password.codePointCount(0, password.length())` is closer to the actual Unicode character count, so 15 emoji count as 15 rather than 30.

## Why isn't this a login system?

The app doesn't save accounts, authenticate users, or verify existing passwords. Its only job is to help explain a *demonstration* password policy. Implementing password authentication responsibly is a different, much bigger project.

## SSDLC mapping

| Phase | What we implemented |
|---|---|
| Requirements | `docs/SECURITY_REQUIREMENTS.md` |
| Design & threat modelling | `docs/THREAT_MODEL.md`, `docs/RISK_ASSESSMENT.md` |
| Coding | Small separated classes, input checks, no network requests |
| Verification | `PasswordAnalyzerTest` and GitHub Actions compile/tests |
| SAST | CodeQL scan workflow configured; it runs on GitHub after you push (not tested locally) |
| Maintenance | CI rechecks every future change; update blocklist/policy with documented tests |

## Questions you should be able to answer in an interview

1. Why is checking an empty username important?
2. What is the difference between a small illustrative blocklist and a real breach corpus?
3. Why doesn't a strong password require a symbol under current NIST guidance?
4. Why does masking typed input matter, and why is that still not the same as secure memory?
5. Why does a test suite make SSDLC security work repeatable?
6. Why should the future web interface avoid sending passwords to a server?
7. Why can't DAST test a CLI application as though it is a deployed website?
