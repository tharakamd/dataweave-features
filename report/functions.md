# Functions

## Description

Can use functions in the script

#### Input
``` javascript
{
  "FirstName": "Max",
  "LastName": "Mule",
  "Company": "MuleSoft"
}
```
#### Output

``` javascript
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