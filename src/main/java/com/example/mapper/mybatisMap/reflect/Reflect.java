package com.example.mapper.mybatisMap.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class Reflect {
    public static void main(String[] args) throws Exception{
       // test4();
        fan();  //泛型擦除
        fanCompare();
    }
    /**
     *
     *打印出String类所有的属性和方法
     */
    public static void test1(){
        Class c = String.class;
        Method[] methods = c.getMethods();
        for(int i = 0;i<methods.length;i++){
            System.out.println(methods[i].getName());
        }
        System.out.println("----------------------------String结束---------------------");
        System.out.println(String.class.getFields().length);
        Field[] fields = c.getFields();
        for(Field f : fields){
            System.out.println(f.getType()+":"+f.getName());
        }
    }

    /**
     * 根据类名动态创建类的对象
     * @throws Exception
     */
    public static void test2() throws Exception{
        Class c = Class.forName("com.didispace.reflect.Student");
        Student student = (Student)c.newInstance();
        student.setName("java");
        student.setId("1001");
        student.show();
    }

    /**
     * 根据类名和方法名，执行对象的方法
     * @param student
     * @param method
     * @param value
     * @throws Exception
     */
    public static void test3(Student student,String method,String value) throws Exception{
        String s1 = method.substring(0,1).toUpperCase();
        String s2 = method.substring(1);
        String m = "set"+s1+s2;
        System.out.println(m);
        Class c = student.getClass();
        Method set = c.getMethod(m,new Class[]{String.class});
        set.invoke(student,new Object[]{value});
    }

    /**
     * 动态创建数组对象，对数组元素复制和取值
     */
    public static void test4(){
        try{
            Class cls = Class.forName("java.lang.String");
            //创建一个String类型的数组，大小为10
            Object arr = Array.newInstance(cls, 10);
            //在数组5索引的位置赋值
            Array.set(arr, 5, "this is a testpack");
            //获取数组5索引位置的值
            String s = (String)Array.get(arr, 5);
            System.out.println(s);
        }catch(Throwable e){
            System.out.println(e);
        }
    }

    /**
     * https://www.cnblogs.com/xll1025/p/6489088.html
     * 泛型擦除:在程序中定义了一个ArrayList泛型类型实例化为Integer的对象，如果直接调用add方法，那么只能存储整形的数据。
     * 不过当我们利用反射调用add方法的时候，却可以存储字符串。这说明了Integer泛型实例在编译之后被擦除了，只保留了原始类型。
     */
    public static void fan(){
        try{
            ArrayList<Integer> arrayList3=new ArrayList<Integer>();
            arrayList3.add(1);//这样调用add方法只能存储整形，因为泛型类型的实例为Integer
            arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "asd");
            for (int i=0;i<arrayList3.size();i++) {
                System.out.println(arrayList3.get(i));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 我们定义了两个ArrayList数组，不过一个是ArrayList<String>泛型类型，只能存储字符串。
     * 一个是ArrayList<Integer>泛型类型，只能存储整形.最后，我们通过arrayList1对象和arrayList2对象的getClass方法获取
     * 它们的类的信息，最后发现结果为true。说明泛型类型String和Integer都被擦除掉了，只剩下了原始类型。
     */
    public static void fanCompare(){
        ArrayList<String> arrayList1=new ArrayList<String>();
        arrayList1.add("abc");
        ArrayList<Integer> arrayList2=new ArrayList<Integer>();
        arrayList2.add(123);
        System.out.println(arrayList1.getClass()==arrayList2.getClass());
    }


}

