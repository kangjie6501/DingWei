package com.dingwei.dingwei

import DisplayManager
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.dingwei.dingwei.mvp.model.LocationModel
import com.dingwei.dingwei.mvp.model.bean.LocationBean
import com.dingwei.dingwei.service.TraceServiceImpl
import com.dingwei.dingwei.utils.Preference
import com.google.gson.Gson
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.xdandroid.hellodaemon.DaemonEnv
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


/**
 * Created by xuhao on 2017/11/16.
 *
 */

class MyApplication : Application(){
    //声明AMapLocationClient类对象
    var mLocationClient: AMapLocationClient? = null
    //声明定位回调监听器
    var mLocationListener: AMapLocationListener = MyAMapLocationListener()
    //声明AMapLocationClientOption对象
    var mLocationOption: AMapLocationClientOption? = null

    private var refWatcher: RefWatcher? = null

    companion object {

        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: FragmentActivity?): RefWatcher? {
            val myApplication = context!!.applicationContext as MyApplication
            return myApplication.refWatcher
        }

    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        refWatcher = setupLeakCanary()
        initConfig()
        DisplayManager.init(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        initLoction(this)
        DaemonEnv.initialize(
                this,  //Application Context.
                TraceServiceImpl::class.java, //刚才创建的 Service 对应的 Class 对象.
                null) //定时唤醒的时间间隔(ms), 默认 6 分钟.

        startService( Intent(this,TraceServiceImpl::class.java))
    }



    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }


    /**
     * 初始化配置
     */
    private fun initConfig() {

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 隐藏线程信息 默认：显示
                .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("hao_zz")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }

    fun initLoction(baseApp: MyApplication) {

        //初始化定位
        mLocationClient = AMapLocationClient(applicationContext)
        //设置定位回调监听
        mLocationClient!!.setLocationListener(mLocationListener)
        //初始化AMapLocationClientOption对象
        mLocationOption = AMapLocationClientOption()
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption!!.setInterval(20000)
        //  mLocationOption.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption!!.setNeedAddress(true)

        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption!!.setWifiActiveScan(false)
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption!!.setMockEnable(true)

        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption!!.setHttpTimeOut(20000)
        //关闭缓存机制
        mLocationOption!!.setLocationCacheEnable(false)
        //给定位客户端对象设置定位参数
        mLocationClient!!.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient!!.startLocation()

    }

    internal inner class MyAMapLocationListener : AMapLocationListener {

        override fun onLocationChanged(amapLocation: AMapLocation?) {
            if (amapLocation != null) {
                if (amapLocation.errorCode == 0) {
                    //可在其中解析amapLocation获取相应内容。
                 //  amapLocation = amapLocation
                    amapLocation.locationType//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    Log.e("AmapError", amapLocation.latitude.toString() + "====" + amapLocation.longitude)
                    //  Toast.makeText(BaseApp.this,amapLocation.getLatitude()+"",Toast.LENGTH_SHORT).show();
                    amapLocation.latitude//获取纬度
                    amapLocation.longitude//获取经度
                    amapLocation.accuracy//获取精度信息
                    amapLocation.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.country//国家信息
                    amapLocation.province//省信息
                    amapLocation.city//城市信息
                    amapLocation.district//城区信息
                    amapLocation.street//街道信息
                    amapLocation.streetNum//街道门牌号信息
                    amapLocation.cityCode//城市编码
                    amapLocation.adCode//地区编码
                    amapLocation.aoiName//获取当前定位点的AOI信息
                    amapLocation.buildingId//获取当前室内定位的建筑物Id
                    amapLocation.floor//获取当前室内定位的楼层
                    //    amapLocation.getGpsStatus();//获取GPS的当前状态
                    //获取定位时间
                    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val date = Date(amapLocation.time)
                    df.format(date)

                    //记录坐标
                    saveLocation(amapLocation)

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.errorCode + ", errInfo:"
                            + amapLocation.errorInfo)

                }
            }
        }
    }
    private var compositeDisposable = CompositeDisposable()
    private val locationModel  by lazy {
        LocationModel()
    }
    private val gson by lazy {
        Gson()
    }
    var locationList : MutableList<LocationBean>? = null
    //此提交时间
    var lastLocationTime :Long =0
    //上次提交 经度坐标
    var lastLocationLongitude :Long =0
    //上次提交 纬度坐标
    var lastLocationLatitude :Long =0
    //提交间隔时间
    var pushTime :Long =1000*60
    var userId:String by Preference("userId","")
    private fun saveLocation(amapLocation: AMapLocation) {
//        lastLocation //上次提交的//
//        lastLocationTime//上次提交时间 //5min
//        与上次提交 距离差距布较//-
//                与上一次提交的时间比较
        if (locationList==null){
            locationList = ArrayList<LocationBean>()
        }
        amapLocation.latitude//获取纬度
        amapLocation.longitude//获取经度
        var location = LocationBean(amapLocation.time.toString(),amapLocation.longitude.toString(),  amapLocation.latitude.toString())
        locationList!!.add(location)


        if (amapLocation.time - lastLocationTime > pushTime){
            //达到提交时间

            //提交
            val disposable = locationModel.addLocation(userId,gson.toJson(locationList))
                    .subscribe({
                        aa ->
                        //提交成功
                    },{
                        throwable ->
                        //提交失败
                    })
            compositeDisposable.add(disposable)
            //重新设置提交时间
            lastLocationTime = amapLocation.time
            //清空
            locationList!!.clear()

        }
    }


}
