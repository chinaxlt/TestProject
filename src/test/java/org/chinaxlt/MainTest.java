package org.chinaxlt;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.val;
import org.apache.commons.beanutils.BeanUtils;
import org.chinaxlt.classTest.*;
import org.chinaxlt.util.MD5Utils;
import org.chinaxlt.util.ObjectUtils;
import org.junit.Test;
import org.junit.gen5.commons.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        nums.add(1);
        List<Integer> numlist1 = nums.stream().filter(num -> num < 0).collect(Collectors.toList());
        List<Integer> numlist2 = nums.stream().filter(num -> num >= 0).collect(Collectors.toList());
        List<Integer> numlist3 = nums.stream().filter(num -> num >= 10).collect(Collectors.toList());
        System.out.println(numlist1.toString());
        System.out.println(numlist2.toString());
        List<String> typelist = Arrays.asList(TypeEnum.values()).stream().map(te -> te.getStatus()).collect(Collectors.toList());
        System.out.println(typelist.toString());
    }

    @Test
    public void timeTest() {
        //Asia/Kuala_Lumpur +8
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("System Default TimeZone : " + defaultZoneId);

        //toString() append +8 automatically.
        Date date = new Date();
        System.out.println("date : " + date);

        //1. Convert Date -> Instant
        Instant instant = date.toInstant();
        System.out.println("instant : " + instant); //Zone : UTC+0

        //2. Instant + system default time zone + toLocalDate() = LocalDate
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        System.out.println("localDate : " + localDate);

        //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        System.out.println("localDateTime : " + localDateTime);

        //4. Instant + system default time zone = ZonedDateTime
        ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
        System.out.println("zonedDateTime : " + zonedDateTime);
    }


    @Test
    public void timeTest2() {
        // java.util.Date -> java.time.LocalDate
        String dateStr = "2018-08-02";
        LocalDate date = LocalDate.parse(dateStr.replace("-", ""), DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse(dateStr);
        LocalDate now = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        System.out.println("date:" + date);
        System.out.println("date2:" + date2);
        System.out.println("now:" + now);
        System.out.println("localDate:" + Period.between(date, now).getDays());
        System.out.println("localDate:" + Period.between(now, date).getDays());
        System.out.println("localDate:" + Period.between(now, now).getDays());
    }

    @Test
    public void mapTest() {
        List<String> pathList = Lists.newArrayList();
        pathList.add("/tmp/images/2018-08-08@1.jpg");
        pathList.add("/tmp/images/2018-08-05@2.jpg");
        pathList.add("/tmp/images/2018-08-02@3.jpg");
        pathList.add("/tmp/images/2018-08-10@4.jpg");
        System.out.println(Collections.max(pathList));
        System.out.println(Collections.min(pathList));

        LinkedHashMap<String, String> pathTimestampMap = new LinkedHashMap<>();
        pathTimestampMap.put("/tmp/images/2018-08-08.jpg", "2018-08-08");
        pathTimestampMap.put("/tmp/images/2018-08-05.jpg", "2018-08-05");
        pathTimestampMap.put("/tmp/images/2018-08-02.jpg", "2018-08-02");
        pathTimestampMap.put("/tmp/images/2018-08-10.jpg", "2018-08-10");
        System.out.println(Collections.max(pathTimestampMap.keySet()));
        System.out.println(Collections.min(pathTimestampMap.keySet()));

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("keywords", "123");
        map.put("keyword", "1234");
        if (map.get("keywords") != null && map.get("keyword") == null) {
            map.put("keyword", map.get("keywords"));
            map.remove("keywords");
        }
        System.out.println(map.toString());
    }

    @Test
    public void pathTest() {
        String path = "xlt-1533176343461/images/4216773-fd.jpg";
        int i = path.indexOf("images/");
        path = path.substring(i, path.length());
        System.out.println(i);
        System.out.println(path);

    }

    @Test
    public void zzTest() {
        String str = "[INFO][2018-04-23 10:29:08 911][http-nio-6900-exec-8 2018-04-24]";
        String regex = "\\d{4}-\\d{2}-\\d{2}?";

        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
        }
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
        dataMap.put("KEY", "VALUS");

        MyModel myModel = new MyModel();
        myModel.setDataList(dataList);
        myModel.setDataMap(dataMap);

//        MyDTO myDto = MyConverter.instance.domain2dto(myModel);
//        System.out.println(myDto);
    }

    @Test
    public void dateTest() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.YEAR, -1);//日期减1年
        rightNow.add(Calendar.MONTH, 3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR, 10);//日期加10天
        Date date = rightNow.getTime();
        System.out.println(date.toString());
    }

    @Test
    public void beanTest() throws InvocationTargetException, IllegalAccessException {
        Map<String, String> beanMap = Maps.newHashMap();
        beanMap.put("name", "xlt");
        beanMap.put("age", "12");
        beanMap.put("sex", "man");
        beanMap.put("isDead", "false");
        Bean bean = new Bean();
        BeanUtils.populate(bean, beanMap);
        List<Bean> beans = Lists.newArrayList();
        beans.add(new Bean("xlt1", 11, false));
        beans.add(new Bean("xlt2", 12, true));
        beans.add(new Bean("xlt3", 13, false));
        beans.add(new Bean("xlt4", 14, true));
        bean.setBeans(beans);
        Map<String, Object> objectMap = ObjectUtils.objectToMap(bean);
        Bean newBean = new Bean();
        BeanUtils.populate(newBean, objectMap);
        System.out.println(bean.toString());
        System.out.println(objectMap.toString());
    }

    @Test
    public void hashTest() {
        List<String> ips = Lists.newArrayList();
        ips.add("127.0.0.1");
        ips.add("127.0.0.2");
        System.out.println(
                Hashing.md5().newHasher()
                        .putLong(100L)
                        .putString(Joiner.on("").join(ips), Charsets.UTF_8)
                        .putString("yanan", Charsets.UTF_8)
                        .hash()
                        .toString()
        );
    }

    @Test
    public void splitTest() {
        String str = null;
        String[] split = str.split(";");
        System.out.println(split.toString());
    }

    @Test
    public void uuidTest() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        //时间戳指的就是从1970年1月1日0时0分0秒到现在时间的毫秒数
        long cm = System.currentTimeMillis();
        System.out.println(cm);
    }

    @Test
    public void listJsonTest() {
        ArrayList<Integer> strings = Lists.newArrayList(2, 3, 4);
        System.out.println(strings.toString());
    }

    @Test
    public void combineString() {
        final String str1 = "浙江省";
        final String str2 = "杭州市";
        final String str3 = "西湖区";
        final String str4 = null;
        final String combineString = (str1 + str2 + str3 + str4).replace("null", "");
        System.out.println(combineString);
    }

    @Test
    public void md5Test() {
        String md5 = Hashing.md5().hashString("666", Charset.forName("GBK")).toString();
        System.out.println(md5);
        System.out.println(new Date().getTime());
    }

    @Test
    public void loaclDateTest() {
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        System.out.println(localDate.toString());
        System.out.println(date.toString());
    }

    @Test
    public void modTest() {
//        System.out.println(5000012 % 100000);
        String isbn = "adfasdfe2we2qwe";
        Float price = 66.32F;
        System.out.println(String.format("%s-%d", isbn, price.longValue()));
        System.out.println(String.format("%s-%d", isbn, null));
        System.out.println(String.format("%s-%d", isbn, ""));
    }

    @Test
    public void md5Test2() throws Exception {
        String md5 = MD5Utils.EncoderByMd5("1234");
        System.out.println(md5);
        System.out.println(md5.length());
    }

}
