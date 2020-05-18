# Functions

## Description

Can use functions in the script

#### Input
``` json
{
  "FirstName": "Max",
  "LastName": "Mule",
  "Company": "MuleSoft"
}
```
#### Output

``` json
"Match"
```

#### Dataweave Script

```
%dw 2.0
output application/json
fun function(arg) = if(arg == "MuleSoft") "Match" else "No Match"
---
function(payload.Company)
```

#### Groovy Script

``` groovy
def function(arg){
    if(arg == "MuleSoft"){
        return "Match"
    }else {
        return "No Match"
    }
}

function(payload.Company)
```
#### Java

```java
public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        String companyName = jsonObject.get("Company").getAsString();
        String result = function(companyName);

        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                result, true, true);

        return true;
    }

    private String function(String arg) {
        if(arg.equals("MuleSoft")){
            return "Match";
        }else {
            return "No Match";
        }
    }
```