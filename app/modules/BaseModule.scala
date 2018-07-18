package modules

import com.google.inject.AbstractModule
import dao.AuthTokenDAO
import dao.impl.AuthTokenDAOImpl
import service.AuthTokenService
import net.codingwell.scalaguice.ScalaModule
import service.impl.AuthTokenServiceImpl

/**
 * The base Guice module.
 */
class BaseModule extends AbstractModule with ScalaModule {

  /**
   * Configures the module.
   */
  def configure(): Unit = {
    bind[AuthTokenDAO].to[AuthTokenDAOImpl]
    bind[AuthTokenService].to[AuthTokenServiceImpl]
  }
}
