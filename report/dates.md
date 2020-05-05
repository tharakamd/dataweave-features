# Parse Dates 

## Description

These DataWeave examples define a function (fun) in the DataWeave header to normalize date separators (/, ., and -) within different date formats so that all of them use the same separator (-).

#### Input
``` xml
<dates>
  <date>26-JUL-16</date>
  <date>27/JUL/16</date>
  <date>28.JUL.16</date>
</dates>
```
#### Output

``` xml
<?xml version='1.0' encoding='US-ASCII'?>
<dates>
  <normalized_as_string>26-JUL-16</normalized_as_string>
  <normalized_as_string>27-JUL-16</normalized_as_string>
  <normalized_as_string>28-JUL-16</normalized_as_string>
</dates>
```

#### Dataweave Script

```
%dw 2.0
output text/xml
fun normalize(date) date replace "/" with "-" replace "." with "-"
---

dates: (payload.dates mapObject {
    normalized_as_string: normalize($.date)
})
```
