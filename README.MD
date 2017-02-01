# NFC Extended Length Xposed Fix

Simple Xposed module to change the hardcoded value of 261 bytes which Android is saying is the upper limit of your NFC Controller to transmit although the NFC chip in your phone is maybe capable of more.

Its only useful if some apps check this value and throw an error. If your build-in NFC chip doesn't support this message length the module can't help you and apps trying to use this capacity may crash.

This module hooks two funktions:

- NativeNfcManager.getMaxTransceiveLength to return 2462 instead of the hardcoded 261 bytes.
- NativeNfcManager.getExtendedLengthApdusSupported to return TRUE instead of FALSE.

This could help to get apps and/or NFC Tags working who exceed the 261 bytes limits.

For german users: This module makes it possible to use the AusweisApp2 with eID Funktion of your ID Card (neuer Personalausweis (nPA)).
With an OnePlus One I was able to get till the PIN check before an unknown error occurred. Some others phone are maybe more compatible.

See this Android bug for more information.
https://code.google.com/p/android/issues/detail?id=76598
