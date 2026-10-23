# Next phase: privacy-first browser interface

## The product idea

A responsive educational web page where visitors **try made-up sample passwords** and see clearly explained policy feedback (accepted/not accepted, code-point length, suggestions). No registration, no password-saving, no backend for submitted passwords.

## Safest Lovable specification (copy into Lovable later)

> Build a clean, responsive educational password-policy demo called "Password Analyzer".
> Analyze input entirely inside the visitor's browser using JavaScript; never send typed passwords to a server, API, database, analytics, logs, or AI model. No account system.
> Display "Do not enter a real password; use made-up examples" prominently above the input.
> Provide show/hide password toggle and accessible labels. Use a single-factor policy minimum of 15 Unicode code points, allow spaces and symbols and Unicode, and don't require specific character classes.
> Compare whole sample inputs, case-insensitively, against a short, clearly labeled *example* blocklist of common passwords. Explain this is not a real breach database or guarantee of strength.
> Show useful messages without displaying the password elsewhere or logging it. Default to no third-party analytics. Clear input on page close if possible; allow a Clear button.
> Provide sample fictional examples and an educational explanation of NIST guidance.
> Include client-side tests for 14 vs 15 code points, passphrases, Unicode emoji, case-insensitive blocklist, and empty input. Keep a minimal dependency footprint.
> Include a footnote linking the public GitHub Java reference implementation. Do not claim the website runs Java without a Java backend.

## Architecture choices

**Recommended:** Browser-only JavaScript validation using the same policy logic. No password crosses the browser/server boundary. Compare JS test results with Java regression examples to avoid rule drift.

**Alternative:** Java API backend, only if there is a compelling need. Passwords would cross a network boundary: this requires TLS, strict no-logging, hosting/incident-response/security controls, and a more complete threat model. It's unnecessary for the educational demo.

## Demo acceptance criteria

1. No network requests ever contain the input value (verify using browser DevTools Network tab).
2. No telemetry or storage ever captures it (verify localStorage and analytics).
3. Short inputs receive precise feedback, and Unicode lengths count code points.
4. No UI language suggests this can safely test a visitor's real passwords.
5. Accessibility: keyboard navigation, labels, clear errors, adequate contrast.
