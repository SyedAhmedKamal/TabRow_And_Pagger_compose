package com.example.composetabrow

/**
 * AUTHOR Syed Ahmed Kamal, CREATED ON (10/20/2024).
 */

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabBar() {
    val pagerState = rememberPagerState()
    val pages = listOf("kotlin","java","c#","php","golang","rust")
    val defaultIndicator = @Composable { tabPositions: List<TabPosition> ->
        TabRowDefaults.Indicator(
            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        )
    }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    ScrollableTabRow(
        modifier = Modifier.height(50.dp).clip(RoundedCornerShape(5.dp)),
        selectedTabIndex = pagerState.currentPage,
        indicator = indicator
    ) {
        pages.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier.zIndex(6f),
                text = { Text(text = title) },
                selected = pagerState.currentPage == index,
                onClick = { /* TODO */ },
            )
            // For default tab background
            // you can do something similar to that
            // Sets a background to all tabs except the selected one
//            Tab(
//                modifier = Modifier.zIndex(6f),
//                text = {
//                    if (pagerState.currentPage != index) {
//                        Box(modifier = Modifier.background(Color.Red).padding(10.dp)) {
//                            Text(text = title)
//                        }
//                    } else {
//                        Text(text = title)
//                    }
//                },
//                selected = pagerState.currentPage == index,
//                onClick = { /* TODO */ },
//            )
        }
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = pages.size,
        state = pagerState,
    ) { page ->
        Box(Modifier.fillMaxSize()) {
            Text(modifier = Modifier.align(Alignment.Center), text = "Page $page")
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: com.google.accompanist.pager.PagerState) {
    val transition = updateTransition(pagerState.currentPage)
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = Color(0xFFFF7455), RoundedCornerShape(50))
            .border(BorderStroke(2.dp, Color(0xFFC13D25)), RoundedCornerShape(50))
            .zIndex(1f)
    )
}

@Preview(showBackground = true)
@Composable
fun MyPreView() {
 TabBar()
}