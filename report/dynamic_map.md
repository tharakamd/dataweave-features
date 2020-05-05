# Dynamically Map Based on a Definition

## Description

You can create a transformation that can dynamically change what it does depending on a definition input. This DataWeave example receives both a payload input, and a variable named mapping that specifies how to rename each field and what default value to use in each.

#### Input Payload
``` xml
<sfdc_users>
    <sfdc_user>
      <sfdc_name>Mariano</sfdc_name>
      <sfdc_last_name>Achaval</sfdc_last_name>
      <sfdc_employee>true</sfdc_employee>
    </sfdc_user>
    <sfdc_user>
      <sfdc_name>Julian</sfdc_name>
      <sfdc_last_name>Esevich</sfdc_last_name>
      <sfdc_employee>true</sfdc_employee>
    </sfdc_user>
    <sfdc_user>
      <sfdc_name>Leandro</sfdc_name>
      <sfdc_last_name>Shokida</sfdc_last_name>
    </sfdc_user>
</sfdc_users>
```

### Input variable for mapping

```json
[
  {
    "source": "sfdc_name",
    "target": "name",
    "default": "---"
  },
  {
    "source": "sfdc_last_name",
    "target": "lastName",
    "default": "---"
  },
  {
    "source": "sfdc_employee",
    "target": "user",
    "default": true
  }
]
```
#### Output

``` json
[
  [
    {"name": "Mariano"},
    {"lastName": "Achaval"},
    {"user": "true"}
  ],
  [
    {"name": "Julian"},
    {"lastName": "Esevich"},
    {"user": "true"}
  ],
  [
    {"name": "Leandro"},
    {"lastName": "Shokida"},
    {"user": true}
  ]
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
var applyMapping = (input, mappingsDef) -> (
   mappingsDef map (def) -> {
    (def.target) : input[def.source] default def."default"
  }
)
---
payload.sfdc_users.*sfdc_user map (user) -> (
        applyMapping(user, vars.mappings)
)
```