package com.example.zeytunpharma.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.zeytunpharma.R
import com.example.zeytunpharma.data.Client
import com.example.zeytunpharma.data.HomeTabs
import com.example.zeytunpharma.data.Order
import com.example.zeytunpharma.data.OrderToClient
import com.example.zeytunpharma.ui.theme.Keyline1
import com.example.zeytunpharma.util.verticalGradientScrim

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchResults(
    orderToClient: List<OrderToClient>,
    modifier: Modifier = Modifier,
    selectedHomeTab: HomeTabs,
    homeTabs: List<HomeTabs>,
    onHomeTabSelected: (HomeTabs) -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    Column(modifier = modifier) {

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalGradientScrim(
                color = MaterialTheme.colors.primary.copy(alpha = 0.38f),
                startYPercentage = 1f,
                endYPercentage = 0f
            )) {
            val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)
//            Spacer(
//                Modifier
//                    .background(appBarColor)
//                    .fillMaxWidth()
//                    .statusBarsHeight()
//            )
//            HomeAppBar(
//                backgroundColor = appBarColor,
//                modifier = Modifier.fillMaxWidth(),
//            )

            if (homeTabs.isNotEmpty()) {
                HomePageTabs(
                    tabs = homeTabs,
                    selectedHomeTab = selectedHomeTab,
                    onHomeTabSelected = onHomeTabSelected
                )
            }
            if (orderToClient.isNotEmpty()) {
                Orders(
                    orderToClient = orderToClient,
                    modifier = Modifier
                        .padding(start = Keyline1, top = 0.dp, end = Keyline1)
                        .fillMaxWidth()
                        .height(200.dp),
                    navigateToDetail = navigateToDetail
                )

                Spacer(Modifier.height(16.dp))
            }
        }

    }
}

@Composable
private fun HomePageTabs(
    tabs: List<HomeTabs>,
    selectedHomeTab: HomeTabs,
    onHomeTabSelected: (HomeTabs) -> Unit,
    modifier: Modifier = Modifier,
) {

    val selectedIndex = tabs.indexOfFirst { it == selectedHomeTab }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        HomeTabIndicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
        )
    }
    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = indicator,
        modifier = modifier
    ) {
        Log.e("TAB IN SEARCHRES ", selectedHomeTab.toString())
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onHomeTabSelected(tab) },
                text = {
                    Text(
                        text = when (tab) {
                            HomeTabs.Current -> stringResource(R.string.home_current)
                            HomeTabs.History -> stringResource(R.string.home_history)
                        },
                        style = MaterialTheme.typography.body2
                    )
                }
            )
        }
    }
}

@Composable
fun HomeTabIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
) {
    Spacer(
        modifier
            .padding(horizontal = 24.dp)
            .height(4.dp)
            .background(color, RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun Orders(
    orderToClient: List<OrderToClient>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(orderToClient, key = { it.order.orderNumber }) { item ->
            OrderItem(
                client = item.client,
                order = item.order,
                modifier = Modifier.fillParentMaxWidth(),
                navigateToDetail,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun OrderItem(
    client: Client,
    order: Order,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier.clickable { navigateToDetail(order.id) } then modifier
    ) {
//        val (
//            divider, orderNumber, storeName, createdDate
//        ) = createRefs()
        val (
            divider, episodeTitle, podcastTitle, image, playIcon,
            date, addPlaylist, overflow,
        ) = createRefs()
        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = order.orderNumber,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.constrainAs(episodeTitle) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = Keyline1,
                    endMargin = 16.dp,
                    bias = 0f
                )
                top.linkTo(parent.top, 10.dp)

                width = Dimension.preferredWrapContent
            }
        )
        val titleImageBarrier = createBottomBarrier(podcastTitle, image)

        Row(Modifier
            .constrainAs(podcastTitle) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = Keyline1,
                    endMargin = 16.dp,
                    bias = 0f
                )
                top.linkTo(episodeTitle.bottom, 6.dp)

                width = Dimension.preferredWrapContent
            }) {

            val dateTimeCreated = order.created_date.toLocalDateTime().toString().replace("T", " ")
            Text(
                text = dateTimeCreated,
                maxLines = 2,
                style = MaterialTheme.typography.subtitle2,
            )
            Spacer(Modifier.width(Keyline1))
            Icon(
                modifier = Modifier.size(22.dp, 22.dp),
                painter = painterResource(R.drawable.ic_location),
                tint = Color(0x80FFFFFF),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = order.store_name,
                maxLines = 2,
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Row(modifier = Modifier.constrainAs(playIcon)
        {
            linkTo(
                start = parent.start,
                end = parent.end,
                startMargin = Keyline1,
                endMargin = 8.dp,
                bias = 0f
            )
            top.linkTo(titleImageBarrier, margin = 10.dp)
        }) {
            Icon(
                modifier = Modifier.size(25.dp, 25.dp),
                painter = painterResource(R.drawable.order_prepare_assigned),
                tint = Color(0x80FFFFFF),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = order.store_res_person,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Row(modifier = Modifier.constrainAs(date)
        {
            linkTo(
                start = parent.start,
                end = parent.end,
                startMargin = Keyline1,
                endMargin = 8.dp,
                bias = 0f
            )
            top.linkTo(playIcon.bottom, margin = 10.dp)
        }) {
            Icon(
                modifier = Modifier.size(25.dp, 25.dp),
                painter = painterResource(R.drawable.delivery_car),
                tint = Color(0x80FFFFFF),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = order.delivery_res_person,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Row(modifier = Modifier.constrainAs(addPlaylist)
        {
            linkTo(
                start = parent.start,
                end = parent.end,
                startMargin = Keyline1,
                endMargin = 8.dp,
                bias = 0f
            )
            top.linkTo(date.bottom, margin = 10.dp)
            bottom.linkTo(parent.bottom, 10.dp)
        }) {
            Icon(
                modifier = Modifier.size(25.dp, 25.dp),
                painter = painterResource(R.drawable.person_pin_circle),
                tint = Color(0x80FFFFFF),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = client.title + "  (" + client.name + ")",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
            )

        }
        if (order.is_completed) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.cd_unsubmitted),
                tint = animateColorAsState(
                    Color.Black.copy(alpha = ContentAlpha.high)
                ).value,
                modifier = Modifier
                    .constrainAs(overflow) {
                        linkTo(
                            start = addPlaylist.end,
                            end = parent.end,
                            startMargin = Keyline1,
                            endMargin = 8.dp,
                            bias = 1f
                        )
                        top.linkTo(date.bottom, margin = 10.dp)
                    }
                    .shadow(
                        elevation = animateDpAsState(if (order.is_completed) 0.dp else 1.dp).value,
                        shape = MaterialTheme.shapes.small
                    )
                    .background(
                        color = animateColorAsState(
                            Color.White
                        ).value,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(4.dp)
            )
        }
    }
}