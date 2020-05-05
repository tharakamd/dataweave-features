# Numbers

## Description

The Numbers library enables conversion of numbers into formats such as binary, hex or radix and can be imported into your DataWeave script by including the package in the header of the script. The toHex() function converts a number into hexadecimal, and the fromHex() function converts a hexadecimal number into a decimal number. The toBinary() function transforms a number into Binary, and the fromBinary() function transforms a number from Binary into a decimal number. The toRadixNumber() function transforms a specified radix into a decimal number, and the fromRadixNumber() function transforms a number into a radix. Check out the below demonstration to see these functions working in action.

#### Input
``` json
{}
```
#### Output

``` json
  "a": "75bcd15",
  "b": "111010110111100110100010101",
  "c": "10",
  "d": 123456789,
  "e": 123456789,
  "f": 10
}
```

#### Dataweave Script

```
%dw 2.0
import * from dw::core::Numbers
output application/json
---
{
   a: toHex(123456789),
   b: toBinary(123456789),
   c: toRadixNumber(2,2),
   d: fromHex("75bcd15"),
   e: fromBinary(111010110111100110100010101),
   f: fromRadixNumber("10",10)
}
```

### Groovy Script
``` groovy
[
    "a": Long.toHexString(123456789),
    "b": Long.toBinaryString(123456789),
    "c": Long.toBinaryString(2),
    "d": Long.parseLong("75bcd15", 16),
    "e": Long.parseLong("111010110111100110100010101", 2),
    "f": Long.parseLong("10",10)
]
```

