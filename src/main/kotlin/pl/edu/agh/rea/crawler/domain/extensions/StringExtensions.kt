package pl.edu.agh.rea.crawler.domain.extensions

fun String.removeNonNumericCharacters(): String {
    return this.replace(Regex("[^0-9.,]"), "").replace(',', '.')
}