package com.tmd.data.model

case class StockRecord(
                        symbol: String,
                        timestamp: String,
                        open: Double,
                        high: Double,
                        low: Double,
                        close: Double,
                        volume: Long
                      )