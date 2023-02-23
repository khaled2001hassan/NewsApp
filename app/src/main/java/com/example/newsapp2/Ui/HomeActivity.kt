package com.example.newsapp2.Ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp2.R
import com.example.newsapp2.Ui.HomeUiFragments.CategoriesFragment
import com.example.newsapp2.Ui.HomeUiFragments.SettingFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    lateinit var icDrawer: ImageView
    lateinit var CategoriesSideMenu: TextView
    lateinit var SettingSideMenu: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initListener()
        showFragment(CategoriesFragment())

    }

    fun initView() {
        icDrawer = findViewById(R.id.icDrawer)
        drawerLayout = findViewById(R.id.drawer_layout)
        CategoriesSideMenu = findViewById(R.id.CategoriesSideMenu)
        SettingSideMenu = findViewById(R.id.SettingSideMenu)
    }

    fun initListener() {
        icDrawer.setOnClickListener {
            drawerLayout.open()
        }
        CategoriesSideMenu.setOnClickListener {
            showFragment(CategoriesFragment())
            drawerLayout.close()
        }
        SettingSideMenu.setOnClickListener {
            drawerLayout.close()
            showFragment(SettingFragment())
        }

    }

    fun showFragment(fragment:Fragment ){
        supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer,fragment).commit()
    }


}