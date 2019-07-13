package creativelizard.creative_scanner

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class CreativeScannerPlugin(val activity:Activity,channel: MethodChannel): MethodCallHandler {
  init {
    channel.setMethodCallHandler(this)
  }
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "creative_scanner")
      channel.setMethodCallHandler(CreativeScannerPlugin(registrar.activity(),channel))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    }else if (call.method == "startScan") {
      startScanView()
    } else {
      result.notImplemented()
    }
  }

  private fun startScanView() {
    //val intent = Intent(activity, BarcodeScannerActivity::class.java)
    //activity.startActivityForResult(intent, 100)
    activity.startActivity(Intent(activity,ContinuousCaptureActivity::class.java))
   // Toast.makeText(context,"hi it's working!",Toast.LENGTH_SHORT).show()
  }
}
