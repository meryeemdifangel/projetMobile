package com.example.movieexample

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RestaurantUnitTest {
    private lateinit var database:AppDatabase
    private lateinit var dao: RestaurantDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = database.getRestaurantDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testGetRestaurants() {
        val restaurant1 = Restaurant( 1,"Casa Mono",    R.drawable.plat, "Hello" ,

            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        val restaurant2 = Restaurant( 1,"Casa Mono",R.drawable.dessert,"Hello",
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        dao.addRestaurant(restaurant1, restaurant2)
        val restaurants = dao.getRestaurants()
        Assert.assertEquals(2, restaurants.size)
        Assert.assertTrue(restaurants.contains(restaurant1))
        Assert.assertTrue(restaurants.contains(restaurant2))
    }

    @Test
    fun testGetRestaurantById() {
        val restaurant1 = Restaurant( 1,"Casa Mono",R.drawable.plat,"Hello" ,
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        val restaurant2 = Restaurant( 1,"Casa Mono", R.drawable.dessert,"Hello",
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        dao.addRestaurant(restaurant1, restaurant2)
        val retrievedRestaurant1 = dao.getRestaurantById(1)
        val retrievedRestaurant2 = dao.getRestaurantById(2)
        Assert.assertEquals(restaurant1, retrievedRestaurant1)
        Assert.assertEquals(restaurant2, retrievedRestaurant2)
    }

    @Test
    fun testAddRestaurant() {
        val restaurant = Restaurant( 1,"Casa Mono",R.drawable.plat,"Hello" ,
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        dao.addRestaurant(restaurant)
        val retrievedRestaurant = dao.getRestaurantById(1)
        Assert.assertEquals(restaurant, retrievedRestaurant)
    }

    @Test
    fun testUpdateRestaurant() {
        val restaurant = Restaurant( 1,"Casa Mono",R.drawable.plat,"Hello" ,
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        dao.addRestaurant(restaurant)
        val updatedRestaurant = Restaurant( 1,"Casa Mono updated restaurant ",R.drawable.plat,"updated restaurant " ,
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        dao.updateRestaurant(updatedRestaurant)
        val retrievedRestaurant = dao.getRestaurantById(1)
        Assert.assertEquals(updatedRestaurant, retrievedRestaurant)
    }

    @Test
    fun testDeleteRestaurant() {
        val restaurant = Restaurant( 1,"Casa Mono",R.drawable.plat,"Hello" ,
            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        dao.addRestaurant(restaurant)
        dao.deleteRestaurant(restaurant)
        val retrievedRestaurant = dao.getRestaurantById(1)
        Assert.assertNull(retrievedRestaurant)
    }

}