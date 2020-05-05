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