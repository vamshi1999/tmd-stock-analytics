package com.tmd.data

import com.tmd.data.config.AppConfig
import com.tmd.data.ingestion.AlphaVantageClient
import com.tmd.data.utils.{FileWriter, JsonParser}

object Main {

  def main(args: Array[String]): Unit = {

    val symbol = AppConfig.defaultSymbol

    println(s"Fetching data for $symbol")

    AlphaVantageClient.fetchWeeklyData(symbol) match {

      case Right(json) =>
        val records = JsonParser.parseWeekly(json, symbol)

        println(s"Fetched ${records.size} weekly records")

        FileWriter.write(records, AppConfig.outputDir)

      case Left(error) =>
        println(s"Error: $error")
    }
  }
}