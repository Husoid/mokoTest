package di

import di.ktor.ktorModule
import di.json.serializationModule
import org.kodein.di.DI

val coreModule = DI.Module("coreModule") {

    importAll(
        ktorModule,
        serializationModule
    )
}