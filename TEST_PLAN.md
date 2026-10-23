# Test Plan — SSDLC verification and regression

## Automated tests

Run `run-tests.bat` on Windows or `sh run-tests.sh` on macOS/Linux.

Cases include:
- Null and empty password rejection; short strings and 14/15 character boundary
- Unicode code-point behavior including emoji and accented characters
- Acceptance of long passphrases, spaces, punctuation, lowercase-only strings
- Acceptance of lengths 64 and 1024; rejection past the 1024 demo cap
- Sample whole-password blocklist and capitalization bypass prevention
- Rejection of nonprinting control characters
- Optional username, including empty and one-character values
- Optional advice without unnecessary rejection
- Generic error messages that don't echo password input

## Manual security review

- [ ] Run in a terminal and verify the prompt hides characters where `System.console()` works
- [ ] Run in Eclipse and verify it warns that input is visible
- [ ] Search source for println/log statements containing `password` (none should echo the value)
- [ ] Test with invented passwords only
- [ ] Confirm project contains no analytics, remote API, or storage layer
- [ ] Verify release notes do not call the toy list a breach database

## CI release gate

A pull request or push triggers `javac` compilation and the dependency-free test runner in GitHub Actions. Passing CI demonstrates these checks only, not absence of all vulnerabilities.

## Tool selection rationale

SAST can analyze Java code without running it. A CodeQL workflow is configured in `.github/workflows/codeql.yml`, but it must run on GitHub before any scan results can be claimed. DAST cannot scan a command-line program as though it were a web endpoint. SCA has little to report here because no external libraries are used. Use the right tool for each lifecycle phase rather than claiming all tools were run.
