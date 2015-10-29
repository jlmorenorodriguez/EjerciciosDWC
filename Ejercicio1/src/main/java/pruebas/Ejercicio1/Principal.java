package pruebas.Ejercicio1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

import static com.mongodb.client.model.Projections.*;

import static com.mongodb.client.model.Updates.*;
public class Principal {

	public static void main(String[] args) throws NullPointerException {
		// TODO Auto-generated method stub

		
		

		try {
			
		
			
			MongoClient cliente = new MongoClient("localhost", 27017);
			MongoDatabase basededatos = cliente.getDatabase("Libreria");
			MongoCollection<Document> coleccion = basededatos.getCollection("libros");
			
			Document lib = new Document().append("titulo", "Dictado musical 5").append("nº pag", 0)
					.append("editorial", "SM").append("año", 2012)
					.append("caracteristicas", new Document().append("precio", 22.3).append("web", "www.sm.es"));

			coleccion.insertOne(lib);
			
			//i. Encontrar el primer libro cuyo número de páginas sea mayor que 200 y su año de publicación sea el 2014.

			Document doc = coleccion.find(and(gt("nº pag", 200), eq("año", 2014))).first();
			System.out.println("El Primer libro con mas de 200 paginas y año 2014  :  " + doc.get("titulo") + ", "
					+ doc.getString("editorial") + "," + doc.getInteger("año"));
			//ii. Listar todos los libros de la editorial Anaya.
			System.out.println("Listar todos los libros de la editorial Anaya");
			MongoCursor<Document> cursor = coleccion.find(eq("editorial", "Anaya")).iterator();
			while (cursor.hasNext()) {
				Document document = (Document) cursor.next();
				System.out.println(document);
			}
			cursor.close();
			//iii. Contar todos los libros cuyo precio sea superior a 20€.
			System.out.println("Libros cuyo precio sea mayor a 20€: ");
			Block<Document> printBlock = new Block<Document>() {
				public void apply(final Document document) {
					System.out.println(document.toJson());
				}
			};
			coleccion.find(gte("caracteristicas.precio", 20)).forEach(printBlock);
			//iv. Mostrar todos los libros que tengan una web de referencia.
			System.out.println("Libros que tengan web de referencia: ");
			Block<Document> printBlock2 = new Block<Document>() {
				public void apply(final Document document1) {
					System.out.println(document1.toJson());
				}
			};
			//c. Hacer una consulta de todos los libros ordenados por precio.

			coleccion.find(exists("caracteristicas.web")).forEach(printBlock2);
			System.out.println("Libros ordenados por precio");
			Block<Document> printBlock3 = new Block<Document>() {
				public void apply(Document doc) {
					System.out.println(doc);
				}
			};
			coleccion.find().sort(ascending("caracteristicas.precio")).forEach(printBlock3);
			// d. Actualizar todos los documentos de la colección incrementando su
			// precio en 2€ siempre que su año de publicación sea el 2010, 2012 o
			// 2014.
			UpdateResult updateresult = coleccion.updateMany(or(eq("año", 2010), eq("año", 2012), eq("año", 2014)),
					inc("caracteristicas.precio", 2));
			System.out.println("Libros incrementados de precio en 2€ de los años 2010,2012,2014 : " + updateresult.getModifiedCount());
			
			//e. Borrar todos los documentos cuyo precio sea 0€ o el número de páginas sea 0.
			System.out.println("Borrar libros con coste 0€ y sin paginas. ");
			MongoCursor<Document> borrar = coleccion.find(or(eq("caracteristicas.precio", 0),eq("nº pag",0))).iterator();
			while (borrar.hasNext()) {
				Document document = (Document) borrar.next();
				System.out.println("Libro "+document.getString("titulo")+ " borrado.");
				coleccion.deleteOne(document);
			}
			borrar.close();

//			coleccion.deleteMany(or(eq("caracteristicas.precio", 0),eq("nº pag",0)));
			
			//System.out.println("Borrados: " + borrado);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
