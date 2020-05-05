# Zip Arrays Together

## Description

This DataWeave example restructures bills of materials for Ikea-style furniture. The input contains the measurements and amounts of screws in two separate arrays that run in parallel, the transformation reorders them so that the "screws" array is made up of tuples, each with a measurement and its corresponding amount. The same is applied to wooden boards: the input contains two arrays with the x and the y measurements of each; the transformation rearranges them into a series of tuples, one for each board.

#### Input
``` json
[
   {
    "name":"wooden-chair",
    "itemID": "23665",
    "screws":{
      "size":[4,6,10],
      "quantity":[15,8,28]
    },
    "measurements":
    {"x":[25,46, 46, 16,150,5, 100, 100, 8],
    "y":[15,4, 4, 80,3, 4, 4, 15]
    }
  },
   {
    "name":"cofee-table",
    "itemID": "14398",
    "screws":{
      "size":[3,8,10],
      "quantity":[8,12,20]
    },
    "measurements":
    {"x":[55, 48, 48, 48, 48, 30, 30, 30, 30],
    "y":[55, 40, 40, 40, 50, 4, 4, 4, 4]
    }
  }
]
```
#### Output

``` json
[
  {
    "name": "wooden-chair",
    "id": "23665",
    "screws": [
      [4, 15],
      [6, 8],
      [10, 28]
    ],
    "measurements": [
      [25,  15],
      [46, 4],
      [46,  4],
      [16,  80],
      [150,  3],
      [5,  4],
      [100,  4],
      [100,  15]
    ]
  },
  {
    "name": "cofee-table",
    "id": "14398",
    "screws": [
      [3, 8],
      [8, 12],
      [10, 20]
    ],
    "measurements": [
      [55, 55],
      [48, 40 ],
      [48, 40],
      [48, 40],
      [48,50],
      [30, 4],
      [30, 4],
      [30, 4],
      [30, 4]
    ]
  }
]
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
payload map (item, index) ->
{
    name: item.name,
    id: item.itemID,
    screws: zip(item.screws.size, item.screws.quantity),
    measurements: zip(item.measurements.x,item.measurements.y )
}
```

#### Groovy Script

``` groovy
payload.collect {
    [
    name: it.name,
    id: it.itemID,
    screws: [it.screws.size, it.screws.quantity].transpose(),
    measurements: [it.measurements.x,it.measurements.y ].transpose()
    ]
}
```