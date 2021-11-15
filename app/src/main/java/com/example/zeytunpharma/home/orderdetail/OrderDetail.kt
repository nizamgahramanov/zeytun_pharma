package com.example.zeytunpharma.home.orderdetail

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zeytunpharma.R
import com.example.zeytunpharma.components.InsetAwareTopAppBar
import com.example.zeytunpharma.util.isScrolled
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun OrderDetail(
    medicineViewModel: MedicineViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onSubmitPressed: () -> Unit,
) {

    val viewState by medicineViewModel.state.collectAsState()
    val order = viewState.selectedOrder
    Log.e("orderMedicineCategor", viewState.orderMedicineCategory.size.toString())
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = {
            InsetAwareTopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                            .padding(start = 30.dp)
                    ) {
                        if (viewState.selectedOrder != null) {
                            Text(
                                text = (order!!.orderNumber),
                                style = MaterialTheme.typography.body1,
                                color = LocalContentColor.current,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .weight(2f)
                            )
                            if (order.is_completed) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = stringResource(R.string.cd_unsubmitted),
                                    tint = animateColorAsState(
                                        LocalContentColor.current
                                    ).value,
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .shadow(
                                            elevation = animateDpAsState(if (order.is_completed) 0.dp else 1.dp).value,
                                            shape = MaterialTheme.shapes.small
                                        )
                                        .background(
                                            color = animateColorAsState(
                                                MaterialTheme.colors.surface.copy(0.38f)
                                            ).value,
                                            shape = MaterialTheme.shapes.small
                                        )
                                        .padding(0.dp)
                                )
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_up),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                elevation = if (!scrollState.isScrolled) 0.dp else 4.dp,
                backgroundColor = MaterialTheme.colors.surface
            )
        },
        content = { innerPadding ->
            MedicineContent(
                state = scrollState,
                orderMedicineCategory = viewState.orderMedicineCategory,
                modifier = Modifier
                    .padding(innerPadding)
                    .navigationBarsPadding(bottom = false)
                    .fillMaxWidth()
            )
        },
        bottomBar = {
            BottomBar(
                onSubmitPressed = medicineViewModel::orderCompleted
            )
        }
    )
}

@Composable
private fun BottomBar(onSubmitPressed: () -> Unit) {
    Surface(
        elevation = 7.dp,
        modifier = Modifier.fillMaxWidth() // .border(1.dp, MaterialTheme.colors.primary)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 30.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                onClick = onSubmitPressed,
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}