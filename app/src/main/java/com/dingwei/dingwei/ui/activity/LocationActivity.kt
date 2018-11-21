package com.dingwei.dingwei.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.LocationContract
import com.dingwei.dingwei.mvp.model.bean.LocationBean
import com.dingwei.dingwei.mvp.presenter.LocationPresenter
import com.dingwei.dingwei.net.BaseResponce
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

    override fun showLocation(result: BaseResponce<List<LocationBean>>) {
         aMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(result.data.get(0).jing.toDouble(),result.data.get(0).wei.toDouble()), 19f))
         val markerOptions = MarkerOptions()
         markerOptions.position(LatLng(result.data.get(0).jing.toDouble(),result.data.get(0).wei.toDouble()))
         aMap!!.addMarker(markerOptions)
    }

    override fun layoutId(): Int {
        return R.layout.activity_location_layout
    }

    override fun initData() {

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