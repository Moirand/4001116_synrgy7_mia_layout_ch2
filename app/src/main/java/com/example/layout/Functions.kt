import java.text.DecimalFormat

fun autoCalculateTip(costOfService: String, percentage: Int): String? {
    return if (costOfService.isNotEmpty()) {
        val count = (removeDot(costOfService).toLong() * percentage) / 100
        addDot(count.toString())
    } else {
        null
    }
}

fun addDot(text: String): String {
    return if (text.isNotEmpty()){
        DecimalFormat("#,###").format(text.toLong())
    } else {
        ""
    }
}

fun removeDot(text: String): String {
    return if (text.isNotEmpty()){
        text.replace(".", "")
    } else {
        ""
    }
}