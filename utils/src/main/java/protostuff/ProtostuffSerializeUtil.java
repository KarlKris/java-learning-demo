package protostuff;

import cn.hutool.core.util.ClassUtil;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author li-yuanwen
 * @date 2021/12/13
 */
public class ProtostuffSerializeUtil {

    private static Logger logger = LoggerFactory.getLogger(ProtostuffSerializeUtil.class);

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }

    /**
     * 序列化.
     *
     * @param obj 需要序列化的obj
     */
    public static <T> byte[] serialize(T obj) {
        if (obj == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            return null;
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化.
     *
     * @param data  需要反序列化的数据
     * @param clazz 结果class
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null || data.length < 1) {
            logger.error("ProtostuffSerializeUtil.deserialize --> 反序列化对象，byte序列为空");
            return null;
        }
        try {
            Schema<T> schema = getSchema(clazz);
            T obj = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 原生protobuf序列化.
     *
     * @param obj 需要序列化的obj
     */
    public static <T> byte[] serializeProtoBuf(T obj) {
        if (obj == null) {
            logger.error("ProtostuffSerializeUtil.serializeProtoBuf --> 序列化对象为空！");
            return null;
        }
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            buffer.clear();
        }
    }

    /**
     * 原生protobuf反序列化.
     *
     * @param data  需要反序列化的数据
     * @param clazz 结果class
     */
    public static <T> T deserializeProtoBuf(byte[] data, Class<T> clazz) {
        if (data == null || data.length < 1) {
            logger.error("ProtostuffSerializeUtil.deserializeProtoBuf --> 反序列化对象，byte序列为空");
            return null;
        }
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtobufIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 序列化map.
     *
     * @param objMap 需要序列化的map
     * @return byte[]
     */
    public static byte[] serializeMap(Map<Object, Object> objMap) {
        if (objMap == null || objMap.isEmpty()) {
            logger.error("ProtostuffSerializeUtil.serializeMap --> 序列化map为空");
            return null;
        }
        MapWrapper wrapper = new MapWrapper(objMap);
        return serialize(wrapper);
    }

    /**
     * 反序列化map.
     *
     * @param data 需要反序列化的数据
     * @return map
     */
    public static Map<Object, Object> deserializeMap(byte[] data) {
        if (data == null || data.length < 1) {
            logger.error("ProtostuffSerializeUtil.deserialize --> 反序列化map，byte序列为空");
            return null;
        }
        MapWrapper wrapper = deserialize(data, MapWrapper.class);
        if (wrapper != null) {
            return wrapper.getMap();
        }
        return null;
    }

    /**
     * 序列化列表.
     *
     * @param objList 需要序列化的列表
     * @return 返回结果
     */
    public static <T> byte[] serializeList(List<T> objList) {
        if (objList == null || objList.isEmpty()) {
            logger.error("ProtostuffSerializeUtil.serializeList --> 序列化列表为空");
            return null;
        }

        Class<?> aClass = objList.get(0).getClass();
        if (ClassUtil.isAssignable(Map.class, aClass)) {
            @SuppressWarnings("unchecked")
            Schema<MapWrapper> schema = RuntimeSchema.createFrom(MapWrapper.class);
            LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
            ByteArrayOutputStream bos = null;
            List<MapWrapper> wrappers = objList.stream().map(obj -> new MapWrapper((Map<Object, Object>) obj)).collect(Collectors.toList());
            try {
                bos = new ByteArrayOutputStream();
                ProtostuffIOUtil.writeListTo(bos, wrappers, schema, buffer);
                return bos.toByteArray();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return null;
            } finally {
                buffer.clear();
                try {
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(aClass);
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            return bos.toByteArray();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            buffer.clear();
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 反序列化列表.
     *
     * @param data        需要反序列化的数据
     * @param targetClass Class
     * @return targetClass的列表
     */
    public static <T> List<T> deserializeList(byte[] data, Class<T> targetClass) {
        if (data == null || data.length == 0) {
            logger.error("ProtostuffSerializeUtil.deserializeList --> 序列化列表，byte序列为空");
            return null;
        }
        if (ClassUtil.isAssignable(Map.class, targetClass)) {
            Schema<MapWrapper> schema = RuntimeSchema.getSchema(MapWrapper.class);
            try {
                List<MapWrapper> mapWrappers = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(data), schema);
                return (List<T>) mapWrappers.stream().map(MapWrapper::getMap).collect(Collectors.toList());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        }

        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        try {
            return ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(data), schema);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Data
    private static class MapWrapper {
        private Map<Object, Object> map;

        public MapWrapper() {
            map = new HashMap<>(0);
        }

        public MapWrapper(Map<Object, Object> map) {
            this.map = map;
        }
    }
}
