package org.ko.spark.streaming.project.domain

/**
  * 实战课程搜索引擎跳转点击数实体类
  * @param day_search_course
  * @param click_count
  */
case class CourseSearchClickCount(day_search_course: String, click_count:Long)
