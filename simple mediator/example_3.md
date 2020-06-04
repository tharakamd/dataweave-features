# Simple Mediator Samples

### Example 3

*input*

```text
first_name,last_name,gender
Eli,Sheehan,Male
Roshelle,Bugdell,Female
Neda,Verdey,Female
Konstantin,Screen,Male
Caryn,Davson,Female
```

#### Scenario 1
Filter csv

*output*

```text
first_name,last_name,gender
Eli,Sheehan,Male
Konstantin,Screen,Male
```

*code*

```java
public void mediate(SimpleMessageContext mc) {
       mc.getCsvArrayStream(1)
               .filter(row -> row[2].equals("Male"))
               .collect(mc.collectToCsv(new String[]{"first_name","last_name","gender"}));
}
```

#### Scenario 2
Csv to Json

*output*

```json
[
    {
        "id": 0,
        "first_name": "Eli",
        "last_name": "Sheehan",
        "gender": "Male"
    },
    {
        "id": 3,
        "first_name": "Konstantin",
        "last_name": "Screen",
        "gender": "Male"
    }
]
```

*code*

```java
public void mediate(SimpleMessageContext mc) {
   mc.getCsvArrayStreamWithIndex(1)
             .filter(indexedElement -> indexedElement.getElement()[2].equals("Male"))
             .map(indexedElement -> {
                 String[] row = indexedElement.getElement();
                 JsonObject jsonObject = new JsonObject();
                 jsonObject.add("id", new JsonPrimitive(indexedElement.getIndex()));
                 jsonObject.add("first_name", new JsonPrimitive(row[0]));
                 jsonObject.add("last_name", new JsonPrimitive(row[1]));
                 jsonObject.add("gender", new JsonPrimitive(row[2]));
                 return jsonObject;
             }).collect(mc.collectToJsonArray());
}
```
