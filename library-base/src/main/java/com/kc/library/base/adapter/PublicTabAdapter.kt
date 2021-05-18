package com.kc.library.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @data on 4/23/21 9:35 PM
 * @auther KC
 * @describe TabLayout+ViewPager适配器
 */
class PublicTabAdapter(fm: FragmentManager?,
                       private val list_fragment: ArrayList<Fragment>,
                       private val list_Title: ArrayList<String>) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return list_fragment[position]
    }

    override fun getCount(): Int {
        return list_fragment.size
    }

    // 此方法用来显示tab上的名字
    override fun getPageTitle(position: Int): CharSequence? {
        return list_Title[position % list_Title.size]
    }
}