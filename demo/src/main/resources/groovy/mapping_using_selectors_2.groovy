payload.accountType*.admins.flatten().withIndex().collect { item, index ->
    [
            index      : index,
            accountInfo: [item.Name, item.Company]
    ]
}