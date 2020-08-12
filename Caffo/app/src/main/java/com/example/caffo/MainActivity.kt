package com.example.caffo

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.caffo.navigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_grid.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gridFragment = GridFragment()
        supportFragmentManager.beginTransaction().add(R.id.grid_feed, gridFragment).commit()

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
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
