/**
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package readOther

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.ReceiverInputDStream

object RabbitMQUtils {

  /**
   * Create an input stream that receives messages from a RabbitMQ queue.
   * @param ssc                StreamingContext object
   * @param rabbitMQHost       Url of remote RabbitMQ server
   * @param rabbitMQPort       Port of remote RabbitMQ server
   * @param rabbitMQQueueName  Queue to subscribe to
   * @param storageLevel       RDD storage level. Defaults to StorageLevel.MEMORY_AND_DISK_SER_2.
   */
  def createStreamFromAQueue(ssc: StreamingContext,
                             rabbitMQHost: String,
                             rabbitMQPort: Int,
                             rabbitMQQueueName: String,
                             storageLevel: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER_2,
                             rabbitMQVHost: Option[String] = None,
                             rabbitMQUser: Option[String] = None,
                             rabbitMQPass: Option[String] = None
                              ): ReceiverInputDStream[String] = {
    new RabbitMQInputDStream(
      ssc,
      Some(rabbitMQQueueName),
      rabbitMQHost,
      rabbitMQPort,
      None,
      Seq(),
      storageLevel,
      rabbitMQVHost,
      rabbitMQUser,
      rabbitMQPass
    )
  }


  /**
   * Create an input stream that receives messages from a RabbitMQ queue.
   * @param ssc              StreamingContext object
   * @param rabbitMQHost     Url of remote RabbitMQ server
   * @param rabbitMQPort     Port of remote RabbitMQ server
   * @param exchangeName     Exchange name to subscribe to
   * @param routingKeys      Routing keys to subscribe to
   * @param storageLevel     RDD storage level. Defaults to StorageLevel.MEMORY_AND_DISK_SER_2.
   * @param rabbitMQVHost
   * @param rabbitMQUser
   * @param rabbitMQPass
   * @return
   */
  def createStreamFromRoutingKeys(ssc: StreamingContext,
                                  rabbitMQHost: String,
                                  rabbitMQPort: Int,
                                  exchangeName: String,
                                  routingKeys: Seq[String],
                                  storageLevel: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER_2,
                                  rabbitMQVHost: Option[String] = None,
                                  rabbitMQUser: Option[String] = None,
                                  rabbitMQPass: Option[String] = None
                                   ): ReceiverInputDStream[String] = {
    new RabbitMQInputDStream(
      ssc,
      None,
      rabbitMQHost,
      rabbitMQPort,
      Some(exchangeName),
      routingKeys,
      storageLevel,
      rabbitMQVHost,
      rabbitMQUser,
      rabbitMQPass
    )
  }


}
