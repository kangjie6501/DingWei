package com.dingwei.dingwei.ui.activity

import android.Manifest
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dingwei.dingwei.MyApplication
import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.MainContract
import com.dingwei.dingwei.mvp.model.bean.LoginBean
import com.dingwei.dingwei.mvp.presenter.MainPersenter
import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.service.TraceServiceImpl
import com.example.zhouwei.library.CustomPopWindow
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
    private var mUserId: String? = null
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
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mUserId = MyApplication.getSetting()!!.loadString("userId")
        if (mUserId == null || mUserId.equals("")) {
            goto_login_btn.callOnClick()
            return
        }
        locationAndContactsTask()
    }

    override fun initView() {

        logout_btn.setOnClickListener { view ->
        //    Preference("", "").clearPreference()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        clear_location_btn.setOnClickListener { view ->
            mPresenter.clearLocationByUser(this!!.mUserId!!)
        }

        mine_location_btn.setOnClickListener { view ->
            startActivity(Intent(this, LocationActivity::class.java))
        }

        mine_attention_add.setOnClickListener{
            view ->
            if (main_name_et.text!=null&&main_name_et.text.toString()!=null&&main_name_et.text.toString().trim()!=null&&!main_name_et.
                    text.toString().trim().equals("")){
                mPresenter.addAttention(this!!.mUserId!!,main_name_et.text.toString().trim())
            }
        }

        mine_attention_user.setOnClickListener{
            v ->
            mPresenter.getAttentions(this!!.mUserId!!)
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
  /*  override fun onBackPressed() {
        IntentWrapper.onBackPressed(this)
    }*/

    override fun setClearLocationView(loginBean: BaseResponce<String>) {
        Toast.makeText(this, "已清除", Toast.LENGTH_SHORT).show()
    }

    override fun showAddAttentionSuccess(loginBean: LoginBean) {
        addOneAttention(loginBean)
    }
    var mCustomPopWindow: CustomPopWindow? = null
    private fun addOneAttention(loginBean: LoginBean) {
        var view = View.inflate(this,R.layout.item_attention_user_layout,null)
        view.findViewById<TextView>(R.id.item_attention_name).text = loginBean.name
        view.findViewById<TextView>(R.id.item_attention_phone).text = loginBean.phone
        main_user_ll.addView(view)
        view.setOnClickListener{
            v ->
            val contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu, null)
            //处理popWindow 显示内容
            handleLogic(contentView)
            //创建并显示popWindow
            mCustomPopWindow = CustomPopWindow.PopupWindowBuilder(this)
                    .setView(contentView)
                    .create()
                    .showAsDropDown(v, 0, 20)
        }
    }

    private fun handleLogic(contentView: View) {
        val listener = View.OnClickListener { v ->
            if (mCustomPopWindow != null) {
                mCustomPopWindow!!.dissmiss()
            }
            var showContent = ""
            when (v.id) {
                R.id.menu1 -> showContent = "点击 Item菜单1"
                R.id.menu2 -> showContent = "点击 Item菜单2"
                R.id.menu3 -> showContent = "点击 Item菜单3"
                R.id.menu4 -> showContent = "点击 Item菜单4"
                R.id.menu5 -> showContent = "点击 Item菜单5"
            }
            Toast.makeText(this@MainActivity, showContent, Toast.LENGTH_SHORT).show()
        }
        contentView.findViewById<LinearLayout>(R.id.menu1).setOnClickListener(listener)
        contentView.findViewById<LinearLayout>(R.id.menu2).setOnClickListener(listener)
        contentView.findViewById<LinearLayout>(R.id.menu3).setOnClickListener(listener)
        contentView.findViewById<LinearLayout>(R.id.menu4).setOnClickListener(listener)
        contentView.findViewById<LinearLayout>(R.id.menu5).setOnClickListener(listener)
    }

    override fun showAttentions(loginBeans: List<LoginBean>) {
        main_user_ll.removeAllViews()
        for (l:LoginBean in loginBeans){
            addOneAttention(l)
        }
    }

    override fun showCancleAttentionSuccess(s: String) {
        Toast.makeText(this,"取消成功",Toast.LENGTH_SHORT).show()
    }


}