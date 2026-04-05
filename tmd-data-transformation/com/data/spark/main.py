from spark_session_builder import get_spark_session
import bronze_to_silver
import silver_to_gold
import config

def main():

    spark = get_spark_session()

    # Bronze to Silver
    silver_df = bronze_to_silver.transform(spark, config.BRONZE_PATH)
    bronze_to_silver.write(silver_df, config.SILVER_PATH)

    print("Silver layer completed")

    # Silver to Gold
    gold_df = silver_to_gold.transform(silver_df)
    silver_to_gold.write(gold_df, config.GOLD_PATH)

    print("Gold layer completed")

    spark.stop()


if __name__ == "__main__":
    main()