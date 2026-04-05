package com.tmd.data.utils

import com.tmd.data.model.StockRecord
import io.circe.Json

object JsonParser {

  def parseWeekly(json: Json, symbol: String): List[StockRecord] = {

    val cursor = json.hcursor
    val timeSeriesKey = "Weekly Time Series"

    cursor.downField(timeSeriesKey).focus match {

      case Some(data) =>
        data.asObject.toList.flatMap(_.toMap.map {
          case (date, value) =>
            val v = value.hcursor

            StockRecord(
              symbol = symbol,
              timestamp = date, // weekly date
              open = v.get[String]("1. open").getOrElse("0").toDouble,
              high = v.get[String]("2. high").getOrElse("0").toDouble,
              low = v.get[String]("3. low").getOrElse("0").toDouble,
              close = v.get[String]("4. close").getOrElse("0").toDouble,
              volume = v.get[String]("5. volume").getOrElse("0").toLong
            )
        }).toList

      case None => List.empty
    }
  }
}