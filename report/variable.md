# Using variables

## Description

Can use variables to store data in the script

#### Input
``` jso'
{
  "FirstName": "Max",
  "LastName": "Mule",
  "Company": "MuleSoft"
}
```
#### Output

``` json
"MuleSoft is awesome"
```

#### Dataweave Script

```
%dw 2.0
output application/json
var test = " is awesome"
---
payload.Company ++ test
```

#### Groovy Script

``` groovy
String test = " is awesome"
payload.Company + test
```

#### Java

```java
public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        String test = " is awesome";
        String companyName = jsonObject.get("Company").getAsString();

        String result = companyName + test;


        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                result, true, true);

        return true;
    }
```