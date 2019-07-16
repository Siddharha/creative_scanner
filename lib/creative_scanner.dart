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

  static Future<bool> startScan(Map<String,String> toolbarMap,{@required bool hasToolbar}) async {
      await _channel.invokeMethod('startScan',{"toolbar_color":toolbarMap['toolbar_color'],
        "toolbar_title":toolbarMap['toolbar_title'],"has_toolbar":hasToolbar});

    return true;
  }
}
