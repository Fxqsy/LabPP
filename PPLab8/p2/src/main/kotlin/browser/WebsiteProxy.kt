package browser

class WebsiteProxy(private val realWebsite: RealWebsite) : Website {
    private val blacklist = listOf("violence", "porn", "gambling", "drugs", "death", "kill", "gun", "weapon",
        "blood", "crime", "abuse", "suicide", "alcohol", "tobacco", "cigarette", "curse", "explicit", "adult", "virus", "malware", "hack", "phishing", "darkweb" , "bet", "cazino" , "casino")

    override fun access() {
        if (blacklist.any { realWebsite.getUrl().contains(it, ignoreCase = true) }) {
            println("Acces respins pentru: ${realWebsite.getUrl()}")
        } else {
            realWebsite.access()
        }
    }
} //filtrarea controlului parental prin cuvinte interzise
