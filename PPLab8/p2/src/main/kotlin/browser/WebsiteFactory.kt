package browser

class WebsiteFactory(private val prototype: RealWebsite) {
    fun createWebsite(): RealWebsite {
        return prototype.clone()
    }
} //stocam si clonam site-urile valide
