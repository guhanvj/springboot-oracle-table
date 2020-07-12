package mycompany.web;

import mycompany.processor.ExportData2CSV;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class TableDataController {
    private Session session;
    private ExportData2CSV csv;

    public TableDataController(Session session,ExportData2CSV csv) {
        this.session = session;
        this.csv = csv;
    }

    @GetMapping(path = "oracle/table/compare")
    @Transactional(rollbackFor = Exception.class)
    public void generateCsv(){
        session.doReturningWork(new ReturningWork<Void>() {

          @Override
        public Void execute(Connection conn) throws SQLException {
              Statement stmt=null;
              ResultSet rset=null;
              try {

                  stmt = conn.createStatement();
                  rset = stmt.executeQuery("select * from dimo.family_member");
                  csv.ExportData2CSV(rset,"C:\\Users\\developer\\Documents\\MyFile.csv",true,",");
                  csv.createFileCsv();

              } catch (SQLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }
              finally {
                  if (stmt != null) {stmt.close();}
                  if (conn != null) {conn.close();}
                  if (rset != null) {rset.close();}
              }
              return null;
        }
        });
    }


}
