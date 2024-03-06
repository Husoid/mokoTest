
import di.Inject
import di.coreModule
import di.loginModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton
import utils.PlatformConfiguration

object PlatformSDK {

    fun init(
        configuration: PlatformConfiguration
    ) {
        val platformModule = DI.Module(
            name = "platformModule",
            init = {
                bind<PlatformConfiguration>() with singleton { configuration }
            }
        )

        Inject.createDependencies(
            DI {
                importAll(
                    platformModule,
                    coreModule,
                    loginModule
                )
            }.direct
        )
    }
}