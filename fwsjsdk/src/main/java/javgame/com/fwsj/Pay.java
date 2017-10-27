//package javgame.com.fwsj;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//
///**
// * @author zhangf
// * @date 2017/10/25
// */
//public class Pay {
//    public static void main(String[] args) {
//        try {
//            Class c = Class.forName("javgame.com.fwsj.TestClass");
//            TestClass tc = (TestClass) c.newInstance();
//            Field[] fields = c.getDeclaredFields();
//            Method[] methods = c.getDeclaredMethods();
//
//            for (Field field : fields) {
//                if(field.isAnnotationPresent(BindProt.class)){
//                    BindProt bindProt = field.getAnnotation(BindProt.class);
//                    field.setAccessible(true);
//                    field.set(tc,bindProt.value());
//                }
//                if(field.isAnnotationPresent(BindAddress.class)){
//                    BindAddress bindAddress = field.getAnnotation(BindAddress.class);
//                    field.setAccessible(true);
//                    field.set(tc,bindAddress.value());
//                }
//            }
//
//            for (Method methid: methods) {
//                if(methid.isAnnotationPresent(BindGet.class)){
//                    BindGet annotation = methid.getAnnotation(BindGet.class);
//                    methid.setAccessible(true);
//                    methid.invoke(tc,annotation.url()+annotation.param());
//                }
//            }
////            tc.printInfo();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void testReflect() {
//        try {
//            Class<?> c = Class.forName("java.lang.String");
//            Field[] fields = c.getDeclaredFields();
//            Method[] methods = c.getDeclaredMethods();
//            StringBuilder sb = new StringBuilder();
//            //获得所有属性
//            for (Field field : fields) {
//                sb.append("\n");
//                sb.append(Modifier.toString(field.getModifiers()) + " ");
//                sb.append(field.getType().getSimpleName() + " ");
//                sb.append(field.getName());
//            }
//
//
//            System.out.println(sb.toString());
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
