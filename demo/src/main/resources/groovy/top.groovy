[
        TopCandidateList: payload.candidates.collect {
            [
                    firstName: it.name,
                    rank     : it.score
            ]
        }.sort { it.rank }[-payload.availablePositions..-1]
]