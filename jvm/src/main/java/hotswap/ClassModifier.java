package hotswap;

import common.ByteUtils;

/**
 * @Description 修改Class文件，暂时只提供修改常量池的功能
 * @Author li-yuanwen
 * @Date 2021/1/21 20:58
 */
public class ClassModifier {

    /**
     * Class文件中常量池起始偏移
     **/
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info常量的tag标志
     **/
    private static final int CONSTANT_UTF8_INFO = 1;

    /**
     * 常量池中11种常量所占的长度
     **/
    private static final int[] CONSTENT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classBtyes;

    public ClassModifier(byte[] classBtyes) {
        this.classBtyes = classBtyes;
    }

    /**
     * 修改常量池中CONSTANT_Utf8_info常量的内容
     *
     * @param oldStr 修改前的字符串
     * @param newStr 修改后的字符串
     * @return 修改结果
     **/
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        for (int i = 0; i < cpc; i++) {
            int tag = ByteUtils.bytes2Int(classBtyes, offset , u1);
            if (tag == CONSTANT_UTF8_INFO) {
                int len = ByteUtils.bytes2Int(classBtyes, offset + u1 , u2);
                offset += (u1 + u2);
                String str = ByteUtils.bytes2String(classBtyes, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] lenBytes = ByteUtils.int2Bytes(newStr.length(), u2);

                    classBtyes = ByteUtils.bytesReplace(classBtyes, offset - u2, u2, lenBytes);
                    classBtyes = ByteUtils.bytesReplace(classBtyes, offset, len, strBytes);
                    return classBtyes;
                }else {
                    offset += len;
                }
            }else {
                offset += CONSTENT_ITEM_LENGTH[tag];
            }
        }
        return classBtyes;
    }


    /**
     * 获取常量池中常量的数量
     * @return 常量池数量
     **/
    public int getConstantPoolCount() {
        return ByteUtils.bytes2Int(classBtyes, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}
