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
class MenuUnitTest {
    private lateinit var db: AppDatabase
    private lateinit var menuDao: MenuDao
    private lateinit var restaurantDao: RestaurantDao
    private lateinit var restaurant1: Restaurant
    private lateinit var menu1: Menu
    private lateinit var menu2: Menu

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        menuDao = db.getMenuDao()
        restaurantDao = db.getRestaurantDao()

        // insert a test restaurant
        restaurant1 = Restaurant( 1,"Casa Mono",    R.drawable.plat, "Hello" ,

            "52 Irving Pl, New York, NY 10003",-73.9877,40.7359,"Spanish",
            4.5f,100,"212-253-2773","info@casamononyc.com","https://www.facebook.com/CasaMonoNYC/",
            "https://www.instagram.com/casamononyc/")
        restaurantDao.addRestaurant(restaurant1)

        // insert some test menus
        menu1 = Menu(1, "Menu 1", "Description 1", R.drawable.dessert, 10.99, 1)
        menu2 = Menu(2, "Menu 2", "Description 2", R.drawable.pancake, 9.99, 1)
        menuDao.addMenu(menu1, menu2)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testGetMenus() {
        val menus = menuDao.getMenus()
        Assert.assertEquals(2, menus.size)
        Assert.assertEquals(menu1, menus[0])
        Assert.assertEquals(menu2, menus[1])
    }

    @Test
    fun testGetMenuById() {
        val menu = menuDao.getMenuById(1)
        Assert.assertEquals(menu1, menu)
    }

    @Test
    fun testGetRestaurantMenus() {
        val menus = menuDao.getRestaurantMenus(1)
        Assert.assertEquals(2, menus.size)
        Assert.assertEquals(menu1, menus[0])
        Assert.assertEquals(menu2, menus[1])
    }

    @Test
    fun testAddMenu() {
        val menu3 = Menu(3, "Menu 3", "Description 3", R.drawable.dessert, 12.99, 1)
        menuDao.addMenu(menu3)
        val menus = menuDao.getMenus()
        Assert.assertEquals(3, menus.size)
        Assert.assertEquals(menu3, menus[2])
    }

    @Test
    fun testUpdateMenu() {
        val updatedMenu = menu1.copy(price = 11.99)
        menuDao.updateMenu(updatedMenu)
        val menu = menuDao.getMenuById(1)
        Assert.assertEquals(updatedMenu, menu)
    }

    @Test
    fun testDeleteMenu() {
        menuDao.deleteMenu(menu1)
        val menus = menuDao.getMenus()
        Assert.assertEquals(1, menus.size)
        Assert.assertEquals(menu2, menus[0])
    }
}