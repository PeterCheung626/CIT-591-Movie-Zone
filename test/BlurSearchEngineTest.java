package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import moviezone.*;

class BlurSearchEngineTest {
	BlurSearchEngine bse;
	
	@Test
	public void testTitleSearch() {
		bse = new BlurSearchEngine("love");
		assertNotNull(bse.getMovies());
	}
	
	@Test
	public void testKeywordSearch() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {}
		bse = new BlurSearchEngine("love", 2);
		assertNotNull(bse.getMovies());
	}
	
	@Test
	public void testTopRatedSearch() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {}
		bse = new BlurSearchEngine("top rated", 1);
		assertNotNull(bse.getMovies());
		assertTrue(bse.getMovies().size() < 20);
	}
	
	@Test
	public void testSimilarSearch() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {}
		bse = new BlurSearchEngine(36670, 1);
		assertNotNull(bse.getMovies());
		assertTrue(bse.getMovies().size() < 20);
	}
	
	@Test
	public void testRecommendSearch() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {}
		bse = new BlurSearchEngine(36670, 2);
		assertNotNull(bse.getMovies());
		assertTrue(bse.getMovies().size() < 20);
	}
	
	

}
