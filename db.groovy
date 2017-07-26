@GrabConfig(systemClassLoader=true)
@Grab(group='com.h2database', module='h2', version='1.4.196')

import java.sql.*
import groovy.sql.Sql
import org.h2.jdbcx.JdbcConnectionPool

def sql = Sql.newInstance("jdbc:h2:./things", "sa", "sa", "org.h2.Driver")  // DB files for 'things' in current directory

sql.execute("DROP TABLE IF EXISTS things")

def createTbl = '''
CREATE TABLE IF NOT EXISTS things (
  id INT PRIMARY KEY,
  thing1 VARCHAR(50),
  thing2 VARCHAR(100)
);
'''

sql.execute(createTbl)
sql.execute("INSERT INTO things VALUES(:id, :thing1, :thing2)", [id: 0, thing1: 'I am thing1', thing2: 'I am thing2'])
sql.execute("INSERT INTO things VALUES(:id, :thing1, :thing2)", [id: 1, thing1: 'foo', thing2: 'bar'])
sql.execute("INSERT INTO things VALUES(:id, :thing1, :thing2)", [id: 2, thing1: 'Alisa', thing2: 'Yeoh'])

def query = "SELECT * FROM things"

sql.eachRow(query) {
  println "PRIMARY KEY: $it.id; THING1: ${it.thing1}; THING2: $it.thing2"
}

sql.close()
