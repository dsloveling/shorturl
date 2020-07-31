package org.dsloveling.shorturl.convert.util;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-03 14:27
 */
@UtilityClass
public class ConversionUtils {

    private static final char ZERO = '0';

    private static final char LOWERCASE_START = 'a';

    private static final char UPPERCASE_START = 'A';

    private static final char OUTER_RANGE = '$';

    private static final long TEN = 10L;
    private static final long THIRTY_SIX = 36L;
    private static final long SIXTY_TWO = 62L;

    /**
     * 10进制整数转换为62进制字符串
     *
     * @param id
     * @return
     */
    public static String convert(long id) {
        long mask = 62L;
        long sub = id;
        long mod;
        StringBuilder buffer = new StringBuilder(8);
        while (sub >= 0L) {
            if (sub >= mask) {
                mod = sub % mask;
                sub /= mask;
                buffer.append(get62Character(mod));
            } else {
                buffer.append(get62Character(sub));
                break;
            }
        }
        return StrUtil.reverse(buffer.toString());
    }

    private static Character get62Character(long value) {
        if (value == 0L) {
            return ZERO;
        }
        if (value < TEN) {
            return (char) (value + ZERO);
        }
        if (value < THIRTY_SIX) {
            return (char) (LOWERCASE_START + value - TEN);
        }
        if (value < SIXTY_TWO) {
            return (char) (UPPERCASE_START + value - THIRTY_SIX);
        }
        return OUTER_RANGE;
    }

}
