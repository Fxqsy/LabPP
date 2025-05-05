package browser

import java.io.IO.println

fun main() {
    val facade = SafeSearchFacade()
    val defaultSite = RealWebsite("https://www.google.com")
    facade.loadPrototype(defaultSite)

    facade.visitWebsite("https://www.miniclip.com/")
    facade.visitWebsite("https://www.vladcazino.ro/")
}
