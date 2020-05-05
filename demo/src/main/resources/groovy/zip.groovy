payload.collect {
    [
            name        : it.name,
            id          : it.itemID,
            screws      : [it.screws.size, it.screws.quantity].transpose(),
            measurements: [it.measurements.x, it.measurements.y].transpose()
    ]
}