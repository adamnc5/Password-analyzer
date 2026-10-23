# Threat Model — beginner STRIDE exercise

## Assets and trust boundary

Assets: demo user input (potentially sensitive) and the integrity of feedback messages.

The local boundary is: **Person -> local Java console -> PasswordAnalyzer -> local console messages**.
There is NO server, database, remote request or account system.

## Simple STRIDE table

| STRIDE | Threat scenario | Current control / limitation |
|---|---|---|
| Spoofing | Someone pretends the demo is a real login form | Banner says demo, no authentication is provided |
| Tampering | Attacker edits local code to always return PASS | Version control + visible tests; cannot defend against fully compromised local machine |
| Repudiation | Local user disputes a result | Tests define expected behavior; no tracking users or secrets |
| Information Disclosure | Password shown on-screen or printed in messages | `Console.readPassword()` when possible; visible-input warning otherwise; no password echo |
| Denial of Service | Huge inputs consume resources | Generous 1024-code-point demo cap; CLI intended for local educational use |
| Elevation of Privilege | Demo incorrectly treated as a secure account system | No privileged operations; explicit non-production documentation |

## Risk priority

Highest educational concerns: false sense of security and accidental entry of real passwords. The project makes **no guarantee** a password is safe or not breached.

## When adding a website

**New trust boundary:** browser -> (optional) server/API. Sending secret input across that boundary dramatically changes the privacy and threat model. Preferred educational web UI is client-side only, no backend, no analytics capturing input, no logs, and no network calls containing passwords. Analyze third-party scripts, browser extensions, analytics, and hosting practices. Never advertise a demo as a safe place to test a *real account password*.
