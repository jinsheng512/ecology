package ecustom.util;

import weaver.conn.RecordSet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 2017-6-8.
 */
public class RecordSetUtil<T extends Object> {

    /**
     * 将RecordSet对象转换成实体对象，RecordSet.execute(sql).next()后执行。
     *
     * @param rs
     * @param clazz
     * @return
     * @throws Exception
     */
    public T toObject(RecordSet rs, Class<T> clazz) throws Exception {
        T o = clazz.newInstance();
        String[] colNames = rs.getColumnName();
        Field[] fields = clazz.getDeclaredFields();
        Method method;
        for (String colName : colNames) {
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(colName)) {
                    method = clazz.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), field.getType());
                    if (field.getType().getName().equals(Integer.class.getName())) {
                        method.invoke(o, CustomUtil.getInteger(rs.getString(field.getName())));
                    } else if (field.getType().getName().equals(String.class.getName())) {
                        method.invoke(o, rs.getString(field.getName()));
                    } else if (field.getType().getName().equals(Double.class.getName())) {
                        method.invoke(o, CustomUtil.getDouble(rs.getString(field.getName())));
                    }
                }
            }
        }
        return o;
    }

    /**
     * 将RecordSet对象转换成实体对象集合，RecordSet.execute(sql)后执行。
     * @param rs
     * @param clazz
     * @return
     * @throws Exception
     */
    public List<T> toObjectList(RecordSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<T>();
        while (rs.next()) {
            list.add(toObject(rs, clazz));
        }
        return list;
    }
}