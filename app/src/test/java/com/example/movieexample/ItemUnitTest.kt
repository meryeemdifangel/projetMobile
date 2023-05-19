import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.movieexample.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemUnitTest  {

    private lateinit var itemDao: ItemDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        itemDao = database.getItemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun testGetCart() {
        val item1 = Item(1, 1, 2)
        val item2 = Item(2, 1, 3)
        itemDao.addItem(item1, item2)
        val cart = itemDao.getCart()
        assert(cart.size == 2)
        assert(cart.contains(item1))
        assert(cart.contains(item2))
    }

    @Test
    @Throws(Exception::class)
    fun testDeleteCart() {
        val item1 = Item(1, 1, 2)
        val item2 = Item(2, 1, 3)
        itemDao.addItem(item1, item2)
        itemDao.deleteCart()
        val cart = itemDao.getCart()
        assert(cart.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun testUpdateItem() {
        val item1 = Item(1, 1, 2)
        itemDao.addItem(item1)
        item1.quantity = 3
        itemDao.updateItem(item1)
        val updatedItem = itemDao.getCart().first()
        assert(updatedItem.quantity == 3)
    }

    @Test
    @Throws(Exception::class)
    fun testDeleteItem() {
        val item1 = Item(1, 1, 2)
        val item2 = Item(2, 1, 3)
        itemDao.addItem(item1, item2)
        itemDao.deleteItem(item1)
        val cart = itemDao.getCart()
        assert(!cart.contains(item1))
        assert(cart.contains(item2))
    }

}