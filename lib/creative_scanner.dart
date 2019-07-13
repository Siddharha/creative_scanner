import 'dart:async';

import 'package:flutter/services.dart';

class CreativeScanner {
  static const MethodChannel _channel =
      const MethodChannel('creative_scanner');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> get startScan async {
    await _channel.invokeMethod('startScan');
    return true;
  }
}
