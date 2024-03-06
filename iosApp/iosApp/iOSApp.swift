import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

     init() {
         PlatformSDK().doInit(configuration: PlatformConfiguration())
     }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
