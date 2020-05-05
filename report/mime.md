# MIME types

## Description

Many data transformations require requiring different input and output requirements. In order to support these different read and write formats, you can specify the MIME type for both the input and the output data that flows through the Mule application. To change the MIME type, set the type in the header as shown below. In the examples below, observe the differences in output vs application/json and text/plain.

#### Input
``` json
{
  "FirstName": "Max",
  "LastName": "Mule",
  "Company": "MuleSoft"
}
```
#### Output

``` json
"Max"
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload.FirstName
```