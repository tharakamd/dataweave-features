# Extracting value from JSON

## Description

Extract a value from a JSON object

#### Input
``` json
{
"message": "Hello World!"
}
```
#### Output

``` json
"Hello World!"
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload.message
```

#### Groovy Script

``` groovy
payload.message
```