package com.santaclose.lib.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger {
  return LoggerFactory.getLogger(T::class.java)
}
