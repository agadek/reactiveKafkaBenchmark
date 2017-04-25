package com.reacKafka.utils

import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.util.Try

object ApplicationConfig {

  val config = ConfigFactory.load()

  object akka {
    val akka = config.getConfig("akka")

    object actor {
      val actor = akka.getConfig("actor")
      val receiveTimeout: Duration = Try(actor.getString("receive-timeout")).map(Duration(_)).toOption.getOrElse(30 seconds)
    }

  }

  //  object kafka {
  //    val kafka = config.getConfig("kafka")
  //
  //    object producer {
  //      val producer = kafka.getConfig("producer")
  //    }
  //  }
}
