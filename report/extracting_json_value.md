# Extracting value from JSON

## Description

Extract a value from a JSON object

#### Input
``` javascript
{
"message": "Hello World!"
}
```
#### Output

``` javascript
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