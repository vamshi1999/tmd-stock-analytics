from pyspark.sql.functions import col, to_timestamp

def transform(spark, input_path):

    bronze_df = spark.read.json(input_path)

    silver_df = bronze_df \
        .withColumn("timestamp", to_timestamp(col("timestamp"))) \
        .withColumn("open", col("open").cast("double")) \
        .withColumn("high", col("high").cast("double")) \
        .withColumn("low", col("low").cast("double")) \
        .withColumn("close", col("close").cast("double")) \
        .withColumn("volume", col("volume").cast("long")) \
        .dropDuplicates()

    return silver_df


def write(df, output_path):
    df.write.mode("overwrite").parquet(output_path)