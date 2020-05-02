# Match

## Description

The match function allows you to match a string and then return the results in an array. If you have ever used Java regex before, the match function uses the same library to allow pattern matching on strings. In the example below, we used the literalMatch case to output “Match” if the payload.Company string is equal to “MuleSoft”. In the script, you will notice a dollar sign in the else statement. A single dollar sign is used for accessing the value passed by the function. You can also replace the dollar sign with arg (in this use-case) and the output will have the same result. There are three different operators you can use in your DataWeave scripts: $ (value), $$ (key), and $$$ (index).

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
fun function(arg) = arg match{
   case literalMatch: "MuleSoft" -> "Match"
   else -> $
}
---
function(payload.Company)
```
