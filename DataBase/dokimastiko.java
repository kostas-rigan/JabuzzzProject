package dokimastiko;
//Πριν το τρέξετε:
//Ο Driver πρέπει να κατέβει απο το (κατεβάστε ένα από τα δύο αρχεία):
//https://docs.microsoft.com/en-us/sql/connect/jdbc/release-notes-for-the-jdbc-driver?view=sql-server-ver15
//unzip
//Στο eclipse:
//AllCode > Build Path > Configure Build Path > Libraries > Classpath > Add External JARs...
//και μπαίνετε στον φάκελο που κατεβάσατε και ανοίγετε ένα από τα ακόλουθα αρχεία 
//mssql-jdbc-8.4.1.jre8.jar
//mssql-jdbc-8.4.1.jre11.jar
//mssql-jdbc-8.4.1.jre14.jar
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dokimastiko {
	//Notes:
	//1. jdbc:sqlserver:// αυτό είναι στάνταρ και αναφέρεται στο driver
	//2. sqlserver.dmst.aueb.gr σε αυτή τη θέση μπαίνει το όνομα του SQL Server 
	//(εγώ έβαλα αυτόν του εργαστηρίου και έχει αυτό το όνομα)
	//για να βρείτε το όνομα υτου sql server που χρησιμοποιείτε ακολουθείτε την 
	//διαδρομ: ανοίγετε sql > πάνω πάνω (πάνω και απο τον φάκελο Databases) έιναι το όνομα
	//μπορείτε να το βρείτε και πιο κυριλάτα πατώντας δεξί κλικ > Properties
	// > General > Name (στο πινακάκι) 
		//σημειώστε πως στη βάση του πανεπιστημίου είναι διαφορετικά τα ονόματα επειδή είναι
		//κλειδωμένη (νομίζω)
	//προσθέστε ένα ερωτηματικό μετά το όνομα
	//3.Database= + όνομα βάσης/πίνακα που θα χρησιμοποιήσετε + ;
	//4.IntegratedSecurity=true για να έχετε το ίδιο όνομα και κωδικό για πρόσβαση
	String connectionString=
			"jdbc:sqlserver://sqlserver.dmst.aueb.gr;Database=Categories;IntegratedSecurity=true";{
	try{
		try(Connection connection = DriverManager.getConnection(connectionString)){
			System.out.println("Connection Established");
		}
	}catch (SQLException e) {
		System.out.println("Error connection to the database");
		e.printStackTrace();
	}
}
//Αν δυσκολεύεστε σας βάζω ένα tutorial στο οποίο δουλεύει με το ίδιο script οπότε μπορείτε να βοηθηθείτε και από εκεί
//https://www.youtube.com/watch?v=92q_iJHjZ1g
}