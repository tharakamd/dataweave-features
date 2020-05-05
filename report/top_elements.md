# Pick Top Elements

## Description

This DataWeave example sorts an array of candidates by the score they got in a test, then picks only the ones with the best score, as many as there are open positions to fill

#### Input
``` json
{
 "availablePositions": 3,
 "candidates":
    [
      {
        "name":"Gunther Govan",
        "score":99
      },
       {
        "name":"Michael Patrick",
        "score":35
      },
      {
        "name":"Amalia Silva",
        "score":96
      },
       {
        "name":"Tom Mathews",
        "score":40
      },
      {
        "name":"Simon Wilson",
        "score":84
      },
      {
        "name":"Janet Nguyen",
        "score":52
      }
    ]
}
```
#### Output

``` json
{
  "TopCandidateList": [
    {
      "firstName": "Simon Wilson",
      "rank": 84
    },
    {
      "firstName": "Amalia Silva",
      "rank": 96
    },
    {
      "firstName": "Gunther Govan",
      "rank": 99
    }
  ]
}
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
{
    TopCandidateList: (payload.candidates map ((candidate) -> {
        firstName: candidate.name,
        rank: candidate.score
    }) orderBy $.rank) [ -payload.availablePositions to -1]
}
```

#### Groovy Script

``` groovy
[
        TopCandidateList: payload.candidates.collect{
            [
                    firstName: it.name,
                    rank: it.score
            ]
        }.sort{it.rank}[-payload.availablePositions .. -1]
]
```