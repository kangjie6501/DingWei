package com.dingwei.dingwei.rx.scheduler

/**
 * Created by kangjie on 2018/10/30.
 */
object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}