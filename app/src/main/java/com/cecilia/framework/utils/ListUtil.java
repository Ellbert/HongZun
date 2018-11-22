package com.cecilia.framework.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * List Utils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-7-22
 */
public class ListUtil {

    /**
     * default join separator
     **/
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    private ListUtil() {
        throw new AssertionError();
    }

    /**
     * 获取集合数量
     *
     * @param sourceList 集合
     * @return 集合数大于0时返回数据量，否则返回0
     */
    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     * 判断集合是否为null或集合数量是否为0
     *
     * @param sourceList 集合
     * @return 集合数量大于0：false 反之：true
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * 判断两个集合是否是一样的
     *
     * @param actual   集合1
     * @param expected 集合2
     * @return 一样：true,不一样：false
     */
    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if (actual == null) {
            return expected == null;
        }
        if (expected == null || actual.size() != expected.size()) {
            return false;
        }
        for (int i = 0; i < actual.size(); i++) {
            if (!ObjectUtil.isEquals(actual.get(i), expected.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 集合的ToString,以“,”分隔
     *
     * @param list 集合
     * @return 表以“,”字符分隔。如果列表是空的，返回""
     */
    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * 集合的ToString,以separator分隔
     *
     * @param list      集合
     * @param separator 分隔符
     * @return 列表以separator字符分隔符。如果列表是空的，返回""
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    /**
     * 集合的ToString,以separator分隔
     *
     * @param list      集合
     * @param separator 分隔符
     * @return 列表以separator字符串分隔符。如果列表是空的，返回""
     */
    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    /**
     * 添加对象到集合去重复
     *
     * @param sourceList 集合
     * @param entry      对象
     * @return 集合为空或集合中已经包含当前要添加的对象：false.反之true
     */
    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return (sourceList != null && !sourceList.contains(entry)) && sourceList.add(entry);
    }

    /**
     * 把entryList集合数据加入到sourceList集合中，并且去掉重复项目
     *
     * @param sourceList 原来的集合
     * @param entryList  要加入的集合
     * @return 添加条目数量
     */
    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }
        int sourceCount = sourceList.size();
        for (V entry : entryList) {
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }

    /**
     * 删除列表中的重复项
     *
     * @param sourceList 集合
     * @return 被删除条目数量
     */
    public static <V> int distinctList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return 0;
        }
        int sourceCount = sourceList.size();
        int sourceListSize = sourceList.size();
        for (int i = 0; i < sourceListSize; i++) {
            for (int j = (i + 1); j < sourceListSize; j++) {
                if (sourceList.get(i).equals(sourceList.get(j))) {
                    sourceList.remove(j);
                    sourceListSize = sourceList.size();
                    j--;
                }
            }
        }
        return sourceCount - sourceList.size();
    }

    /**
     * 添加一个非空的对象到集合中
     *
     * @return 如果集合或值为空时，返回false,反之true
     */
    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return (sourceList != null && value != null) && sourceList.add(value);
    }

    /**
     * @see ArrayUtil#getLast(Object[], Object, Object, boolean) 默认为null,返回最后下个值
     */
    @SuppressWarnings("unchecked")
    public static <V> V getLast(List<V> sourceList, V value) {
        return (sourceList == null) ? null : (V) ArrayUtil.getLast(sourceList.toArray(), value, true);
    }

    /**
     * @see ArrayUtil#getNext(Object[], Object, Object, boolean) 默认为null, 返回下一个值
     */
    @SuppressWarnings("unchecked")
    public static <V> V getNext(List<V> sourceList, V value) {
        return (sourceList == null) ? null : (V) ArrayUtil.getNext(sourceList.toArray(), value, true);
    }

    /**
     * list反序
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }
        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }
}
