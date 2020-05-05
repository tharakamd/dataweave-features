# Add and Subtract Time

## Description

This DataWeave example shows multiple addition and subtraction operations that deal with date and time types.

#### Input
``` json
{}
```
#### Output

``` json
{
  "yesterday": "2017-05-04T19:35:35.016Z",
  "theOtherDay": "2017-05-02T19:35:35.016Z",
  "a": "2002-10-01",
  "b": "2002-10-01",
  "c": "2002-10-01T23:57:59Z",
  "d": "2004-10-01T23:57:59Z",
  "e": "2002-10-01T23:57:59",
  "f": "23:50:56",
  "g": "00:08:56",
  "h": "23:50:56-03:00",
  "u": "PT1H",
  "j": "PT4H",
  "k": "2001-01-01T01:00:00",
  "l": "PT1H",
  "o": "P1Y8D",
  "p": "PT8760H",
  "q": "PT8760H"
}
```

#### Dataweave Script

```
%dw 2.0
output application/json
var numberOfDays = 3
---
{
  yesterday: now() - |P1D|,
  theOtherDay: now() - ("P$(numberOfDays)D" as Period),
  a: |2003-10-01| - |P1Y|,
  b: |P1Y| - |2003-10-01|,
  c: |2003-10-01T23:57:59Z| - |P1Y|,
  d: |2003-10-01T23:57:59Z| + |P1Y|,
  e: |2003-10-01T23:57:59| - |P1Y|,
  f: |PT9M| - |23:59:56|,
  g: |23:59:56| + |PT9M|,
  h: |23:59:56-03:00| - |PT9M|,
  u: |23:59:56-03:00| - |22:59:56-03:00|,
  j: |23:59:56-03:00| - |22:59:56-00:00|,
  k: |2003-10-01T23:57:59| - |P2Y9M1D| - |PT57M59S| + |PT2H|,
  l: |23:59:56| - |22:59:56|,
  o: |2003-10-01| - |2002-09-23|,
  p: |2003-10-01T23:57:59Z| - |2002-10-01T23:57:59Z|,
  q: |2003-10-01T23:57:59| - |2002-10-01T23:57:59|
}
```

#### Groovy Script
