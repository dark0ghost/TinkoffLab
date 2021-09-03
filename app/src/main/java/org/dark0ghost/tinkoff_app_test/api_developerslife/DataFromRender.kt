package org.dark0ghost.tinkoff_app_test.api_developerslife

import kotlinx.serialization.Serializable

typealias Url = String

@Serializable
data class DataForRender(val description: String, val gifURL: Url)

@Serializable
data class ListDataForRender(val result: List<DataForRender>)
