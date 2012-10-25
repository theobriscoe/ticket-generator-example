package example.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.h2.tools.Csv;

public class TwapDatabaseImport {

	public void importTwap(String filename) throws SQLException {

		ResultSet rs = Csv.getInstance().read("data/test.csv", null, null);

		ResultSetMetaData meta = rs.getMetaData();
		while (rs.next()) {
			for (int i = 0; i < meta.getColumnCount(); i++) {
				System.out.println(meta.getColumnLabel(i + 1) + ": " + rs.getString(i + 1));
			}
			System.out.println();
		}
		rs.close();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
