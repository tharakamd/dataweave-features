def function(arg) {
    def matcher = arg =~ "MuleSoft"
    if (matcher.size() == 1) {
        return "Match"
    } else {
        return arg
    }
}

function(payload.Company)