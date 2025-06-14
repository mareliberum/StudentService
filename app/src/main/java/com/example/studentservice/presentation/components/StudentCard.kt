package com.example.studentservice.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentservice.presentation.studentListScreen.StudentModel

@Composable
fun StudentCard(
	student: StudentModel,
	onAddToken: () -> Unit,
	onRemoveToken: () -> Unit,
	onExpel: () -> Unit,
	isTeacher: Boolean,
) {
	var expanded by remember { mutableStateOf(false) }

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 8.dp, vertical = 8.dp)
	) {
		Box(modifier = Modifier.fillMaxWidth()) {
			Column(
				modifier = Modifier
					.padding(end = if (isTeacher) 40.dp else 0.dp)
					.fillMaxWidth()
			) {
				Text(
					text = student.name,
					style = MaterialTheme.typography.titleMedium,
					color = MaterialTheme.colorScheme.onSurface
				)
				Spacer(modifier = Modifier.height(4.dp))
				Row(
					horizontalArrangement = Arrangement.spacedBy(16.dp),
					modifier = Modifier.fillMaxWidth(),
				) {
					Text(
						text = "Жетоны: ${student.tokens}",
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
					Text(
						text = "Группа: ${student.group}",
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
				}
			}

			if (isTeacher) {
				Box(
					modifier = Modifier
						.align(Alignment.TopEnd)
				) {
					IconButton(onClick = { expanded = true }) {
						Icon(
							imageVector = Icons.Default.MoreVert,
							contentDescription = "More Options",
							tint = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}

					DropdownMenu(
						expanded = expanded,
						onDismissRequest = { expanded = false }
					) {
						DropdownMenuItem(
							text = { Text("Добавить жетон") },
							onClick = {
								onAddToken()
							}
						)
						DropdownMenuItem(
							text = { Text("Убрать жетон") },
							onClick = {
								onRemoveToken()
							}
						)
						DropdownMenuItem(
							text = {
								Text(
									text = "Отчислить",
									color = Color.Red
								)
							},
							onClick = {
								onExpel()
							}
						)
					}
				}
			}
		}

		Spacer(modifier = Modifier.height(12.dp))
		HorizontalDivider(
			thickness = 1.dp,
			color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
		)
	}
}
