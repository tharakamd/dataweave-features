payload.withIndex().collect{ item, index ->
    [
            "index": index,
            "Full Name": item.FirstName + " " + item.LastName,
            "Company": item.Company
    ]
}
