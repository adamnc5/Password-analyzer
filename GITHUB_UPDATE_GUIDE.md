# Update your existing GitHub repo safely

Your original repository is https://github.com/adamnc5/Password-analyzer.

1. Open the repo and save a copy of the old `psswrdcheck.java`, or create a new branch named `ssdlc-upgrade` before changing anything.
2. Extract this zip, then copy the **files and folders** into the root of that repo. Preserve the relative paths, especially `src/passwordcheck/` and `.github/workflows/`.
3. The original root-level `psswrdcheck.java` is now *obsolete*. Don't keep two active copies of a class with the same name and package. Prefer **move** or remove the old root-level file after you have saved your history.
4. Update the repo description to: "Educational Java password policy analyzer with SSDLC threat modelling, tests, GitHub Actions and CodeQL scanning."
5. Commit with a descriptive message: `Refactor password validator with modern policy and SSDLC tests`.
6. On GitHub, open **Actions** and verify that **Java checks** passes. Review the CodeQL code scanning status; you may need to enable code scanning in repository security settings. The CodeQL workflow was prepared, but it has **not** been run on your GitHub account here.
7. If checks fail, fix the problem before merging into `main`.

**Note:** Copying these files does NOT automatically change your GitHub repository. You control when to push them.
