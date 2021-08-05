package models

object CodeGen extends App{
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.PostgresProfile",
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost/tasklist?user=postgres&password=123",
    "C:\\Users\\ABRA\\Desktop\\databasemodel\\app\\",
    "models",None,None,true,false
  )

}
