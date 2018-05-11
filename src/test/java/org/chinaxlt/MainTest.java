package org.chinaxlt;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.chinaxlt.forClass.*;
import org.chinaxlt.forInterface.MyConverter;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainTest {

    @Test
    public void testFackory() {
        Factory fac = new Factory();
        Sample obj1 = fac.getInstance(1);
        Sample obj2 = fac.getInstance(2);
        System.out.println(obj1.getClass().toString());
        System.out.println(obj2.getClass().toString());
    }

    @Test
    public void testList() {
        List<String> a = new ArrayList<String>();
        List<Integer> b = new ArrayList<Integer>();
        System.out.println(a.getClass().toString());
        System.out.println(b.getClass().toString());
        System.out.println(a.getClass().equals(b.getClass()));
    }

    @Test
    public void testCollection() {
        Vector<String> vec = new Vector<String>();
        vec.add("2131");
        vec.add("qweq");
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("1", "2131");
        hmap.put("2", "qweq");
        TreeMap<String, String> tmap = new TreeMap<String, String>();
        tmap.put("1", "2131");
        tmap.put("2", "qweq");
        ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>();
        cmap.put("1", "2131");
        cmap.put("2", "qweq");
        LinkedHashMap<String, String> lmap = new LinkedHashMap<String, String>();
        lmap.put("1", "2131");
        lmap.put("2", "qweq");
        LinkedList<String> llist = new LinkedList<String>();
        llist.add("2131");
        llist.add("qweq");
        Map<String, String> map = Collections.synchronizedMap(hmap);
        Set<String> set = new TreeSet<String>();
        set.add("2131");
        Vector<String> vector = new Vector<String>();
        vector.add("2131");
        Hashtable<String, String> htable = new Hashtable<String, String>();
        htable.put("1", "2131");
        Collection<String> list = Collections.synchronizedCollection(new ArrayList<String>());
        System.out.println(vec.getClass().toString());
        System.out.println(map.getClass().toString());
        System.out.println(hmap.getClass().toString());
        System.out.println(lmap.getClass().toString());
        System.out.println(tmap.getClass().toString());
        System.out.println(cmap.getClass().toString());
        System.out.println(cmap instanceof AbstractMap);
        System.out.println(llist.getClass().toString());
    }

    @Test
    public void testThread() throws ExecutionException, InterruptedException {
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        ExecutorService pool2 = Executors.newFixedThreadPool(10);
        ExecutorService pool3 = Executors.newCachedThreadPool();
        ExecutorService pool4 = Executors.newScheduledThreadPool(10);

        System.out.println("=====Thread Test=====");
        ThreadTest thd = new ThreadTest();
        Thread thread1 = new Thread(thd);
        thread1.start();
        pool2.execute(thd);

        System.out.println("=====Thread Test Lock=====");
        ThreadTestLock thdtl = new ThreadTestLock();
        Thread thread2 = new Thread(thdtl);
        thread2.start();
        pool2.execute(thdtl);

        System.out.println("=====ThreadReturnValue Test=====");
        ThreadReturnValue thdRV = new ThreadReturnValue();
        Future result1 = pool2.submit(thdRV);
        Future result2 = pool2.submit(thdRV);
        pool2.shutdown();

        System.gc();
        Runtime.getRuntime().gc();
    }

    @Test
    public void testObj() throws NoSuchFieldException, IllegalAccessException {
        Sample sa = new Sample();
        Class<? extends Sample> classInfo = sa.getClass();
        Field field = classInfo.getDeclaredField("value");
        field.setAccessible(true);
        field.set(sa, "99");
        System.out.println(sa.getValue());
    }

    @Test
    public void listTest() {
        List<Integer> nums = new ArrayList<>();
        nums.add(-2);
        nums.add(-1);
        nums.add(0);
        nums.add(1);
        List<Integer> numlist1 = nums.stream().filter(num -> num < 0).collect(Collectors.toList());
        List<Integer> numlist2 = nums.stream().filter(num -> num >= 0).collect(Collectors.toList());
        System.out.println(numlist1.toString());
        System.out.println(numlist2.toString());
        List<String> typelist = Arrays.asList(TypeEnum.values()).stream().map(te -> te.getStatus()).collect(Collectors.toList());
        System.out.println(typelist.toString());
    }

    @Test
    public void testBds() {
        String result = 1 > 2 ? ">" : "<";
        System.out.println(result);
    }

    @Test
    public void testQuene() {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<String>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("poll=" + queue.poll()); //返回第一个元素，并在队列中删除
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element=" + queue.element()); //返回第一个元素
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("peek=" + queue.peek()); //返回第一个元素
        for (String q : queue) {
            System.out.println(q);
        }
    }

    @Test
    public void booleanTest() {
        System.out.println(0 > 0);
        System.out.println(1 > 0);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        System.out.println(map);
    }

    @Test
    public void myTest() {
        List values = Arrays.asList(1, 2, 3, 4, 5);
        values.forEach(n -> System.out.println("value:" + n));

        Predicate<String> check = a -> a.length() > 0;
        System.out.println(check.test("12345"));
        Predicate<Object> check2 = Objects::nonNull;
        System.out.println(check2.test("12345"));

        String str = "1-2 - 3 -4-5";
        List<String> strlist = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        strlist.forEach(System.out::println);
    }

    @Test
    public void ConverterTest() {
        List<String> dataList = Lists.newArrayList();
        dataList.add("LIST");
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("KEY" , "VALUS");

        MyModel myModel = new MyModel();
        myModel.setDataList(dataList);
        myModel.setDataMap(dataMap);

        MyDTO myDto = MyConverter.instance.domain2dto(myModel);
        System.out.println(myDto);
    }
}
