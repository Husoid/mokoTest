package compose.ui.splash.viewModel.models

sealed class SplashEvent {
    object LoginClick: SplashEvent()
    object RegistrationClick: SplashEvent()
    object DemoClick: SplashEvent()
}