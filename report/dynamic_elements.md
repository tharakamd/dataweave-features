# Dynamic elements

## Description

In DataWeave, there are multiple instances where you will need to output a list of elements from an array but not based on the index. In DataWeave, we have special syntax known as dynamic elements which allows you to add the result of an expression key-value pair of an object. In the example below, we return all of the entries in the array that match with the string in the conditional. First, we call the array by payload.entries, then in the square brackets, we use the filter selector defined by the question mark, which searches through an array or object for all key-value pairs. The condition is defined by a $ followed by a single-value selector which will return any value that belongs to its corresponding key. The dollar sign variable allows the code to gain access to each item in the array.

## Input
``` javascript
{
  "entries": [
    {
      "FirstName": "Max",
      "LastName": "Mule",
      "Company": "MuleSoft",
      "Email": "maxthemule@mulesoft.com"
    },
    {
      "FirstName": "Astro",
      "LastName": "Nomical",
      "Company": "Salesforce",
      "Email": "astro@salesforce.com"
    },
    {
      "FirstName": "Cloudy",
      "LastName": "the Goat",
      "Company": "Salesforce",
      "Email": "cloudy@salesforce.com"
    }
  ]
}
```
## Output

``` javascript
[
  {
    "FirstName": "Max",
    "LastName": "Mule",
    "Company": "MuleSoft",
    "Email": "maxthemule@mulesoft.com"
  }
]
```

## Dataweave Script

```
%dw 2.0
output application/json
---
payload.entries[?($."Company" == "MuleSoft")]
```

## Groovy Script

``` groovy
payload.entries.findAll{it.Company == "MuleSoft"}
```