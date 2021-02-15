package com.coroutineslogapp.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.coroutineslogapp.ui.util.NetworkUtil

class ConnectivityReceiver(
    private val connectivityReceiverListener: ConnectivityReceiverListener,
    private val network: NetworkUtil
) : BroadcastReceiver() {
    private var connectivityPreviousStatus = true

    override fun onReceive(context: Context?, intent: Intent?) {
        val currentStatus = network.isConnectedOrConnecting()
        if (connectivityPreviousStatus != currentStatus) {
            connectivityReceiverListener.onNetworkConnectionChanged(currentStatus)
            connectivityPreviousStatus = currentStatus
        }
    }
}
