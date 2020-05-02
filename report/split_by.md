# Split By

## Description
The splitBy() function splits a string into an array based on the regex values that match up with the string. This is a powerful function, and you can use it to split up strings, add values inside of each array element, then just use the joinBy() function to rejoin the string from the output array.

#### Input
``` javascript
{}
```
#### Output

``` javascript
[
  "192",
  "88",
  "99",
  "0"
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
"192.88.99.0" splitBy(".")
```

#### Groovy Script

