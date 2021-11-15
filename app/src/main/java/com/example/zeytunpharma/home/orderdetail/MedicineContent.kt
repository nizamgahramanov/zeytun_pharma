package com.example.zeytunpharma.home.orderdetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.zeytunpharma.data.OrderToMedicine
import com.example.zeytunpharma.ui.theme.Keyline1

private val defaultSpacerSize = 16.dp

@Composable
fun MedicineContent(
    state: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
    orderMedicineCategory: List<OrderToMedicine>,
) {
    Column(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {

            if (orderMedicineCategory.isNotEmpty()) {
                Log.e("MEDICES ", orderMedicineCategory.toString())
                Medicines(
                    orderMedicineCategory = orderMedicineCategory,
                    modifier = Modifier
                        .padding(start = Keyline1, top = 16.dp, end = Keyline1)
                        .fillMaxWidth()
                        .height(200.dp),
                )
            }
        }
    }
}

@Composable
private fun Medicines(
    orderMedicineCategory: List<OrderToMedicine>,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (MaterialTheme.colors.isLight) {
        MaterialTheme.colors.onSurface.copy(alpha = 0.04f)
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
    }
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(orderMedicineCategory, key = { it.id }) { item ->
            MedicineItem(
                orderMedicineCategory = item,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(4.dp)
                    .background(
                        color = backgroundColor,
                        shape = MaterialTheme.shapes.small
                    ),
            )
        }
    }
}

@Composable
private fun MedicineItem(
    orderMedicineCategory: OrderToMedicine,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = Modifier.clickable { false } then modifier.padding(vertical = 4.dp,
            horizontal = 4.dp)
    ) {
        val (
            divider, episodeTitle, podcastTitle, image, playIcon,
            date, addPlaylist, overflow,
        ) = createRefs()

        Text(
            text = orderMedicineCategory.cat_name,
            maxLines = 1,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(divider) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 8.dp,
                    endMargin = 8.dp,
                    bias = 0f
                )
                top.linkTo(parent.top, 4.dp)
            }
        )

        Text(
            text = orderMedicineCategory.medicine_name,
            maxLines = 2,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(episodeTitle) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 8.dp,
                    endMargin = 8.dp,
                    bias = 0f
                )
                top.linkTo(divider.bottom, 10.dp)

                width = Dimension.preferredWrapContent
            }
        )
        val titleImageBarrier = createBottomBarrier(podcastTitle, image)

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = orderMedicineCategory.code,
                maxLines = 2,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.constrainAs(podcastTitle) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        startMargin = 8.dp,
                        endMargin = 8.dp,
                        bias = 0f
                    )
                    top.linkTo(episodeTitle.bottom, 6.dp)

                    width = Dimension.preferredWrapContent
                }
            )
            Text(
                text = orderMedicineCategory.medicine_count.toString(),
                maxLines = 2,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(image) {
                    linkTo(
                        start = podcastTitle.end,
                        end = parent.end,
                        startMargin = Keyline1,
                        endMargin = 8.dp,
                        bias = 0f
                    )
                    top.linkTo(episodeTitle.bottom, 6.dp)

                    width = Dimension.preferredWrapContent
                }
            )
            Text(
                text = orderMedicineCategory.place,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.constrainAs(playIcon) {
                    linkTo(
                        start = parent.start,
                        end = image.start,
                        startMargin = 8.dp,
                        endMargin = 8.dp,
                        bias = 0f
                    )
                    top.linkTo(titleImageBarrier, margin = 10.dp)

                }
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = orderMedicineCategory.feature,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.constrainAs(date) {
                        centerVerticallyTo(playIcon)
                        linkTo(
                            start = playIcon.end,
                            end = addPlaylist.start,
                            startMargin = Keyline1,
                            endMargin = 16.dp,
                            bias = 0f
                        )
                    }
                )
                Text(
                    text = orderMedicineCategory.country,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.constrainAs(addPlaylist) {
                        centerVerticallyTo(playIcon)
                        linkTo(
                            start = date.end,
                            end = overflow.start,
                            startMargin = Keyline1,
                            endMargin = 16.dp,
                            bias = 0f
                        )
                    }
                )
            }
        }
    }
}