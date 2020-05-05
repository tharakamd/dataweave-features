# Filter

## Description

**The filter function iterates over an array and applies an expression that returns matching values of that expression.** The expression must return either true or false, and if the expression provided has no matches, the output will return an empty array. In the demonstration below, notice how the output only returns items that are greater than or equal to 2, and the items that return false are omitted from the output. Keep in mind that the filter function will only work on array inputs. If you want to filter objects checkout the filterObject function next.

#### Input
``` json
[
  1,
  2,
  3,
  4,
  5
]
```
#### Output

``` json
[
  2,
  3,
  4,
  5
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload filter ((item, index) -> (item >= 2))
```

#### Groovy Script

``` groovy
payload.findAll{it >= 2}
```

