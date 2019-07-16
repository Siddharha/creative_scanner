import 'dart:async';

import 'package:flutter/services.dart';
import 'package:meta/meta.dart';

class CreativeScanner {
  static const MethodChannel _channel =
      const MethodChannel('creative_scanner');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> startScan({@required String toolbarColor, @required String toolbarText}) async {
    await _channel.invokeMethod('startScan',{"toolbar_color":toolbarColor, "toolbar_text":toolbarText});
    return true;
  }
}
