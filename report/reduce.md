# Reduce

## Description

**The reduce function operates on each value of an array and returns a single value as the output.** Reduce takes two arguments, the first represents the current value of the iteration, and second is the accumulator which stores the most recent result after each iteration. The accumulator can be viewed as the output variable since it contains each output as the iteration is run through the array. In the demonstration below, each item and accumulator are added together in order to get the final output of 20. The item is referenced as a $ and the accumulator as a $$. You can think of this as ( 5 + 5 + 5 + 5 ) = 20

#### Input
``` json
[
  5,
  5,
  5,
  5
]
```
#### Output

``` json
20
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload reduce ($ + $$)
```

#### Groovy Script
``` groovy
payload.inject{total, element -> total + element}
```

#### Java

```java
public boolean mediate(MessageContext mc) {

        int sum = JsonHelper.getJsonArrayStream(mc)
                .map(JsonElement::getAsInt)
                .reduce(0, Integer::sum);

        JsonHelper.setJsonPayload(mc, String.valueOf(sum));
        return true;
}
```

## Example 2

#### Input
``` javascript
[
  2,
  2,
  2,
  2
]
```
#### Output

``` javascript
32
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload reduce ((item, acc = 2) -> acc * item)
```

#### Groovy Script

```groovy
payload.inject(2){total, element -> total * element}
```