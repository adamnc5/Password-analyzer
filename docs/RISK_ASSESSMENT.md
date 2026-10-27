# Qualitative Risk Assessment — educational exercise

Likelihood/impact bands here are **illustrative**, not measured probabilities.

| Risk | Likelihood | Impact | Treatment |
|---|---|---|---|
| User pastes real password into a visible IDE terminal | Medium | High | Explicit warning; recommend fake examples only; Console masking when available |
| User assumes PASS means breach-free | High | Medium | Result and README explicitly disclaim this; sample-only blocklist |
| Empty username makes all passwords fail (original bug) | Medium | Low | Username optional; automated regression tests |
| Case variation bypasses common-password match | Medium | Medium | Case-insensitive full-match check and test |
| Regression introduces false ACCEPT/REJECT | Medium | Medium | Plain-Java tests and CI gate |
| Passwords leak from a future web interface | Unknown | High | No web backend yet; mandate client-only analysis in UI plan |

**Residual risk:** This is still only a small sample blocklist. A passing password may be highly guessable or compromised. Do not use this program for real account enrollment or authentication.

**Risk acceptance:** Because this is a clearly labeled local educational demo, full production-grade authentication safeguards are out of scope and MUST NOT be implied.
