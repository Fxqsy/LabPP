package browser

class RealWebsite(private val url: String) : Website {
    override fun access() {
        println("Se acceseaza site-ul: $url")
    }

    fun getUrl(): String = url

    fun clone(): RealWebsite = RealWebsite(this.url)
} //implementare concreta a unui site
