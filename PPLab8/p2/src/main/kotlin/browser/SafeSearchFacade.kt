package browser

import java.io.IO.println

class SafeSearchFacade {
    private var websiteFactory: WebsiteFactory? = null

    fun loadPrototype(prototype: RealWebsite) {
        websiteFactory = WebsiteFactory(prototype)
    }

    fun visitWebsite(url: String) {
        val prototype = websiteFactory?.createWebsite()
        if (prototype != null) {
            val newWebsite = RealWebsite(url)
            val protectedWebsite = WebsiteProxy(newWebsite)
            protectedWebsite.access()
        } else {
            println("Prototipul nu a fost incarcat!")
        }
    }
}//simplificarea interfetei. ascunde detalii legate de clonare si filtrare

