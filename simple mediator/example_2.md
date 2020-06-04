# Simple Mediator Samples

### Example 2

*input*

```xml
<?xml version='1.0' encoding='UTF-8'?>
<dataset>
  <record>
    <first_name>Eilis</first_name>
    <last_name>Howatt</last_name>
    <gender>Female</gender>
  </record>
  <record>
    <first_name>Efren</first_name>
    <last_name>MacGille</last_name>
    <gender>Male</gender>
  </record>
  <record>
    <first_name>Jayson</first_name>
    <last_name>Boame</last_name>
    <gender>Male</gender>
  </record>
  <record>
    <first_name>Dennis</first_name>
    <last_name>Fenkel</last_name>
    <gender>Male</gender>
  </record>
  <record>
    <first_name>Aeriela</first_name>
    <last_name>Tirkin</last_name>
    <gender>Female</gender>
  </record>
</dataset>
```

#### Scenario 1
Xml to Json

*output*
```json
[
    {
        "first_name": "Eilis",
        "last_name": "Howatt",
        "gender": "Female"
    },
    {
        "first_name": "Efren",
        "last_name": "MacGille",
        "gender": "Male"
    },
    {
        "first_name": "Jayson",
        "last_name": "Boame",
        "gender": "Male"
    },
    {
        "first_name": "Dennis",
        "last_name": "Fenkel",
        "gender": "Male"
    },
    {
        "first_name": "Aeriela",
        "last_name": "Tirkin",
        "gender": "Female"
    }
]
```

*code*
```java
public void mediate(SimpleMessageContext mc) {
   mc.getXmlChildElementsStream()
             .map(omElement -> {
                 JsonObject jsonObject = new JsonObject();
                 jsonObject.add("first_name",
                         new JsonPrimitive(omElement.getFirstChildWithName(new QName("first_name")).getText()));
                 jsonObject.add("last_name", new JsonPrimitive(omElement.getFirstChildWithName(new QName(
                         "last_name")).getText()));
                 jsonObject.add("gender",
                         new JsonPrimitive(omElement.getFirstChildWithName(new QName("gender")).getText()));
                 return jsonObject;
             }).collect(mc.collectToJsonArray());
}
```

#### Scenario 2
XPath, Modifying root xml element

*output*

```xml
<People>
    <Person id="0" name="Eilis"/>
    <Person id="1" name="Efren"/>
    <Person id="2" name="Jayson"/>
    <Person id="3" name="Dennis"/>
    <Person id="4" name="Aeriela"/>
</People>
```

*code*

```java
public void mediate(SimpleMessageContext mc) {
    final OMFactory fac = OMAbstractFactory.getOMFactory();
    OMElement rootElement = fac.createOMElement(new QName("People"));
    mc.getXmlElementsStreamWithIndex("//first_name")
            .forEach(omElementIndexedElement -> {
               OMElement personElement = fac.createOMElement(new QName("Person")) ;
               personElement.addAttribute("id", String.valueOf(omElementIndexedElement.getIndex()), null);
               personElement.addAttribute("name", omElementIndexedElement.getElement().getText(), null);
               rootElement.addChild(personElement);
            });
    mc.replaceRootXmlElement(rootElement);
}
```