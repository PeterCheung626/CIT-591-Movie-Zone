package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import moviezone.*;
public class MovieTest {
	private static ExactSearchEngine ese, ese2;
	private static Movie m, n;
	
	@BeforeClass
	public static void setUpMovie() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {}
		ese = new ExactSearchEngine(36670);
		ese2 = new ExactSearchEngine(221782);
		m = ese.getMovie();
		n = ese2.getMovie();
		
	}
	
	@Test
	public void testId() {
		
		int id = m.getId();
		assertEquals("Id should be 36670", 36670, id);
	}

	@Test
	public void testTitle() {
		String title = m.getTitle();
		assertEquals("Title should be Never Say Never Again", "Never Say Never Again", title);
	}

	@Test
	public void testIsGenre() {
		String genre = "romantic";
		assertEquals("Movie should not be of romantic genre", false, m.isGenre(genre));
	}

	@Test
	public void testReleaseDate() {
		Date d = new Date("1983-10-7");
		assertEquals("The release date should be 1983/10/7", 0, m.getReleaseDate().compareTo(d));
	}

	@Test
	public void testPopularity() {
		double p = m.getPopularity();
		assertEquals("Polularity should be a positvie number", true, p > 0);
	}

	@Test
	public void testRuntime() {
		int t = m.getRunTime();
		assertEquals("Runtime should be 134", 134, t);
	}

	@Test
	public void testDirector() {
		String s = m.getDirector();
		assertEquals("Director should be Irvin Kershner", "Irvin Kershner", s);
	}
	
	@Test
	public void testRating() {
		double r = m.getRatings();
		assertEquals(5.9, r, 0.001);
	}
	
	@Test
	public void testCountry() {
		ArrayList<String> c = m.getProductCountry();
		assertEquals("Product countries should include USA", true, c.contains("USA"));
		assertEquals("Product countries should include Germany", true, c.contains("Germany"));
		assertEquals("Product countries should include United Kingdom", true, c.contains("United Kingdom"));
		assertEquals("Product countrues should not include Japan", false, c.contains("Japan"));
	}

	@Test
	public void testCast() {
		ArrayList<String> c = m.getCast();
		assertEquals("Cast should include Sean Connery", true, c.contains("Sean Connery"));
		assertEquals("Cast should include Marsha A. Hunt", true, c.contains("Marsha A. Hunt"));
		assertEquals("Cast should not include Brad Pitt", false, c.contains("Brad Pitt"));
	}

	@Test
	public void testGenre() {
		ArrayList<String> g = m.getGenres();
		assertEquals("This should be an action movie", true, g.contains("Action"));
	}

	@Test
	public void testLanguages() {
		ArrayList<String> lang = m.getLanguages();
		assertEquals("This movie is in English", true, lang.contains("English"));
		assertEquals("This movie is also in French", true, lang.contains("Français"));
		assertEquals("This movie is in 4 languages in total", 4, lang.size());
	}

	@Test
	public void testKeyword() {
		ArrayList<String> k = m.getKeywords();
		assertEquals("This movie should have 8 keywords", 8, k.size());
	}
	
	@Test
	public void testSimilar() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {}
		ArrayList<Movie> ms = m.getSimilarMovies();
		assertNotNull(ms);
		assertEquals("There should be less than 5 movies recommended", true, ms.size() < 5);
	}
	
	@Test
	public void testRecommendations() {
		ArrayList<Movie> ms = m.getRecommendations();
		assertNotNull(ms);
		assertEquals("There should be no more than 5 similar movies", true, ms.size() <= 5);
	}
	
	@Test
	public void testRecommend() {
		ArrayList<Movie> like = new ArrayList<>();
		ArrayList<Movie> watch = new ArrayList<>();
		like.add(m);
		watch.add(m);
		ArrayList<Movie> rec = Movie.recommend(like, watch);
		assertNotNull(rec);
		System.out.println(rec.size());
		assertEquals("There should be no more than 5 similar movies", true, rec.size() <= 5);
	}
	
	@Test 
	public void testNullPoster() {
		String s = n.getPoster();
		assertNotNull(s);
		assertEquals("Movie with no poster should use default poster", "http://gogmat.com/images/cancelling%20gmat%20score.jpg", s);
	}
	
	@Test
	public void testEmptyKeywords() {
		ArrayList<String> s = n.getKeywords();
		assertNull(s);
	}
	
	@Test
	public void testEmptyGenres() {
		ArrayList<String> s = n.getGenres();
		assertNull(s);
	}
	
	@Test
	public void testEmptyCountries() {
		ArrayList<String> s = n.getProductCountry();
		assertNull(s);
	}
	
	@Test
	public void testEmptyCast() {
		ArrayList<String> c = n.getCast();
		assertNull(c);
	}
	
	@Test
	public void testDefaultRating() {
		double r = n.getRatings();
		assertEquals(0, r, 0.001);
	}
	
	@Test
	public void testEmptyLanguage() {
		assertNull(n.getLanguages());
	}
	
	@Test
	public void testEmptyDirector() {
		assertNull(n.getDirector());
	}
	
	

}
