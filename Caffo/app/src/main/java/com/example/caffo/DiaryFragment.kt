package com.example.caffo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.caffo.tab.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_diary.*

class DiaryFragment : Fragment() {
    var NUM_PAGES = 5
    lateinit var diaryViewPager : ViewPager2
    lateinit var diaryTabLayout : TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰페이저
        diaryViewPager = view_pager
        var pagerAdapter = DiaryViewPagerAdapter(this)
        diaryViewPager.adapter = pagerAdapter

        // 탭 레이아웃
        diaryTabLayout = tab_layout
        TabLayoutMediator(diaryTabLayout, diaryViewPager) {tab, position ->
            when (position) {
                0 -> tab.text = "컵"
                1 -> tab.text = "음료"
                2 -> tab.text = "토핑"
                3 -> tab.text = "빨대"
                else -> tab.text = "소품"
            }
        }.attach()
    }

    inner class DiaryViewPagerAdapter (fa : Fragment) : FragmentStateAdapter (fa) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CupFragment()
                1 -> DrinkFragment()
                2 -> ToppingFragment()
                3 -> StrawFragment()
                else -> PropsFragment()
            }
        }
        override fun getItemCount(): Int = NUM_PAGES
    }
}
