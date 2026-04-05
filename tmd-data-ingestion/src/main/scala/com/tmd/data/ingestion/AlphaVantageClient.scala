package com.tmd.data.ingestion

import com.tmd.data.config.AppConfig
import sttp.client3.*
import sttp.client3.circe.*
import io.circe.Json

object AlphaVantageClient {

  private val backend = HttpURLConnectionBackend()

  def fetchWeeklyData(symbol: String): Either[String, Json] = {
    val request = basicRequest
      .get(uri"${AppConfig.baseUrl}?function=TIME_SERIES_WEEKLY&symbol=$symbol&apikey=${AppConfig.apiKey}&datatype=json")
      .response(asJson[Json])

    val response = request.send(backend)

    response.body match {
      case Right(json) => Right(json)
      case Left(error) => Left(error.toString)
    }
  }
}