package com.tmd.data.utils

import com.tmd.data.model.StockRecord
import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets
import java.time.Instant

object FileWriter {

  def write(records: List[StockRecord], basePath: String): Unit = {

    val timestamp = Instant.now().toEpochMilli
    val filePath = s"$basePath/stock_$timestamp.json"

    val jsonLines = records.map { r =>
      s"""{"symbol":"${r.symbol}","timestamp":"${r.timestamp}","open":${r.open},"high":${r.high},"low":${r.low},"close":${r.close},"volume":${r.volume}}"""
    }

    Files.write(
      Paths.get(filePath),
      jsonLines.mkString("\n").getBytes(StandardCharsets.UTF_8)
    )
  }
}