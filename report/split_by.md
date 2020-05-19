# Split By

## Description
**The splitBy() function splits a string into an array based on the regex values that match up with the string.** This is a powerful function, and you can use it to split up strings, add values inside of each array element, then just use the joinBy() function to rejoin the string from the output array.

#### Input
``` json
{}
```
#### Output

``` json
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

```groovy
"192.88.99.0".split(/\./)
```

#### Java

```java
public boolean mediate(MessageContext mc) {

        String input = "192.88.99.0";
        return Stream.of(input.split("\\."))
                .map(JsonPrimitive::new)
                .collect(JsonHelper.toJsonPayloadAsArray(mc));
}
```
