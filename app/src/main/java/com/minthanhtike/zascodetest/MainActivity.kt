package com.minthanhtike.zascodetest

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.shape.Shapeable
import com.minthanhtike.zascodetest.frags.BagFragments
import com.minthanhtike.zascodetest.frags.HomeFragment
import com.minthanhtike.zascodetest.frags.NotificationFrags
import com.minthanhtike.zascodetest.frags.SavedFragments
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var title:TextView
    private lateinit var shapeableImg:ShapeableImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationBarView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        title=findViewById(R.id.title_textView)
        shapeableImg=findViewById(R.id.shapeableImageView)
        bottomNavigationBarView.setOnItemSelectedListener(this)
        bottomNavigationBarView.selectedItemId=R.id.home_nav
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_nav -> {
                if (shapeableImg.drawable!=null) shapeableImg.drawable.setTint(Color.TRANSPARENT);
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
                return true
            }

            R.id.bag_nav -> {
                title.text="Cart"
                shapeableImg.setImageResource(R.drawable.menu)
                shapeableImg.drawable.setTint(resources.getColor(R.color.heavy_red,null))
                shapeableImg.setContentPadding(25,25,25,25)
                shapeableImg.setBackgroundColor(resources.getColor(R.color.light_pink,null))
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, BagFragments()).commit()
                return true
            }

            R.id.notification_nav -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, NotificationFrags()).commit()
                return true
            }

            R.id.saved_nav -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SavedFragments()).commit()
                return true
            }

            else -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
        }
        return false
    }
}