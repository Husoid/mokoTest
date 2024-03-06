package compose.ui.splash.viewModel.models

sealed class SplashAction {
    object OpenLoginScreen: SplashAction()
    object OpenRegisterScreen: SplashAction()
    object OpenDemoScreen: SplashAction()
}