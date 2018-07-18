package com.dingwei.dingwei.ui.activity

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dingwei.dingwei.R
import pub.devrel.easypermissions.EasyPermissions


/**
 * Created by kangjie on 2018/7/18.
 */
class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "MainActivity"
        private val LOCATION_AND_CONTACTS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS)

        private val RC_CAMERA_PERM = 123
        private val RC_LOCATION_CONTACTS_PERM = 124
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationAndContactsTask()
    }

    fun locationAndContactsTask() {
        if (hasLocationAndContactsPermissions()) {
            // Have permissions, do the thing!
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show()
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    "aaaa",
                    RC_LOCATION_CONTACTS_PERM,
                    *LOCATION_AND_CONTACTS)
        }
    }

    private fun hasLocationAndContactsPermissions(): Boolean {
        return EasyPermissions.hasPermissions(this, *LOCATION_AND_CONTACTS)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}