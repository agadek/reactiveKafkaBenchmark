package com.reacKafka.utils

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.Random
import akka.pattern.pipe

/**
 * Created by admin on 17/01/17.
 */
class Receiver extends Actor {
  val stat = new Stat
  val n = 2000
  var partitions = Set[String]()
  var lastPrint = System.nanoTime()

  override def receive: Receive = {
    case msg @ Msg(inputTime, topic, partition) =>
      partitions += partition

      val durationMicros = (time() - inputTime) / 1000

      stat.add(durationMicros)

      //      println(s"# ${stat.count} $msg took $durationMicros micros") // FIXME

      if (stat.count % n == 0) {
        val newTime = time()

        val batchDurationMillis = (newTime - lastPrint) / 1000 / 1000
        println(s"$stat msg/s:${(n * 1000 / batchDurationMillis)} partitions: ${partitions.size}")
        partitions = Set()
        lastPrint = newTime
        stat.flush()
      }

      sender() ! Commit(msg.requestId)
  }

  def time() = System.nanoTime()

  override def postStop(): Unit = {
    println(stat.toString)
    super.postStop()
  }
}
