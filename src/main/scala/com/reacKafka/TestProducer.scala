package com.reacKafka

import java.util.concurrent.ThreadLocalRandom

import akka.actor.ActorSystem
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.collection.JavaConversions._

class TestProducer(bootstrapServers:String)(implicit system: ActorSystem) {

  def run(): Unit = {
    val topic = system.settings.config.getString("topicName")
    val props = new scala.collection.mutable.HashMap[String, String]()
    //props.put("bootstrap.servers", ApplicationConfig.kafka.producer.producer.getString("bootstrap.servers"))
    props.put("bootstrap.servers", bootstrapServers)

    val r = ThreadLocalRandom.current()

    val producer = new KafkaProducer(props, new StringSerializer, new StringSerializer)
    while (true) {
      // target ~ 1000 msg/s

      (1 to 10).foreach { y =>
        val time = System.nanoTime().toString
        producer.send(new ProducerRecord[String, String](topic, r.nextInt(10000).toString, time))
      }
      Thread.sleep(9)
    }
  }

}