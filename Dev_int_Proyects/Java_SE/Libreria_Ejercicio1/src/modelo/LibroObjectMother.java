package modelo;

public class LibroObjectMother {

	public static void iniciarLibreria(Libreria libreria) {

		libreria.addLibro(new Libro("9780141036144", "1984", "George Orwell", "Penguin Books", 10.99f));
		libreria.addLibro(new Libro("9780061120084", "To Kill a Mockingbird", "Harper Lee",
				"Harper Perennial Modern Classics", 12.99f));
		libreria.addLibro(new Libro("9780062315007", "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company",
				11.49f));
		libreria.addLibro(new Libro("9780544003415", "The Hobbit", "J.R.R. Tolkien", "Houghton Mifflin Harcourt", 14.99f));
		libreria.addLibro(new Libro("9780060850524", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", 9.99f));
		libreria.addLibro(new Libro("9780743273565", "The Da Vinci Code", "Dan Brown", "Pocket Books", 13.95f));
		libreria.addLibro(new Libro("9780061122415", "The Hunger Games", "Suzanne Collins", "Scholastic Press", 8.99f));
		libreria.addLibro(new Libro("9780143134772", "Becoming", "Michelle Obama", "Penguin Books", 18.99f));
		libreria.addLibro(new Libro("9780739326222", "The Road", "Cormac McCarthy", "Vintage", 10.95f));
		libreria.addLibro(new Libro("9780062662851", "Educated", "Tara Westover", "Random House", 16.99f));
	
	}

}
