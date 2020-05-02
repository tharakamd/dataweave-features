# Filter Object

## Description

The filterObject function works in a similar fashion to the filter function, however, instead of returning an array, it lists out the key-value pairs in an object and applies an expression that returns only the matching objects. If the expression provided has no matches, the output will return an empty object. Additionally, the expression must only return true or false. If true, it will return the object, and if false the object will be filtered out. To demonstrate this, look at the example below. Notice in the demonstration below, that all values greater than or equal to 2 will return true, and any value that is below 2 will return false. That is why the output does not include the first value that was included in the input.

#### Input
``` javascript
{
  "count1": 1,
  "count2": 2,
  "count3": 3,
  "count4": 4,
  "count5": 5
}
```
#### Output

``` javascript
{
  "count2": 2,
  "count3": 3,
  "count4": 4,
  "count5": 5
}
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload filterObject ((value, key, index) -> value >= 2)
```

#### Groovy Script
