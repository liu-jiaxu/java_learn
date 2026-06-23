package main.a2_Basic_Algorithms.a5_hashtable;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: HashTable
 * Package: main.a2_Basic_Algorithms.a5_hashtable
 * Description:哈希表类
 *
 * @Author: zgh296
 * @Create: 2023/6/25 - 9:23
 * @Version: v1.0
 */
public class HashTable {

    // 节点类
    static class Entry {

        int hash; // 哈希码
        Object key; // 键
        Object value; // 值
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

    }

    Entry[] table = new Entry[16]; // 选2^n作为数组大小
    int size = 0; // 元素个数
    float loadFactor = 0.75f; // 12：阈值，0.75f：负载因子
    int threshold = (int) (loadFactor * table.length); // 阈值(数组所有元素数量超过12时自动扩容)

    /* 求模运算替换为位运算
        - 前提：数组长度是 2 的 n 次方
        - hash % 数组长度 等价于 hash & (数组长度-1)
            &：按位与，性能优于取模
     */
    // 二进制按位与和取模规律： m % 2^n == m & (2^n - 1)

    // 根据 hash 码获取 value
    Object get(int hash, Object key) {
        // 求数据存储索引位置，按位与性能优于取模
        int idx = hash & (table.length - 1);
        // 当前索引未存储任何元素，不用继续查找
        if (table[idx] == null) {
            return null;
        }
        // 当前位置有数据存储，则获取链表头部，遍历即可
        Entry p = table[idx];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    // 向 hash 表存入新 key value，如果 key 重复，则更新 value
    void put(int hash, Object key, Object value) {
        /*
        思路
            1.当前数组位置无数据，直接新建链表节点插入即可
            2.当前数组位置有数据，遍历查找链表
                (1)链表中存在索引，则直接替换索引key的value值即可
                (2)链表中未找到索引key，在链表最后插入新数据即可
         */
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            // 1. idx 处有空位, 直接新增
            table[idx] = new Entry(hash, key, value);
        } else {
            // 2. idx 处无空位, 沿链表查找 有重复key更新，否则新增
            Entry p = table[idx];
            while (true) {
                if (p.key.equals(key)) {
                    p.value = value; // 更新
                    return;
                }
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            /*
            hashMap为什么使用尾插法？
                https://www.zhihu.com/question/446629921
             */
            p.next = new Entry(hash, key, value); // 新增
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    // 扩容方法
    /*
        补：从JDK1.8开始，在HashMap里面定义了一个常量TREEIFY_THRESHOLD，默认为8。当链表中的节点数量大于
    TREEIFY_THRESHOLD时，链表将会考虑改为红黑树
     */
    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i]; // 拿到每个链表头
            if (p != null) {
            /*
                拆分链表，移动到新数组，拆分规律
                * 一个链表最多拆成两个
                * hash & table.length == 0 的一组
                * hash & table.length != 0 的一组
                                          p
                0->8->16->24->32->40->48->null
                            a
                0->16->32->48->null
                        b
                8->24->40->null
             */
                // 两个新链表
                Entry a = null;
                Entry b = null;
                // 链表头指针
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    // 分组
                    // p.hash & table.length：化成二进制看倒数第n位(数组长度：2^n)，第n位变为1说明要分到新组中
                    if ((p.hash & table.length) == 0) {
                        // 第一次插入和后几次插入的情况
                        if (a != null) {
                            a.next = p;
                        } else {
                            aHead = p;
                        }
                        a = p; // 分配到a
                    } else {
                        if (b != null) {
                            b.next = p;
                        } else {
                            bHead = p;
                        }
                        b = p; // 分配到b
                    }
                    p = p.next;
                }
                // 规律： a 链表保持索引位置不变，b 链表索引位置+table.length
                if (a != null) {
                    a.next = null;
                    newTable[i] = aHead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bHead;
                }
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    // 根据 hash 码删除，返回删除的 value
    Object remove(int hash, Object key) {
        /*
        思路
            1.当前数组位置无数据 / 链表遍历完仍未找到删除元素，直接返回null
            2.当前数组位置有数据，遍历查找链表
                (1)待删除元素为链表头，直接让数组索引位置 = 删除元素的下一个元素即可
                (2)待删除元素不为链表头，正常的链表删除操作，让其前一个元素指针指向删除元素的后一个元素
         */
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry p = table[idx];
        Entry prev = null;
        while (p != null) {
            if (p.key.equals(key)) {
                // 找到了, 删除
                if (prev == null) { // 链表头
                    table[idx] = p.next;
                } else { // 非链表头
                    prev.next = p.next;
                }
                size--;
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    /*
    1.什么叫做 hash算法，常见的 hash 算法有哪些？
        hash算法是一种将任意长度的数据通过一个算法，变成固定长度数据的过程，这个固定长度的数据就是hash值。
    hash算法可以将任意大小的数据压缩到固定大小的值。常见的hash算法有MD5、SHA1、SHA256、SHA512、CRC32等。
    其中，MD5和SHA系列算法是最常用的hash算法。这些算法在计算hash值时，都考虑了原始数据的每一个字节，一旦改
    动原始数据的任何一个字节，所得到的hash值都会有明显地不同。因此，hash算法被广泛应用于数据完整性校验和加密。
    2.一个好的哈希算法应该满足以下几个条件：
        1.均匀性(Uniformity)：对于任意的输入,输出应尽可能地离散均匀,也就是输入的数据经过哈希算法处理后，分布到哈希表中
    的各个位置的概率越接近于均匀分布，这样可以使得哈希表中的数据分布更加平均，减少冲突，提高哈希查询和插入的效率。
        2.碰撞概率(Collision Probability)：哈希算法需要保证碰撞概率尽可能地小，也就是尽可能地避免哈希冲突。哈希冲突
    会导致哈希表中各链表的长度增加，直接影响散列表的性能。
        3.散列性(Scalability)：哈希算法应该能够支持散列表的扩容、收缩等操作，而不会对散列表中已有的数据造成影响。
        4.易于计算(Computational Complexity)：哈希算法的计算复杂度应尽可能地小，也就是说哈希算法应该是简单易懂、易于实现的。
        5.抗攻击(Resilience)：哈希算法应该能够抵御常见的哈希攻击和编码攻击,比如碰撞攻击和人为构造输入攻击等。
        6.除此之外，还有其他一些要考虑的因素，例如哈希计算速度、内存占用率、哈希表的装载因子等等。
     */

    /*
     * Object 的 hashCode 方法默认是生成随机数作为 hash 值（会缓存在对象头当中）
     * 缺点是包含相同值的不同对象，他们的 hashCode 不一样，不能够用 hash 值来反映对象的值特征
     * 因此诸多子类都会重写 hashCode 方法
     */
    public static int hash(Object key) {
        // 将字符串类型做murmur哈希转换
        if (key instanceof String k) {
            return Hashing.murmur3_32().hashString(k, StandardCharsets.UTF_8).asInt();
        }
        int hash = key.hashCode();
        // 为什么要和高16位异或运算：区分32位二进制时，不再只看后几位了，将高位的二进制也考虑进来
        /*
        例：524290 和 2
            两者没有异或运算时，后四位相同，这样得到的hash码也相同，若异或则考虑了高16位，减少了相同的概率
                524290 ： 0000 0000 0000 1000 0000 0000 0000 0010
                   异或：  右移的高16位   0000 0000 0000 1000
                          原二进制低16位 0000 0000 0000 0010
                          异或          0000 0000 0000 1010
                2      ： 0000 0000 0000 0000 0000 0000 0000 0010
                   异或：  0000 0000 0000 0010
           可以看出两者异或不一样，得到的hash码也不同
           注：前提：数组大小为2^n
         */
        return hash ^ (hash >>> 16);
        // 如下是字符串的hash生成
        /*int hash = 0;
        for (int i = 0; i < key.toString().length(); i++) {
            char c = key.toString().charAt(i);
            hash = (hash << 5) - hash + c;
        }
        return hash;*/
    }

    Object get(Object key) {
        int hash = hash(key);
        return get(hash, key);
    }

    void put(Object key, Object value) {
        int hash = hash(key);
        put(hash, key, value);
    }

    Object remove(Object key) {
        int hash = hash(key);
        return remove(hash, key);
    }

    // 检查 hash 表的分散性
    public void print() {
        // sum数组表示哈希表，如下循环求得哈希表指定位置的元素个数
        int[] sum = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            while (p != null) {
                sum[i]++;
                p = p.next;
            }
        }
        // System.out.println(Arrays.toString(sum));
        Map<Integer, Long> result = Arrays.stream(sum).boxed()
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
            /*
                stream 转换为数据流
                boxed 封装为包装引用数据类型
                collect 分组
                groupingBy 分组，将数据流中相同的元素分为一组
                counting 统计分组后的个数
                result集合 key对应分组的数据 value对应每组元素的数量
             */
        System.out.println(result);
    }

    /*
    MurmurHash是一种非加密哈希函数。它通过对输入数据进行数学运算生成一个固定长度的输出值,称为哈希值。
    MurmurHash具有快速计算、低碰撞率和良好的随机性等特点,被广泛应用于哈希表、布隆过滤器、哈希集合等数据结构中。
    MurmurHash由Austin Appleby于2008年开发，最初用于Google的一些项目。它有多个版本，MurmurHash3是最新版本。
    MurmurHash3支持32位和128位哈希，具有如下特点:
        1.快速计算:相对于其他哈希函数具有更快的计算速度;
        2.低碰撞率：碰撞率极低，处理一亿个数据最多只有两个冲突；
        3.随机性强:能够很好地保持数据的随机性。
        注：使用时需要导包或添加坐标
     */

    public void print2() {
        int hash = Hashing.murmur3_32().hashString("abc", StandardCharsets.UTF_8).asInt();
        System.out.println(hash);
    }

    // 查看源代码
    Map<String,Integer> map = new HashMap<>();
    Map<String,Integer> map2 = new Hashtable<>();
    /*
    问题：
        1. 我们的代码里使用了尾插法，如果改成头插法呢？
             https://www.zhihu.com/question/446629921
        2. JDK 的 HashMap 中采用了将对象 hashCode 高低位相互异或的方式减少冲突，怎么理解
            两者没有异或运算时，后四位相同，这样得到的hash码也相同，若异或则考虑了高16位，减少了相同的概率(见230行)
        3. 我们的 HashTable 中表格容量是 2 的 n 次方，很多优化都是基于这个前提，能否不用 2 的 n 次方作为表格容量？
            2^n对应方法：按位与、拆分链表、高低位异或
                求余数时效率高(可以按位与)，但分散性不好(例如524290和2hash码相同)
            HashTable初始容量为11(质数)
                求余数效率低，但分散性好(不一定比2^n好)
        4. JDK 的 HashMap 在链表长度过长会转换成红黑树，对此你怎么看
                JDK的HashMap在链表长度过长时会自动将链表转换为红黑树，这是为了解决链表过长导致的性能问题。红黑树相
            比链表具有更高地查找效率，可以提高HashMap的性能。但是，红黑树相对于链表会占用更多的内存空间，因此在元素
            数量较少的情况下，使用红黑树反而会降低HashMap的性能。
     */

    // 两数之和：给定一个数组和指定值，从数组中找到任意两个数字使其之和等于指定值，且默认该数组中仅存在一组这样的值
    public int[] twoSum(int[] nums, int target) {
        /*
        思路：
            1.遍历数组，拿到每个数字x
            2.以target - x作为key到hash表中查找
                (1)若没找到，将x作为key，索引作为value存入hash表
                (2)若找到，返回x和它配对数的索引
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                return new int[]{i, map.get(y)};
            } else {
                map.put(x, i);
            }
        }
        return null;
    }

    // 计算指定字符串的最长子串
    // 例：abca -> 过程：a ab abc(下一步遇到重复字符a) bca -> length = 3
    public int lengthOfLongestSubstring(String s) {
        /*
        思路：
            设置首尾指针begin、end，每次移动尾指针，判断当前位置map中是否有相同的元素，没有
        则添加，有则改变其begin位置，最后利用两个指针的位置即可计算最大长度
         */
        HashMap<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                begin = Math.max(begin, map.get(ch) + 1);
            } else {
                map.put(ch, end);
            }
            // System.out.println(s.substring(begin, end + 1));
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }

    // 计算指定字符串的最长子串
    // 题目中说明 s 由英文字母、数字、符号和空格组成，因此它的范围是有限的（在 0 ~127 之内），可以用数组来替代 HashMap 优化
    public int lengthOfLongestSubstring2(String s) {
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map[ch] != -1) { // 重复时调整 begin
                begin = Math.max(begin, map[ch] + 1);
                map[ch] = end;
            } else { // 不重复
                map[ch] = end;
            }
            // System.out.println(s.substring(begin, end + 1));
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }

    // 将字母的异位词分组
    // 例：eat的异位词：eta aet ate tae tea
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
        思路：
            用一个key为String，value为List的map存储，先对字符串数组中的每个字符串进行排序，再创建集合
        分组存储每个字符串，若list存在则直接将遍历的字符串添加到指定list中，若map中没有list则表示首次
        添加，直接新建list，再将key和list关联，之后添加当前字符串到list即可
         */
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            /*if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }*/
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    // 判断有无重复数字
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length * 2); // 此处优化防止扩容
        int i = 0;
        for (int num : nums) {
            // 如果插入的 key 对应的 value 已经存在，则执行 value 替换操作，返回旧的 value 值，如果不存在则执行插入，返回 null。
            Integer put = map.put(num, i); // 此处优化后每次都会插入相同的integer对象i，不会每次都创建新的对象了
            if (put != null) {
                return true;
            }
        }
        return false;
    }

