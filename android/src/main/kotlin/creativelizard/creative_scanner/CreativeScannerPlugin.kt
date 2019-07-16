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
     // val toolbar_color = call.argument("toolbar_color") as String
     // val toolbar_text = call.argument("toolbar_text") as String

      val toolbarMap = java.util.HashMap<String,String>()
      toolbarMap.put("toolbar_color","#00bcd4")
      toolbarMap.put("toolbar_text","Hi")
      startScanView(toolbarMap)
    } else {
      result.notImplemented()
    }
  }

  private fun startScanView(toolbar:Map<String,String>) {
    val toolbar_color = toolbar.get("toolbar_color")
    val toolbar_title = toolbar.get("toolbar_title")
    val intent = Intent(activity,ContinuousCaptureActivity::class.java)
    intent.putExtra("toolbar_color",toolbar_color)
    intent.putExtra("toolbar_title",toolbar_title)
    activity.startActivity(intent)
   // Toast.makeText(context,"hi it's working!",Toast.LENGTH_SHORT).show()
  }
}
