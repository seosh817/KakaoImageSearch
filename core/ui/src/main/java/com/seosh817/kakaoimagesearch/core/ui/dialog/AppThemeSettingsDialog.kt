package com.seosh817.kakaoimagesearch.core.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.seosh817.kakaoimagesearch.core.designsystem.component.dialog.BaseDialog
import com.seosh817.kakaoimagesearch.core.ui.R
import com.seosh817.kakaoimagesearch.core.ui.RadioButtonRow
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode

@Composable
fun AppThemeSettingsDialog(
    appThemeMode: DarkThemeMode,
    onThemeClick: (DarkThemeMode) -> Unit,
    onDismiss: () -> Unit,
) {
    BaseDialog(
        title = stringResource(id = R.string.app_theme),
        icon = {
            Icon(
                imageVector = Icons.Default.BrightnessMedium,
                contentDescription = null,
            )
        },
        content = {
            Column {
                DarkThemeMode
                    .entries
                    .forEach { darkThemeMode ->
                        RadioButtonRow(
                            text = darkThemeMode.name,
                            selected = appThemeMode == darkThemeMode,
                            onClick = { onThemeClick(darkThemeMode) },
                        )
                    }
            }
        },
        onDismiss = onDismiss,
    )
}
