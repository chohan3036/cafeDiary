package com.example.caffo.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.caffo.R
import com.example.caffo.tab.CupFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_drink.*

class DrinkFragment: Fragment() {
    var NUM_PAGES = 5
    lateinit var drinkViewPager : ViewPager2
    lateinit var drinkTabLayout : TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰페이저
        drinkViewPager = drink_view_pager
        var pagerAdapter = DrinkViewPagerAdapter(this)
        drinkViewPager.adapter = pagerAdapter

        // 탭 레이아웃
        drinkTabLayout = drink_tab_layout
        TabLayoutMediator(drinkTabLayout, drinkViewPager) {tab, position ->
            when (position) {
                0 -> tab.text = "컵"
                1 -> tab.text = "음료"
                2 -> tab.text = "토핑"
                3 -> tab.text = "빨대"
                else -> tab.text = "소품"
            }
        }.attach()
    }

    inner class DrinkViewPagerAdapter (fa : Fragment) : FragmentStateAdapter(fa) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                // position에 따라 각 아이템 불러오도록 구현할거야!
                0 -> CupFragment(position)
                1 -> CupFragment(position)
                2 -> CupFragment(position)
                3 -> CupFragment(position)
                else -> CupFragment(position)
            }
        }
        override fun getItemCount(): Int = NUM_PAGES
    }
}