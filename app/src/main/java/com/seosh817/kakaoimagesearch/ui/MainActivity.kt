package com.seosh817.kakaoimagesearch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.seosh817.kakaoimagesearch.core.designsystem.theme.KakaoImageSearchTheme
import com.seosh817.kakaoimagesearch.navigation.KakaoImageSearchNavigator
import com.seosh817.kakaoimagesearch.navigation.rememberKakaoImageSearchNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition(condition = { false })

        setContent {
            val kakaoImageSearchNavigator: KakaoImageSearchNavigator = rememberKakaoImageSearchNavigator()
            val snackbarHostState = remember { SnackbarHostState() }
            val bottomAppBarState = rememberBottomAppBarState()

            KakaoImageSearchTheme {
                MainScreen(
                    kakaoImageSearchNavigator = kakaoImageSearchNavigator,
                    snackbarHostState = snackbarHostState,
                    bottomAppBarState = bottomAppBarState
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    KakaoImageSearchTheme {
        MainScreen(
            kakaoImageSearchNavigator = rememberKakaoImageSearchNavigator(),
            snackbarHostState = remember { SnackbarHostState() },
            bottomAppBarState = rememberBottomAppBarState()
        )
    }
}