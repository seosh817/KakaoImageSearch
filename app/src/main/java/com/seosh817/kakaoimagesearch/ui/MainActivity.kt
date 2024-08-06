package com.seosh817.kakaoimagesearch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.seosh817.kakaoimagesearch.core.designsystem.theme.KakaoImageSearchTheme
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews
import com.seosh817.kakaoimagesearch.core.ui.dialog.AppLanguageSettingsDialog
import com.seosh817.kakaoimagesearch.core.ui.dialog.AppThemeSettingsDialog
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode
import com.seosh817.kakaoimagesearch.domain.entity.OpenDialog
import com.seosh817.kakaoimagesearch.navigation.KakaoImageSearchNavigator
import com.seosh817.kakaoimagesearch.navigation.rememberKakaoImageSearchNavigator
import com.seosh817.kakaoimagesearch.util.LocalizationUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState by mutableStateOf<MainUiState>(MainUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }


        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                is MainUiState.Loading -> true
                is MainUiState.Success -> false
            }
        }

        setContent {
            val kakaoImageSearchNavigator: KakaoImageSearchNavigator = rememberKakaoImageSearchNavigator()
            val bottomAppBarState = rememberBottomAppBarState()
            var openDialog by rememberSaveable {
                mutableStateOf(OpenDialog.NONE)
            }

            val isDarkTheme = shouldUseDarkTheme(uiState)

            if (openDialog != OpenDialog.NONE) {
                when (openDialog) {
                    OpenDialog.APP_THEME_SETTINGS -> {
                        if (uiState is MainUiState.Success) {
                            AppThemeSettingsDialog(
                                appThemeMode = (uiState as MainUiState.Success).appSettings.darkThemeMode,
                                onThemeClick = viewModel::updateDarkThemeMode,
                                onDismiss = {
                                    openDialog = OpenDialog.NONE
                                },
                            )
                        }
                    }

                    OpenDialog.APP_LANGUAGE_SETTINGS -> {
                        if (uiState is MainUiState.Success) {
                            AppLanguageSettingsDialog(
                                appLanguage = (uiState as MainUiState.Success).appSettings.appLanguage,
                                onLanguageClick = { appLanguage ->
                                    LocalizationUtil.setLanguage(this@MainActivity, appLanguage)
                                    viewModel.updateAppLanguage(appLanguage)
                                },
                                onDismiss = {
                                    openDialog = OpenDialog.NONE
                                },
                            )
                        }
                    }

                    else -> {}
                }
            }

            KakaoImageSearchTheme(
                isDarkTheme,
            ) {
                MainScreen(
                    kakaoImageSearchNavigator = kakaoImageSearchNavigator,
                    bottomAppBarState = bottomAppBarState,
                    openDialog = {
                        openDialog = it
                    },
                )
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(
    uiState: MainUiState,
): Boolean = when (uiState) {
    MainUiState.Loading -> isSystemInDarkTheme()
    is MainUiState.Success -> when (uiState.appSettings.darkThemeMode) {
        DarkThemeMode.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeMode.LIGHT -> false
        DarkThemeMode.DARK -> true
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    KakaoImageSearchTheme {
        var openDialog by rememberSaveable {
            mutableStateOf(OpenDialog.NONE)
        }
        MainScreen(
            kakaoImageSearchNavigator = rememberKakaoImageSearchNavigator(),
            bottomAppBarState = rememberBottomAppBarState(),
            openDialog = {
                openDialog = it
            },
        )
    }
}