package hu.neusch.utils

import java.util.stream.Collectors
import scala.collection.JavaConverters._

object Converters {

  /**
    * Converts java.util.stream.Stream of T into scala.List of T
    * @param stream Java Stream of T
    * @tparam T type T
    * @return Scala List of T
    */
  def convertJavaStreamToScalaList[T](stream: java.util.stream.Stream[T]): List[T] ={
    var javaList: java.util.List[T] = stream.collect(Collectors.toList[T]())
    javaList.asScala.toList
  }

}
