# Filter Object

## Description

**The filterObject function works in a similar fashion to the filter function, however, instead of returning an array, it lists out the key-value pairs in an object and applies an expression that returns only the matching objects.** If the expression provided has no matches, the output will return an empty object. Additionally, the expression must only return true or false. If true, it will return the object, and if false the object will be filtered out. To demonstrate this, look at the example below. Notice in the demonstration below, that all values greater than or equal to 2 will return true, and any value that is below 2 will return false. That is why the output does not include the first value that was included in the input.

#### Input
``` json
{
  "count1": 1,
  "count2": 2,
  "count3": 3,
  "count4": 4,
  "count5": 5
}
```
#### Output

``` json
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

``` groovy
payload.findAll{it.value >= 2}
```

#### Java

```java
public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        JsonObject result = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue().getAsInt() >= 2) {
                result.add(entry.getKey(), entry.getValue());
            }
        }

        String transformedJson = result.toString();
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                transformedJson, true, true);

        return true;
}
```