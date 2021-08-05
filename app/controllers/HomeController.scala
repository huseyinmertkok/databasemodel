package controllers

import dao.UsersDao
import javax.inject._
import models.TaskListDatabase
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext
import com.github.cleverage.elasticsearch.ScalaHelpers.{IndexQuery, _}
import com.github.cleverage.elasticsearch.component.IndexComponent
import javax.inject.{Singleton, Inject}
import indexing.{IndexTest, IndexTestManager}
import org.elasticsearch.index.query.QueryBuilders
import play.api._
import play.api.mvc._
import play.Logger

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(indexComponent: IndexComponent,usersDao: UsersDao, protected val dbConfigProvider: DatabaseConfigProvider, controllerComponents: ControllerComponents)(implicit executionContext: ExecutionContext)
  extends AbstractController(controllerComponents) with HasDatabaseConfigProvider[JdbcProfile]{

  private val model = new TaskListDatabase(db)

  def index = Action.async {
    val indexTest = IndexTest("1", "Log", "The category")
    IndexTestManager.index(indexTest)
    Logger.info("IndexTestManager.index() : " + indexTest)

    val gettingIndexTest = IndexTestManager.get("1")
    Logger.info("IndexTestManager.get() => " + gettingIndexTest)

    IndexTestManager.delete("1")
    Logger.info("IndexTestManager.delete()");

    val gettingIndexTestMore = IndexTestManager.get("1")
    Logger.info("IndexTestManager.get() => " + gettingIndexTestMore)

    IndexTestManager.index(IndexTest("1", "Log1", "First category"))
    IndexTestManager.index(IndexTest("2", "Log2", "First category"))
    IndexTestManager.index(IndexTest("3", "Log3", "Second category"))
    IndexTestManager.index(IndexTest("4", "Log4", "Second category"))

    val indexQuery = IndexQuery[IndexTest]()
      .withBuilder(QueryBuilders.matchQuery("name", "Here"))
    val results: IndexResults[IndexTest] = IndexTestManager.search(indexQuery)
    Logger.info("IndexTestManager.search()" + results)

    usersDao.all().map { case (users) => Ok(views.html.index(users)) }
  }

}
