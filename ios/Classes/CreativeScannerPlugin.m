#import "CreativeScannerPlugin.h"
#import <creative_scanner/creative_scanner-Swift.h>

@implementation CreativeScannerPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftCreativeScannerPlugin registerWithRegistrar:registrar];
}
@end
