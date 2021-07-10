payload.accountType[0].collect{ item ->
    [
            "index": item.properties.key,
            "accountInfo": item.properties.value
    ]
}
