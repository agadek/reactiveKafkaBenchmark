package com.reacKafka.utils

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

/**
 * Created by admin on 17/01/17.
 */
class Stat {
  var ds = new DescriptiveStatistics();

  def add(num: Long): Unit = ds.addValue(num)

  override def toString =
    f"sampleSize:$count \t min:$min%1.0f \t max:$max%1.0f \t mean:$maen%1.0f \t median:$median%1.0f\t p95:$p95%1.0f\t p99:$p99%1.0f"

  def flush() = {
    ds = new DescriptiveStatistics()
  }

  def count = ds.getN

  def maen = ds.getMean

  def min = ds.getMin

  def max = ds.getMax

  def p95 = ds.getPercentile(95)

  def p99 = ds.getPercentile(99)

  def median = ds.getPercentile(50)

}
