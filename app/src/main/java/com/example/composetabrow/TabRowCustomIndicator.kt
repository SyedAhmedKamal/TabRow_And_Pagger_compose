package com.example.composetabrow

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * AUTHOR Syed Ahmed Kamal, CREATED ON (10/19/2024).
 */

//@Composable
//fun BasicTab() {
//    var tabIndex by remember { mutableStateOf(0) }
//
//    val tabs = listOf("Profile", "Settings")
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        androidx.compose.material3.TabRow(
//            selectedTabIndex = tabIndex,
//            modifier = Modifier
//                .padding(5.dp)
//                .background(Color.Transparent)
//                .clip(RoundedCornerShape(30.dp)),
//            containerColor = Color.Green,
//            indicator = { tabPositions ->
//                TabRowDefaults.Indicator(
//                    modifier = Modifier
//                        .pagerTabIndicatorOffset(
//                            pagerState, tabPositions
//                        )
//                        .width(0.dp)
//                        .height(0.dp)
//                )
//            }
//        ) {
//            tabs.forEachIndexed { index, title ->
//                Tab(text = { Text(text = title) },
//                    selected = tabIndex == index,
//                    onClick = { tabIndex = index }
//                )
//            }
//        }
//    }
//}

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch



@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun TabScreen() {
    val tabs = listOf("Home", "About", "Settings")
    val pagerState = rememberPagerState(initialPage = 0) {
        tabs.size
    }
    val scope = rememberCoroutineScope()
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        // my custom indicator
//        Box(
//            Modifier
//                // you need implementation("com.google.accompanist:accompanist-pager-indicators:0.32.0") in build gradle
//                .pagerTabIndicatorOffset(
//                    pagerState,
//                    tabPositions
//                )
//                .fillMaxHeight()
//                .clip(RoundedCornerShape(54.dp))
//                .background(color = MaterialTheme.colorScheme.primary)
//        )
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.zIndex(1f),
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    TabScreen()
}
