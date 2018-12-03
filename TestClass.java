import java.lang.reflect.Method;
import java.util.Objects;

public class TestClass {
    private String someString;
    private boolean isSomething;
    private int someInt;
    private Double someDouble;

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public boolean isSomething() {
        return isSomething;
    }

    public void setSomething(boolean something) {
        isSomething = something;
    }

    public int getSomeInt() {
        return someInt;
    }

    public void setSomeInt(int someInt) {
        this.someInt = someInt;
    }

    public Double getSomeDouble() {
        return someDouble;
    }

    public void setSomeDouble(Double someDouble) {
        this.someDouble = someDouble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return isSomething() == testClass.isSomething() &&
                getSomeInt() == testClass.getSomeInt() &&
                Objects.equals(getSomeString(), testClass.getSomeString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSomeString(), isSomething(), getSomeInt());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method methlist[] = o.getClass().getDeclaredMethods();
        TestClass testClass = (TestClass) o;
        Method isSomethingMethod;
        Method getSomeIntMethod;
        Method getSomeStringMethod;
        for (int i = 0; i < methlist.length; i++) {
            Method m = methlist[i];
            if (m.getName().contains("isSomething"))
                isSomethingMethod = m;
            if (m.getName().contains("getSomeInt"))
                getSomeIntMethod = m;
            if (m.getName().contains("getSomeString"))
                getSomeStringMethod = m;
        }
        return isSomething() == isSomethingMethod.invoke(testClass) &&
                getSomeInt() == getSomeIntMethod.invoke(testClass) &&
                Objects.equals(getSomeString(), getSomeStringMethod.invoke(testClass));
    }
}
