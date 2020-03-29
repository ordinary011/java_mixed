package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str() default "testing";
    int val() default 900;
    String value();
}

public class Meta {
//    @MyAnno(str = "Two parameters", val = 19)
    @MyAnno("go")
    public static void myMeth(String str, int i){
        Meta obj = new Meta();

        try {
            Class<?> c = obj.getClass();
            Method m = c.getMethod("myMeth", String.class, int.class);

            MyAnno anno = m.getAnnotation(MyAnno.class);

//            System.out.println(anno.str() + " " + anno.val());
            System.out.println(anno.value());
        }catch (NoSuchMethodException e) {
            System.out.println("method not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        myMeth("test", 10);
    }
}

