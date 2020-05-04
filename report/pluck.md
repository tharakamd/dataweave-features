# Pluck

## Description

**The pluck function is an alternative to the mapObject function. Pluck is used to map objects and return the resulting payload into an array.** Pluck works by iterating over an object and returning an array of keys, values or indices from the object. In the demonstration below, we are formatting the output in the same format as the mapObject example. The main difference between Pluck and mapObject as demonstrated below is the output is an array instead of an object.

#### Input
``` javascript
{
  "accountType": [
    {
      "users": [
        {
          "Name": "Jordan",
          "Company": "MuleSoft"
        },
        {
          "Name": "Bob",
          "Company": "Salesforce"
        }
      ],
      "admins": [
        {
          "Name": "Max",
          "Company": "MuleSoft"
        }
      ]
    }
  ]
}
```
#### Output

``` javascript
[
  {
    "0": "users",
    "accountInfo": [
      {
        "Name": "Jordan",
        "Company": "MuleSoft"
      },
      {
        "Name": "Bob",
        "Company": "Salesforce"
      }
    ]
  },
  {
    "1": "admins",
    "accountInfo": [
      {
        "Name": "Max",
        "Company": "MuleSoft"
      }
    ]
  }
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload.accountType[0] pluck ((value, key, index) -> {
  (index): (key),
  "accountInfo" : (value)
})
```

#### Groovy Script
``` groovy
payload.accountType[0].collect().withIndex().collect{ element, index ->
   [
           (index): element.key,
           "accountInfo": element.value
   ]
}
```

