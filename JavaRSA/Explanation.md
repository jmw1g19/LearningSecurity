# JavaRSA
Version 1.0 completed on 23rd July 2020

## Explanation
This is a rather basic implementation of RSA in Java (hence the name, JavaRSA). We use the *BigInteger* class to overcome range limits with the primitive integer type (2^31 - 1 positive maximum), and then perform the maths as described [here](https://brilliant.org/wiki/rsa-encryption/) using operations such as *.modPow* and *.modInverse* -- for this first version, all that happens is a simple number is encrypted and decrypted.

Future versions will likely extend this implementation into a proper library and introduce support for strings.

## Version History
**Version 1.0** *23rd July 2020*
- Basic encryption and decryption functionality on another *BigInteger*.
- Equality test to ensure implementation works as expected.
- Improvements need to be made, including ensuring that phi is not a multiple of 65537, else GCD(phi, e) no longer equals 1.
