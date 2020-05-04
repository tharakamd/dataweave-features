payload.accountType[0].collect().withIndex().collect { element, index ->
    [
            (index)      : element.key,
            "accountInfo": element.value
    ]
}