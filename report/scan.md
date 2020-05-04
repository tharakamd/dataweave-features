# Scan

## Description

**The scan() function returns an array with whatever matches are found in the given input string.** Each of the matches is returned in an array and the function uses regex to scan through the string for matches. In the example below, each section of the scan (defined by parathesis), is split up into its own value in the array which makes it easy to modify and then combine the array after the contents have been modified.

#### Input
``` javascript
"maxthemule@mulesoft.com"
```
#### Output

``` javascript
[
  [
    "maxthemule@mulesoft.com",
    "maxthemule",
    "mulesoft",
    "com"
  ]
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload scan(/([a-z]*)@([a-z]*).([a-z]*)/)
```

#### Groovy Script

```groovy
[(matcher = payload =~ /([a-z]*)@([a-z]*).([a-z]*)/)[0]]

```