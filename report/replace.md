# Replace

## Description

The replace() function is used to replace certain parts of the string with an updated value. The function is split into two parts, the text that the string will search for to match, and the matcher which includes the matching values that the text will replace.

#### Input
``` json
{}
```
#### Output

``` json
"CaliforniaZIP"
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
"California94117" replace "94117" with("ZIP")
```

#### Groovy Script

```groovy
"California94117".replace("94117","ZIP")
```