#Simple Mediator Samples

### Example 1

*Input*

```json
[
    {
        "first_name": "Lissie",
        "last_name": "Linguard",
        "gender": "Female"
    },
    {
        "first_name": "Rufe",
        "last_name": "Wycliffe",
        "gender": "Male"
    },
    {
        "first_name": "Sisile",
        "last_name": "Van Hove",
        "gender": "Female"
    },
    {
        "first_name": "Brandyn",
        "last_name": "Mc Queen",
        "gender": "Male"
    },
    {
        "first_name": "Erminia",
        "last_name": "Saintsbury",
        "gender": "Female"
    }
]
```

#### Scenario 1
Filter and transform 

*output*
```json
[
    {
        "first_name": "Rufe",
        "last_name": "Wycliffe",
        "gender": "Male",
        "full_name": "Rufe Wycliffe"
    },
    {
        "first_name": "Brandyn",
        "last_name": "Mc Queen",
        "gender": "Male",
        "full_name": "Brandyn Mc Queen"
    }
]
```

*code*
```java
public void mediate(SimpleMessageContext mc) 
      mc.getJsonArrayStream()
              .map(JsonElement::getAsJsonObject)
              .filter(jsonObject -> jsonObject.get("gender").getAsString().equals("Male"))
              .map(jsonObject -> {
                  String fullName =
                          jsonObject.get("first_name").getAsString() + " " + jsonObject.get("last_name").getAsString();
                  jsonObject.add("full_name", new JsonPrimitive(fullName));
                  return jsonObject;
              })
              .collect(mc.collectToJsonArray());
}
```

#### Scenario 2
Use index

*output*
```json
[
    {
        "first_name": "Lissie",
        "last_name": "Linguard",
        "gender": "Female",
        "id": 0
    },
    {
        "first_name": "Rufe",
        "last_name": "Wycliffe",
        "gender": "Male",
        "id": 1
    },
    {
        "first_name": "Sisile",
        "last_name": "Van Hove",
        "gender": "Female",
        "id": 2
    },
    {
        "first_name": "Brandyn",
        "last_name": "Mc Queen",
        "gender": "Male",
        "id": 3
    },
    {
        "first_name": "Erminia",
        "last_name": "Saintsbury",
        "gender": "Female",
        "id": 4
    }
]
```

*code*

```java
public void mediate(SimpleMessageContext mc) {
     mc.getJsonArrayStreamWithIndex()
             .map(indexedElement -> {
                 int index = indexedElement.getIndex();
                 JsonObject jsonObject = indexedElement.getElement().getAsJsonObject();
                 jsonObject.add("id", new JsonPrimitive(index));
                 return jsonObject;
             })
             .collect(mc.collectToJsonArray());
}
```

#### Scenario 3

Json path and wrapper object

*output*
```json
{
    "names": [
        "Lissie",
        "Rufe",
        "Sisile",
        "Brandyn",
        "Erminia"
    ]
}
```

*code*

```java
public void mediate(SimpleMessageContext mc) {
       mc.getJsonArrayStream("$.[*].first_name")
               .collect(mc.collectToJsonArray("names"));
}
```

#### Scenario 4
Map to CSV

*output*

```text
id,name
0,Lissie
1,Rufe
2,Sisile
3,Brandyn
4,Erminia
```

*code*

```java
public void mediate(SimpleMessageContext mc) {

   mc.getJsonArrayStreamWithIndex("$.[*].first_name")
              .map(indexedElement -> {
                  String[] csvRow = new String[2]
                  csvRow[0] = String.valueOf(indexedElement.getIndex());
                  csvRow[1] = indexedElement.getElement().getAsString()
                  return csvRow
              }).collect(mc.collectToCsv(new String[]{"id", "name"}));

}
```