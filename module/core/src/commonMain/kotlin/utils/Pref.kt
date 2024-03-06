package utils

import com.liftric.kvault.KVault

expect class Pref(platformConfiguration: PlatformConfiguration) {
     val store: KVault
}