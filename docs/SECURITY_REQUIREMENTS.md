# Security Requirements (SSDLC: planning)

This is a **local educational demo**, not a production password-verification service.

| ID | Requirement | Verification |
|---|---|---|
| SR-01 | Minimum 15 Unicode code points for the chosen *single-factor* demonstration policy | Tests: 14 vs 15, emoji |
| SR-02 | No uppercase/number/symbol combination requirements; allow spaces and Unicode | Tests: lowercase-only, passphrase, accents |
| SR-03 | Permit at least 64 characters | Test: 64 characters |
| SR-04 | Reject whole-password matches to included sample blocklist without case sensitivity | Tests: common/uppercase matches |
| SR-05 | Avoid disclosing the proposed password in validation messages | Tests: messages do not echo test secret; manual review |
| SR-06 | Handle blank/null inputs and missing usernames without crashes or loops | Tests: null/empty inputs |
| SR-07 | Never store, log, hash or transmit actual passwords | Manual code review of console + validator |
| SR-08 | Show visible-input warning if Java console cannot mask input | Manual run from an IDE |
| SR-09 | Regression tests must pass before merging | GitHub Actions workflow |
| SR-10 | Document limits and do not claim production readiness | README and security docs review |

## Acceptance criteria

All included tests pass and no code path prints password characters or transmits them. Use only fictional demo values in testing. Tests provide confidence in described behaviors, not a security guarantee.

## Future production-scale requirements (out of scope)

A large maintained blocklist and true leak checks; purpose-built password hashing with unique salts; secure transport; authentication throttling and account lifecycle; security monitoring; accessibility and privacy reviews; deployment threat modelling.
