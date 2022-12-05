package lab.annotations;

public class MethodsClass {

    MethodsClass() {
    }

    public int multiplePublic(int a, int b) {
        return a * b;
    }
    public int sumPublic(int a, int b) {
        return a + b;
    }
    @MyAnnotation(2)
    protected int multipleProtected(int a, int b) {
        return a * b;
    }
    @MyAnnotation(4)
    protected int sumProtected(int a, int b) {
        return a + b;
    }
    @MyAnnotation(3)
    private int multiplePrivate(int a, int b) {
        return a * b;
    }
    @MyAnnotation()
    private int sumPrivate(int a, int b) {
        return a + b;
    }

}
