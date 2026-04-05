from pyspark.sql.window import Window
from pyspark.sql.functions import avg

def transform(silver_df):

    window_spec = Window.orderBy("timestamp").rowsBetween(-2, 0)

    gold_df = silver_df.withColumn(
        "moving_avg",
        avg("close").over(window_spec)
    )

    return gold_df


def write(df, output_path):
    df.write.mode("overwrite").parquet(output_path)