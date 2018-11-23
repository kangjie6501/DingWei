package com.dingwei.dingwei.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import com.amap.api.maps2d.model.PolylineOptions
import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.LocationContract
import com.dingwei.dingwei.mvp.model.bean.LocationPageBean
import com.dingwei.dingwei.mvp.presenter.LocationPresenter
import com.dingwei.dingwei.utils.Preference
import kotlinx.android.synthetic.main.activity_location_layout.*



/**
 * Created by kangjie on 2018/11/20.
 */
class LocationActivity :BaseActivity(), LocationContract.View {
    var userId by Preference("userId","")
    var aMap: AMap? = null
    val locationPresenter by lazy {
        LocationPresenter()
    }

    override fun showError(errorMsg: Throwable) {
        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun showLocation(result: List<LocationPageBean>) {
         aMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(result.get(0).fields.wei.toDouble(),result.get(0).fields.jing.toDouble()),
         19f))
         val markerOptions = MarkerOptions()
        Log.e("TAG",""+result.get(0).fields.jing.toDouble()+"  "+result.get(0).fields.wei)
         markerOptions.position(LatLng(result.get(0).fields.wei.toDouble(),result.get(0).fields.jing.toDouble()))
         aMap!!.addMarker(markerOptions)

        var mPolylineOptions = PolylineOptions()
        mPolylineOptions.setDottedLine(true)//设置是否为虚线
        mPolylineOptions.geodesic(false)//是否为大地曲线
        mPolylineOptions.visible(true)//线段是否可见
   //     mPolylineOptions.useGradient(false)//设置线段是否使用渐变色
        //设置线颜色，宽度
        mPolylineOptions.color(Color.parseColor("#003245")).width(10f)

        //起点位置和  地图界面大小控制
   //     aMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(list.get(0), 7f))
 //       aMap.setMapTextZIndex(2)
        var locationList = ArrayList<LatLng>()
        for (item: LocationPageBean in result) {
            var l = LatLng(result.get(0).fields.wei.toDouble(),result.get(0).fields.jing.toDouble())
            locationList.add(l)
        }
        aMap!!.addPolyline( mPolylineOptions
                //手动数据测试
                //.add(new LatLng(26.57, 106.71),new LatLng(26.14,105.55),new LatLng(26.58, 104.82), new LatLng(30.67, 104.06))
                //集合数据
                .addAll(locationList)
                //线的宽度
                .width(10f).setDottedLine(true).geodesic(true)
                //颜色
                .color(Color.argb(255, 255, 20, 147)))

    }

    override fun layoutId(): Int {
        return R.layout.activity_location_layout
    }

    override fun initData() {

    }

    init {
        locationPresenter.attachView(this)
    }

    override fun initMapView(savedInstanceState: Bundle?) {
        super.initMapView(savedInstanceState)
        //自己当前位置
        location_map.onCreate(savedInstanceState)

        if (aMap == null) {
            aMap = location_map.getMap()
        }
        //当前位置
       /* aMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(Double.parseDouble(mMomentBean.getLatitude()), Double.parseDouble(mMomentBean
                .getLongitude())), 19f))*/
       /* val markerOptions = MarkerOptions()
        markerOptions.position(LatLng(Double.parseDouble(mMomentBean.getLatitude()), Double.parseDouble(mMomentBean.getLongitude())))
        aMap.addMarker(markerOptions)*/

    }
    override fun initView() {

    }

    override fun start() {
        locationPresenter.getLocationByUserId(userId)
    }

    override fun onDestroy() {
        super.onDestroy()
        location_map.onDestroy()
        locationPresenter.detachView()
    }


    override fun onPause() {
        super.onPause()
        location_map.onPause()
    }

    override fun onResume() {
        super.onResume()
        location_map.onResume()
    }



}