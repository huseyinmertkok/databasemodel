package dao

import javax.inject.Inject
import models.UserData
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UsersDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Users = TableQuery[UsersTable]

  def all(): Future[Seq[UserData]] = db.run(Users.result)

  def insert(user: UserData): Future[Unit] = db.run(Users += user).map { _ => () }

  private class UsersTable(tag: Tag) extends Table[UserData](tag, "users") {

    def username = column[String]("username")
    def password = column[String]("password")

    def * = (username, password) <> (UserData.tupled, UserData.unapply)
  }
}
