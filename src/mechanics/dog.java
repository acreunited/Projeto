package mechanics;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class dog {
	private String name;
	private int age;

	public dog() {
		// empty constructor
	}

	public dog(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printDog() {
		System.out.println(name + " is " + age + " year(s) old.");
	}

	public static void main(String[] args) throws Exception {
		String dogClassName = "characters.dog";
		Class<?> dogClass = Class.forName(dogClassName); // convert string classname to Class object

		Class<?>[] paramTypes = new Class[] { String.class, int.class };
		Constructor<?> ct = dogClass.getConstructor(paramTypes);

		Object[] constArgs = new Object[] { "Mishka", 3 };
		Object dogObject = ct.newInstance(constArgs);

		((dog) dogObject).printDog(); // direct access and cast

		// Object dog = dogClass.getDeclaredConstructor().newInstance(); // invoke empty
		// constructor

		String methodName = "";

		// with single parameter, return void
		methodName = "setName";
		Method setNameMethod = dogObject.getClass().getMethod(methodName, String.class);
		setNameMethod.invoke(dogObject, "Mishka2"); // pass arg

		// without parameters, return string
		methodName = "getName";
		Method getNameMethod = dogObject.getClass().getMethod(methodName);
		String name = (String) getNameMethod.invoke(dogObject); // explicit cast

		System.out.println("New dog name is: " + name);

		// creates object of the desired field by providing
		// the name of field as argument to the
		// getDeclaredField method

		Field field = dogClass.getDeclaredField("age");

		// allows the object to access the field irrespective
		// of the access specifier used with the field
		field.setAccessible(true);

		// takes object and the new value to be assigned
		// to the field as arguments
		field.set(dogObject, 2);

		methodName = "printDog";
		Method printDogMethod = dogObject.getClass().getMethod(methodName);
		printDogMethod.invoke(dogObject);
	}
}