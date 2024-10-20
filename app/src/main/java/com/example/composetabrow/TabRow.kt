package com.example.composetabrow

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * AUTHOR Syed Ahmed Kamal, CREATED ON (10/19/2024).
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun TabRow(
    modifier: Modifier,
    tabItems: List<TabItem>,
    titleState: String,
    titleStateBrowse: String,
    onEventHome: () -> Unit,
    onEventBrowse: () -> Unit
) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        MyCustomIndicator(tabPositions, pagerState)
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        androidx.compose.material.TabRow(
            modifier = Modifier.height(50.dp),
            selectedTabIndex = selectedTabIndex,
            indicator = indicator
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.zIndex(6f),
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = item.title)
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = tabItems[index].title)
//            }

            when(index){
                0 -> {
                    Home(titleState = titleState, modifier = modifier, ){
                        onEventHome()
                    }
                }
                1 -> {
                    Browse(titleState = titleStateBrowse, modifier = modifier){
                        onEventBrowse()
                    }
                }
                2 -> {
                    Account(titleState = titleState, modifier = modifier){
//                        onEvent()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MyCustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        /*transitionSpec = {
            if (initialState < targetState) {
                tween(durationMillis = 100, easing = LinearOutSlowInEasing)
            } else {
                tween(durationMillis = 200, easing = FastOutLinearInEasing)
            }
        },*/ label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        /*transitionSpec = {
            if (initialState < targetState) {
                tween(durationMillis = 200, easing = FastOutLinearInEasing)
            } else {
                tween(durationMillis = 100, easing = LinearOutSlowInEasing)
            }
        },*/ label = ""
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
            .background(color = Color(0xFFECEDF1), RoundedCornerShape(50))
            .border(BorderStroke(2.dp, Color(0xFF00BCD4)), RoundedCornerShape(50))
            .zIndex(1f)
    )
}