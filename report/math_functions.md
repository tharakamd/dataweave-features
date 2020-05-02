# Extracting value from JSON

## Description

EDataWeave comes with a full set of mathematical equations pre-installed into the language. You can use the random() function to generate a random number between 0 and 1.

#### Input
``` javascript
{}
```
#### Output

``` javascript
0.19040448181053804
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
random()
```

#### Groovy Script


### Example 2

#### Input
``` javascript
[
  2,
  2,
  2
]
```
#### Output

``` javascript
6
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
sum(payload)
```

#### Groovy Script


### Example 3

#### Input
``` javascript
[
  2,
  3,
  4
]
```
#### Output

``` javascript
1.7320508075688772
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
sqrt(avg(payload))
```

#### Groovy Script