    // 找到不重复数字(每个重复数字仅会出现两次，且仅有一个不重复数字)
    // set集合方法
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                set.remove(num);
            }
        }
        return set.toArray(new Integer[0])[0];
    }

    // 找到不重复数字(每个重复数字仅会出现两次，且仅有一个不重复数字)
    // 异或方法(同0异1)
    public int singleNumber2(int[] nums) {
        int num = nums[0];
        // 相同数字异或为0,0和任何数字异或为数字本身，因此数组内所有数字异或后，最后得到的就是不重复的数字
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    // 判断字母异位词
    public boolean isAnagram(String s, String t) {
        return Arrays.equals(getKey(s), getKey(t));
    }
    private static int[] getKey(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            array[ch - 97]++;
        }
        return array;
    }

    // 找到字符串(全小写)中第一个不重复字符
    // 字符数组方法
    public int firstUniqChar(String s) {
        /*
        思路：
            1.创建一个大小为26的数组，用于判断字符串中26个字母出现的次数，数组下标即为
        字符型 - 'a'；
            2.之后遍历字符串，将字符串中每个字符出现的次数与数组匹配，若字符串首个字符出
        现的次数为1，则直接返回该下标即可，若遍历完后未找到则返回-1
         */
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            array[ch - 97]++;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (array[ch - 97] == 1) {
                return i;
            }
        }
        return -1;
    }

    // 找到字符串(全小写)中第一个不重复字符
    // hashmap方法
    public int firstUniqChar2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        //先统计每个字符的数量
        for (char ch : chars) {
            // 每次找到相同的字符都会修改map中的value为当前字符出现次数
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
        for (int i = 0; i < s.length(); i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 统计出现次数最多的单词(指定禁用词，多个单词次数最多且相同时保留后者！)
    public String mostCommonWord(String paragraph, String[] banned) {
        /*
        思路：
            1.将paragraph截取为一个个单词
            2.将单词加入map集合，单词本身作为key，出现次数作为value，同时避免禁用词
            3.在map集合中找到最大的value并返回key
         */
        // 截取段落为单词。先转换为小写，再用正则表达式排除字母(此处可忽略大写字母)
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        // 禁用词保存
        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        for (String key : split) {
            /*if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }*/
            // 判断禁用词，若是禁用词无需添加到map中
            if (!set.contains(key)) { // 如果此集合包含指定的元素，则返回true
                // lambda表达式：传递当时的键值，若v == null则传入1，否则传入上一次的v + 1
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        /*
        entrySet 遍历map集合的键值
        stream 转换为数据流
        max 求出数据流中的最大值
        Map.Entry.comparingByValue 指定数据流中的最大值以什么为标准
         */
        Optional<Map.Entry<String, Integer>> max = map.entrySet().stream().max(Map.Entry.comparingByValue());
        // 获取Optional对象中的key，没有则为null
        return max.map(Map.Entry::getKey).orElse(null);
    }

    // 统计出现次数最多的单词(指定禁用词，多个单词次数最多且相同时保留后者！)
    // 提升效率
    public String mostCommonWord2(String paragraph, String[] banned) {
        /*
        思路：
            1.将paragraph截取为一个个单词
            2.将单词加入map集合，单词本身作为key，出现次数作为value，同时避免禁用词
            3.在map集合中找到最大的value并返回key
         */
        // 禁用词保存
        Set<String> set = Set.of(banned);
        //set.add(""); // 此处添加空字符串判断或者在下面585行加入非空判断
        HashMap<String, Integer> map = new HashMap<>();
        // 将段落转换为字符数组
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            /*
                将字符数组中的字母依次添加到数据流中，若遇到非字母，说明这个单词结束了，将数据流
            作为key添加到map中，并用lambda表达式判断value(该字母出现的次数)
                注：此处对空格没有额外判断，需要加入空字符串判断(559行 / 585行)
             */
            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
            } else {
                String key = sb.toString();
                if (!set.contains(key)) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
                sb.setLength(0);
            }
        }
        // 段落仅有一个单词时，上述for循环只会将字母添加到数据流中，需要手动判断添加到map中
        if (sb.length() > 0) {
            String key = sb.toString();
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        Integer max = 0;
        String maxKey = null;
        // 遍历map集合，获取最大value值并返回
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > max && !Objects.equals(entry.getKey(), "")) {
                max = value;
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

}
