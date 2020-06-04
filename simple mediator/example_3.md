#Simple Mediator Samples

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