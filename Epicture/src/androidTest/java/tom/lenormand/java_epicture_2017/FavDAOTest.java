package tom.lenormand.java_epicture_2017;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import tom.lenormand.java_epicture_2017.bd.FavDAO;
import tom.lenormand.java_epicture_2017.bd.Favoris;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FavDAOTest {
	@Test
	public void useAppContext() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();

		assertEquals("tom.lenormand.java_epicture_2017", appContext.getPackageName());
	}

	@Test
	public void checkAddTableInDB() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();
		FavDAO fav = new FavDAO(appContext);

		fav.add(new Favoris("test", "test2", "www.google.com", "www.google.com", 11, "42"));
		ArrayList<Favoris> list =  fav.getFav("test");

		if (list.size() != 1)
			throw new Exception("Invalid add");
		assertTrue(list.get(0).getOwner().equals("test"));
		assertTrue(list.get(0).getUserName().equals("test2"));
		assertTrue(list.get(0).getProfil_picture().equals("www.google.com"));
		assertTrue(list.get(0).getPicture_url().equals("www.google.com"));
		assertTrue(list.get(0).getLikes() == 11);
		assertTrue(list.get(0).getId().equals("42"));
	}

	@Test
	public void checkDeleteTableInDB() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();
		FavDAO fav = new FavDAO(appContext);

		fav.add(new Favoris("test", "test2", "www.google.com", "www.google.com", 11, "42"));
		fav.deleteFromPictureUrl("test", "www.google.com");
		ArrayList<Favoris> list =  fav.getFav("test");

		if (list.size() != 0)
			throw new Exception("Invalid deletion of the table");
	}
}
