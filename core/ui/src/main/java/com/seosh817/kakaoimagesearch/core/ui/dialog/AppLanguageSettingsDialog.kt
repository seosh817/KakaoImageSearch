package com.seosh817.kakaoimagesearch.core.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.seosh817.kakaoimagesearch.core.designsystem.component.dialog.BaseDialog
import com.seosh817.kakaoimagesearch.core.ui.R
import com.seosh817.kakaoimagesearch.core.ui.RadioButtonRow
import com.seosh817.kakaoimagesearch.domain.entity.AppLanguage

@Composable
fun AppLanguageSettingsDialog(
    appLanguage: AppLanguage,
    onLanguageClick: (AppLanguage) -> Unit,
    onDismiss: () -> Unit,
) {
    BaseDialog(
        title = stringResource(id = R.string.language),
        icon = {
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = null,
            )
        },
        content = {
            Column {
                AppLanguage
                    .entries
                    .forEach { language ->
                        RadioButtonRow(
                            text = language.name,
                            selected = appLanguage == language,
                            onClick = { onLanguageClick(language) },
                        )
                    }
            }
        },
        onDismiss = onDismiss,
    )
}
