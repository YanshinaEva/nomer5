package ex;

public class main {
    public static void main(String[] args) {
        ClassWithAnnotation classWithAnnotation = new ClassWithAnnotation();
        Injector injector = new Injector();
        injector.inject(classWithAnnotation);
    }
}
