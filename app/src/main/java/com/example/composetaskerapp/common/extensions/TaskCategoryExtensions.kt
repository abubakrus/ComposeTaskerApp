package com.example.composetaskerapp.common.extensions

import com.example.composetaskerapp.data.models.TaskCategoryCache
import com.example.composetaskerapp.domain.models.TaskCategory

fun TaskCategory.toCache(): TaskCategoryCache {
    return TaskCategoryCache(
        id = id,
        title = title,
        colorCode = colorCode
    )
}

fun TaskCategoryCache.toCategory(): TaskCategory {
    return TaskCategory(
        id = id,
        title = title,
        colorCode = colorCode
    )
}