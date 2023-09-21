package modelo;

public class LibroObjectMother {

	public static void iniciarLibreria(Libreria libreria) {

		libreria.addLibro(new Libro("9780141036144", "1984", "George Orwell", "Penguin Books",
									10.99f, "Rústica", "Reedición", 3));
		libreria.addLibro(new Libro("9780061120084", "To Kill a Mockingbird", "Harper Lee",
						"Harper Perennial Modern Classics", 12.99f, "Grapada","Novedad",9));
		libreria.addLibro(new Libro("9780062315007", "The Catcher in the Rye", "J.D. Salinger",
									"Little, Brown and Company",11.49f, "Cartoné","Novedad",0));
		libreria.addLibro(new Libro("9780544003415", "The Hobbit", "J.R.R. Tolkien", 
								"Houghton Mifflin Harcourt", 14.99f, "Espiral","Reedición",0));
		libreria.addLibro(new Libro("9780060850524", "The Great Gatsby", "F. Scott Fitzgerald",
									"Scribner", 9.99f, "Rústica", "Novedad",0));
		libreria.addLibro(new Libro("9780743273565", "The Da Vinci Code", "Dan Brown", "Pocket Books",
									13.95f, "Espiral","Reedición", 0));
		libreria.addLibro(new Libro("9780061122415", "The Hunger Games", "Suzanne Collins", 
									"Scholastic Press", 8.99f, "Grapada", "Reedición", 12));
		libreria.addLibro(new Libro("9780143134772", "Becoming", "Michelle Obama", "Penguin Books",
									18.99f, "Grapada", "Novedad", 43));
		libreria.addLibro(new Libro("9780739326222", "The Road", "Cormac McCarthy", "Vintage", 10.95f,
									"Grapada", "Reedición", 22));
		libreria.addLibro(new Libro("9780062662851", "Educated", "Tara Westover", "Random House", 
									16.99f, "Grapada","Novedad",4));
	
	}

}
