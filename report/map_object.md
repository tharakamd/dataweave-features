# Map Object

## Description

**The mapObject function processes both keys and values, and returns an object with the key-value pairs that result from processing the object through the function.** In the example below, the mapObject iterates through the accountType array and returns each key (which are users, and admins in this case). In the output, each value is nested under each key based on the structure in the input payload. Each value is sorted and displayed under the key that the data originally was found. The mapObject function requires the input payload to be an object. If you look at the mapObject output vs the map output, you will notice that mapObject returns an object while map returns an array.

#### Input
``` json
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

``` json
[
	{
		"index": "users",
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
		"index": "admins",
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
payload.accountType[0] mapObject (value, key) -> {
 index: key,
 accountInfo: value
}
```

#### Groovy Script

``` groovy
payload.accountType[0].collect{ item ->
    [
            "index": item.properties.key,
            "accountInfo": item.properties.value
    ]
}
```