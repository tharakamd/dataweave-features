# Map

## Description

Map is one of the most used functions in DataWeave and helps integration developers perform complex transformations that would have been previously a challenge. The map function in DataWeave is used when we want to modify elements in an array and perform a series of tasks on each entry. Map essentially transforms one payload into another format which is desired by the target endpoint. One important concept to understand is that the map function isn’t actually modifying the array, it’s creating a new array from the specifications that you define in the map function. Nothing in DataWeave in mutable, which essentially means that once a specific element is created, it can’t be changed again.

Let’s take a look at how the map function works on the most basic level by checking out the example below. In order for the map command to work, we must be calling the map function on a data type structured as an array. In the below example, the input is formatted in JSON and the data is wrapped in an array. When the map command is run on the input, we specify that we want a Full Name field instead of a separate FirstName and LastName field. In order to achieve this transformation, we mapped the payload by specifying the desired output format in our DataWeave script.

#### Input
``` javascript
[
  {
    "FirstName": "Max",
    "LastName": "Mule",
    "Company": "MuleSoft"
  },
  {
    "FirstName": "Astro",
    "LastName": "Nomical",
    "Company": "Salesforce"
  }
]
```
#### Output

``` javascript
[
  {
    "index": 0,
    "Full Name": "Max Mule",
    "Company": "MuleSoft"
  },
  {
    "index": 1,
    "Full Name": "Astro Nomical",
    "Company": "Salesforce"
  }
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload map (item, index) -> {
  "index": index,
  "Full Name": item.FirstName ++ " " ++ item.LastName,
  "Company": item.Company
}
```

#### Groovy Script

``` groovy
payload.withIndex().collect{ item, index ->
    [
            "index": index,
            "Full Name": item.FirstName + " " + item.LastName,
            "Company": item.Company
    ]
}
```