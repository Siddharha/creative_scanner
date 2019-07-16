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
      val hasToolbar = call.argument<Boolean>("has_toolbar")

      if(hasToolbar!!){
        val colorToolbar = call.argument<String>("toolbar_color")
        val titleToolbar  = call.argument<String>("toolbar_title")
        val toolbarMap = HashMap<String,Any>()
        toolbarMap.put("toolbar_color",colorToolbar!!)
        toolbarMap.put("toolbar_title",titleToolbar!!)
        toolbarMap.put("is_toolbar",true)
        startScanView(toolbarMap)
      }else{
        val toolbarMap = HashMap<String,Any>()
        toolbarMap.put("toolbar_color","#000")
        toolbarMap.put("toolbar_title","")
        toolbarMap.put("is_toolbar",false)
        startScanView(toolbarMap)
      }

    } else {
      result.notImplemented()
    }
  }

  private fun startScanView(toolbar:Map<String,Any>) {
    val toolbar_color = toolbar.get("toolbar_color") as String
    val toolbarTitle = toolbar.get("toolbar_title") as String
    val isToolbar = toolbar.get("is_toolbar") as Boolean
    val intent = Intent(activity,ContinuousCaptureActivity::class.java)
    intent.putExtra("toolbar_color",/*toolbar_color*/"#00bcd4")
    intent.putExtra("toolbar_title",toolbarTitle)
    intent.putExtra("is_toolbar",isToolbar)
    activity.startActivity(intent)
   // Toast.makeText(context,"hi it's working!",Toast.LENGTH_SHORT).show()
  }
}
