# Selecting attributes from XML payload

## Description

Extract a value from a XML payload

#### Input
``` xml
<product id="1" type="electronic">
  <brand>SomeBrand</brand>
</product>
```
#### Output

``` json
{
  "item": [
    {
      "type": "electronic",
      "name": "SomeBrand",
      "attributes": {
        "id": "1",
        "type": "electronic"
      }
    }
  ]
}
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
{
  item: [
  	{
      "type" : payload.product.@."type",
      "name" : payload.product.brand,
      "attributes": payload.product.@
    }
  ]
}
```

#### Groovy Script

``` groovy
[
    item: [
            [
                "type" : payload.@type.toString(),
                "name" : payload.brand.toString(),
                "attributes": payload.attributes()
            ]
    ]
]
```