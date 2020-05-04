// this is the correct way of reducing an array
payload.inject { total, element -> total + element }
// but following is easy to get the sum of elements in an array
//payload.sum()