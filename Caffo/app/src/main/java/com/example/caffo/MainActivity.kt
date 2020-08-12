package com.example.caffo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.caffo.tab.*
import kotlinx.android.synthetic.main.fragment_diary.*

class MainActivity : AppCompatActivity() {
    var NUM_PAGES = 2
    lateinit var feedViewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedViewPager = findViewById(R.id.main_viewpager)
        var pagerAdapter = FeedViewPagerAdapter(this)
        feedViewPager.adapter = pagerAdapter

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    inner class FeedViewPagerAdapter (fa : FragmentActivity) : FragmentStateAdapter(fa) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FeedFragment()
                else -> DiaryFragment()
            }
        }
        override fun getItemCount(): Int = NUM_PAGES
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        if (requestCode == UserFragment.PICK_PROFILE_FROM_ALBUM && resultCode == Activity.RESULT_OK) {
//            var imageUri = data?.data
//            var uid = FirebaseAuth.getInstance().currentUser?.uid
//            var storageRef =
//                FirebaseStorage.getInstance().reference.child("userProfileImages").child(uid!!)
//            storageRef.putFile(imageUri!!).continueWithTask {
//                return@continueWithTask storageRef.downloadUrl
//            }.addOnSuccessListener { uri ->
//                var map = HashMap<String, Any>()
//                map["image"] = uri.toString()
//                FirebaseFirestore.getInstance().collection("profileImages").document(uid).set(map)
//            }
//        }
    }

}
