# Extracting value from JSON

## Description

Extract a value from a JSON object

#### Input
``` json
{
"message": "Hello World!"
}
```
#### Output

``` json
"Hello World!"
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload.message
```

#### Groovy Script

``` groovy
payload.message
```

#### Java 
```java
 public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        String message = jsonObject.get("message").getAsString();

        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                message, true, true);

        return true;
    }
```