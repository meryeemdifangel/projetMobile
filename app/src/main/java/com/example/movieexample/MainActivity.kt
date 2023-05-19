package com.example.movieexample

import MyAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // List of menus
        val menus = mutableListOf<Menu>()
        menus.add(Menu(1, "menu 1", "grilled with mushroom", R.drawable.plat, 0, 20.0, 1))
        menus.add(Menu(2, "menu 2", "grilled with mushroom", R.drawable.pancake, 0, 30.0, 1))
        menus.add(Menu(3, "menu 3", "grilled with mushroom", R.drawable.dessert, 0, 40.0,2))


        val instance = AppDatabase.buildDatabase(this.applicationContext)

        if (instance?.getMenuDao()?.getMenus()?.isEmpty() == true){
            menus.forEach { instance.getMenuDao().addMenu(it) }
        }else{
            for (menu in menus) {
                val dbMenus = AppDatabase.buildDatabase(this)?.getMenuDao()?.getMenus()!!
                val dbMenu = dbMenus.find { it.menuId == menu.menuId }
                if (dbMenu == null) {
                    // menu not in database, add it
                    instance?.getMenuDao()?.addMenu(menu)
                } else if (dbMenu != menu) {
                    // menu in database but different, update it
                    instance?.getMenuDao()?.updateMenu(menu)
                }
            }
        }

        binding.cart.setOnClickListener {
            val fragment = CartFragment()
            this.supportFragmentManager.beginTransaction().replace(
                android.R.id.content,
                fragment
            ).addToBackStack(null).commit()
        }

        val navHostFragment = supportFragmentManager. findFragmentById(R.id.fragmentContainerView4) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navBottom,navController)

    }
}