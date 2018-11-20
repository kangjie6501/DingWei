package com.dingwei.dingwei.ui.activity

import android.Manifest
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.MainContract
import com.dingwei.dingwei.mvp.presenter.MainPersenter
import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.service.TraceServiceImpl
import com.dingwei.dingwei.utils.Preference
import com.xdandroid.hellodaemon.DaemonEnv
import com.xdandroid.hellodaemon.IntentWrapper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_layout.*
import pub.devrel.easypermissions.EasyPermissions


/**
 * Created by kangjie on 2018/7/18.
 */
class MainActivity : BaseActivity(), MainContract.View {
//    private var token:String by Preference("token", "")
    private var mUserId:String by Preference<String>("userId","")
    private val mPresenter by lazy { MainPersenter() }
    init {
        mPresenter.attachView(this)
    }
    companion object {
        private val TAG = "MainActivity"
        private val LOCATION_AND_CONTACTS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION/*, Manifest.permission.READ_CONTACTS*/)

        private val RC_CAMERA_PERM = 123
        private val RC_LOCATION_CONTACTS_PERM = 124
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }


    override fun showError(errorMsg: String) {
        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
    }


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        var a:String = mUserId
        Log.e("userID",a)
        if (mUserId == null ||mUserId.equals("")){
            goto_login_btn.callOnClick()
            return
        }
        locationAndContactsTask()
    }

    override fun initView() {

       logout_btn.setOnClickListener{
           view ->
           Preference("","").clearPreference()
           startActivity(Intent(this,LoginActivity::class.java))
       }

        clear_location_btn.setOnClickListener{
            view->
            mPresenter.clearLocationByUser(mUserId)
        }
    }

    override fun start() {

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


    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start -> {
                TraceServiceImpl.sShouldStopService = false
                DaemonEnv.startServiceMayBind(TraceServiceImpl::class.java)
            }
            R.id.btn_white -> IntentWrapper.whiteListMatters(this, "轨迹跟踪服务的持续运行")
            R.id.btn_stop -> TraceServiceImpl.stopService()
        }
    }

    //防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀
    override fun onBackPressed() {
        IntentWrapper.onBackPressed(this)
    }

    override fun setClearLocationView(loginBean: BaseResponce<String>) {
       Toast.makeText(this,"已清除",Toast.LENGTH_SHORT).show()
    }

}