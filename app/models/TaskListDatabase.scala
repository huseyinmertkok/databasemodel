package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}
import models.Tables._
import org.mindrot.jbcrypt.BCrypt

class TaskListDatabase(db: Database)(implicit executionContext: ExecutionContext) {

  def validateUser(username: String, password: String): Future[Boolean] = {
    val matches = db.run(Users.filter(userRow => userRow.username === username).result)
    matches.map(userRows => userRows.filter(userRow => BCrypt.checkpw(password, userRow.password)).nonEmpty)
  }

  def createUser(username: String, password: String): Future[Boolean] = {
    db.run(Users += UsersRow(-1, username, BCrypt.hashpw(password, BCrypt.gensalt())))
      .map(addCount => addCount > 0)
  }
}
