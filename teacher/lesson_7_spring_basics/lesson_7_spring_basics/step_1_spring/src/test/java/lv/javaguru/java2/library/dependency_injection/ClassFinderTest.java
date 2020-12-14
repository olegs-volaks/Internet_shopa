package lv.javaguru.java2.library.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ClassFinderTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		ClassFinder finder = new ClassFinder();
		List<Class> classes = finder.findClassesInsidePackage("lv.javaguru.java2.library");
		classes.forEach(aClass -> {
			System.out.println(aClass.getName());
		});
	}

}