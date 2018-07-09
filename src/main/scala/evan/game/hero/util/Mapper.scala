package evan.game.hero.util

import java.text.SimpleDateFormat

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

/**
  * Created by evan on 18-5-20.
  */
trait Mapper {
  final val mapper = {
    val _mapper = new ObjectMapper() with ScalaObjectMapper
    _mapper.setVisibility(PropertyAccessor.FIELD, Visibility.PUBLIC_ONLY)
    _mapper.registerModule(DefaultScalaModule).setSerializationInclusion(Include.NON_ABSENT)
    _mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)


    import com.fasterxml.jackson.databind.SerializationFeature

    _mapper.configure(SerializationFeature.INDENT_OUTPUT, false)
    _mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    _mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
    _mapper.registerModule(new SimpleModule())
    _mapper
  }
}
