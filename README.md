# Password Analyzer — Educational Secure SDLC Java Project

A beginner-friendly **Java password-policy demonstration**, updated using Secure Software Development Life Cycle (SSDLC) practices. The program analyzes *made-up example passwords* and explains which demo rules pass or fail.

> **Privacy:** Never enter a password used by a real account. This is a local educational tool, **not** a password manager, authentication service, strength guarantee, comprehensive breached-password checker, or a claim of NIST certification.

## What it checks

- Minimum of **15 Unicode code points** for the selected single-factor demonstration policy.
- Allows passphrases, spaces, Unicode, and long passwords. Uppercase, symbols, and numbers are **not required**.
- Rejects exact matches against a **small illustrative** common-password blocklist (case-insensitive), as well as control characters and blank inputs.
- Provides optional advice when a supplied name appears in a password; the name itself is optional.
- Supports up to 1,024 code points for this demo.
- Uses masked input when a real Java console is available; warns if your IDE can't hide typing.
- Does not intentionally save or send entered passwords.

Passing these checks **does not prove** a password is strong, unique, or uncompromised.

## Project layout

```text
src/passwordcheck/
  PasswordAnalyzer.java       # Reusable password rules
  psswrdcheck.java            # Console program
test/passwordcheck/
  PasswordAnalyzerTest.java   # 27 dependency-free regression checks
docs/
  SECURITY_REQUIREMENTS.md
  THREAT_MODEL.md
  RISK_ASSESSMENT.md
  TEST_PLAN.md
.github/workflows/
  ci.yml                      # Compile and test on pushes and pull requests
  codeql.yml                  # CodeQL Java security analysis
```

## Requirements and how to run

Install **JDK 21** (or newer). Then, in the repository root:

**Windows:** run `run-tests.bat` to test, or `run.bat` for the console app.

**macOS/Linux:** run `sh run-tests.sh` to test, or `sh run.sh` for the console app.

Or run manually:

```sh
mkdir -p out
javac -encoding UTF-8 -d out src/passwordcheck/*.java test/passwordcheck/*.java
java -cp out passwordcheck.PasswordAnalyzerTest
java -cp out passwordcheck.psswrdcheck
```

The tests check null/empty inputs, 14/15-character boundaries, passphrases, Unicode, case-insensitive blocklist matching, username handling, and non-echoing error messages.

## SSDLC and CI/CD

The `docs/` folder maps security requirements, a beginner STRIDE threat model, a qualitative risk assessment, and a test plan to this project.

GitHub Actions' **Java checks** workflow compiles the project and runs the regression tests on pushes and pull requests. A separate **CodeQL** workflow performs static analysis when supported by the repository's GitHub settings. Check the **Actions** tab for actual run results; merely having a workflow file is not proof that a scan passed.

## Future web interface

A proposed educational web UI should validate *fictional examples* entirely in the visitor's browser, without transmitting entered values. The Java program is a reference implementation; a browser-only UI would need equivalent JavaScript rules and tests.

## Limitations

This is **not production authentication**. It does not check a real breach database, enforce account policies, hash account credentials, or establish a secure login. The sample list is intentionally small. Review the security docs before extending it.
