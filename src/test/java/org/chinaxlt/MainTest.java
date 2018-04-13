package org.chinaxlt;

import org.chinaxlt.forClass.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
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
}
