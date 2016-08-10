package readOther

import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.DStream

import org.json4s.jackson.JsonMethods._
import org.slf4j.LoggerFactory

object readRabbitmq {
  
    def createPropStream(ssc: StreamingContext): DStream[String] = {
       val host = ""
       val quene = ""
       val port =1750
       val user = ""
       val pass = ""
      RabbitMQUtils.createStreamFromAQueue(ssc, host,  port, quene,StorageLevel.MEMORY_AND_DISK_SER_2,Option("/"), Option(user), Option(pass))
  }
    
   def main(args:Array[String]):Unit={
     println("************")
     val conf = new SparkConf().setAppName("").setIfMissing("spark.master", "local[4]")
     val ssc = new StreamingContext(conf, Seconds(2))
     val rdd=createPropStream(ssc: StreamingContext)
     rdd.print()
     
    ssc.start()
    sys.addShutdownHook(() => ssc.stop())
    ssc.awaitTermination()
   }
    
    
}