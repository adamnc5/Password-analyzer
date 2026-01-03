# Password-analyzer
Java project that helps users choose the right password with respect to rules that increase password complexity.
## Features used:
**Policy compliance**: Requires the user to use a password with a minimum 9 characters length.

**Complexity**: Requires the use of mixed characters(digits, special characters).

**Uniqueness**: Check if the password is not among the most used ones.

**Output masking**: Password is printed in a secure way to maintain confidentiality.

## How the code works:
Use of the while loop with boolean variables to make sure each rule is respected and if not requires the user to enter a new password that align with the security and policy rules.

Arrays were also used to compare the password entered by the user with the ones found in breach dictionaries.
