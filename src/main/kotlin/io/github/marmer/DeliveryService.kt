package io.github.marmer

interface DeliveryService {
    fun getStarFrom(coordinates: String): Star
    fun sendTo(coordinates: String, star: Star)

}
