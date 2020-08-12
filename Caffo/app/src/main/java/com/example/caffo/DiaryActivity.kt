package com.example.caffo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.caffo.tab.CupFragment
import com.example.caffo.tab.DrinkFragment
import com.example.caffo.tab.StrawFragment
import com.example.caffo.tab.ToppingFragment

class DiaryActivity : AppCompatActivity() {
    var NUM_PAGES = 4
    lateinit var viewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        viewPager = findViewById(R.id.view_pager)

        var pagerAdapter = SlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {}

    inner class SlidePagerAdapter (fa : FragmentActivity) : FragmentStateAdapter (fa) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CupFragment()
                1 -> DrinkFragment()
                2 -> ToppingFragment()
                else -> StrawFragment()
            }
        }
        override fun getItemCount(): Int = NUM_PAGES
    }
}
