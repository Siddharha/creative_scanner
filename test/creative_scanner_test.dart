import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:creative_scanner/creative_scanner.dart';

void main() {
  const MethodChannel channel = MethodChannel('creative_scanner');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await CreativeScanner.platformVersion, '42');
  });
}
