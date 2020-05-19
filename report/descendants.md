# Descendants Selector

## Description

The .. selector acts on arrays and objects.

This selector applies to the context using the form ..myKey, and it retrieves the values of all matching key-value pairs in the sub-tree under the selected context. Regardless of the hierarchical structure of the fields, the output is returned at the same level.

In this example, all of the fields that match the key name are placed in a list called names regardless of their cardinality in the tree of the input data.

#### Input
``` json
{
  "people": {
    "person": {
      "name": "Nial",
      "address": {
        "street": {
          "name": "Italia",
          "number": 2164
        },
        "area": {
          "zone": "San Isidro",
          "name": "Martinez"
        }
      }
    }
  }
}
```
#### Output

``` json
{
  "names": [
    "Nial",
    "Italia",
    "Martinez"
  ]
}
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
{ names: payload.people..name }
```

#### Java

```java
public boolean mediate(MessageContext mc) {
        try {
            JsonArray array = JsonHelper.getPayloadJsonElement(mc, "$..name").getAsJsonArray();
            JsonHelper.setJsonPayload(mc, array);
        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
}
```
