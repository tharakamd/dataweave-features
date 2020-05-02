# Mapping using selectors

## Description

Now let’s move on to a more complicated map example using selectors. In the demonstration below, the sample input data has all of its elements listed under accountType, where there are two sub-sections, users and admins. Inside of each of these arrays is user information which includes the person's Name and Company. The goal is to list out ONLY the data under the users array which will be remapped under the array named accountInfo.

To achieve this transformation, we start off by defining that we want to map the payload.accountType array. We then use a multi-selector, signified by *users, which is used to run the map command on entries matching with the name users. In DataWeave, you can represent the index count by using a double dollar sign $$ and can represent the element with a single dollar sign $. In this example, we use the single dollar sign to list out the elements in each object.

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
  }
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload.accountType.*users map {
  accountInfo: $
}
```

#### Groovy Script

``` groovy
[accountInfo: payload.accountType.users.flatten()]
```

## Example 2

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
    "index": 0,
    "accountInfo": [
      "Max",
      "MuleSoft"
    ]
  }
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload.accountType.*admins map {
  index: $$,
  accountInfo: $.Name ++ $.Company,
}
```
