package utils

import com.liftric.kvault.KVault

actual class Pref actual constructor(private val platformConfiguration: PlatformConfiguration) {
     actual val store = KVault()
}