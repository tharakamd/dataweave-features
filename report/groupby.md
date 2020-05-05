# Regroup Fields

## Description

These DataWeave examples take input that is grouped under one field and transform it into a new structure that groups data under another field

#### Input
``` xml
<school>
    <teachers>
        <teacher>
            <name>Mariano</name>
            <lastName>De Achaval</lastName>
            <subject>DW</subject>
        </teacher>
        <teacher>
            <name>Emiliano</name>
            <lastName>Lesende</lastName>
            <subject>DW</subject>
        </teacher>
        <teacher>
            <name>Leandro</name>
            <lastName>Shokida</lastName>
            <subject>Scala</subject>
        </teacher>
    </teachers>
</school>
```
#### Output

``` json
{
  "classrooms": {
    "class": {
      "name": "Scala",
      "teachers": {
        "teacher": {
          "name": "Leandro",
          "lastName": "Shokida"
        }
      }
    },
    "class": {
      "name": "DW",
      "teachers": {
        "teacher": {
          "name": "Mariano",
          "lastName": "De Achaval"
        },
        "teacher": {
          "name": "Emiliano",
          "lastName": "Lesende"
        }
      }
    }
  }
}
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
classrooms: payload..*teacher groupBy $.subject mapObject ((teacherGroup, subject) -> {
   class: {
     name: subject,
     teachers: { (teacherGroup map {
       teacher:{
           name: $.name,
           lastName: $.lastName
       }
     })
   }
  }
})
```

#### Groovy Script
