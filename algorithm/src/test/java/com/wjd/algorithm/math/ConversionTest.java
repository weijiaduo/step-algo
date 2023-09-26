package com.wjd.algorithm.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionTest {

    @Test
    void fromBinaryStr() {
        Conversion conversion = new Conversion();
        String[] strs = new String[]{"1111011", "1011000100100010", "10010001010001", "1001"};
        int[] expects = new int[]{123, 45346, 9297, 9};
        for (int i = 0; i < strs.length; i++) {
            int actual = conversion.fromBinaryStr(strs[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void fromOctalStr() {
        Conversion conversion = new Conversion();
        String[] strs = new String[]{"173", "130442", "22121", "11", "0130442", "022121", "011"};
        int[] expects = new int[]{123, 45346, 9297, 9, 45346, 9297, 9};
        for (int i = 0; i < strs.length; i++) {
            int actual = conversion.fromOctalStr(strs[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void fromHexStr() {
        Conversion conversion = new Conversion();
        String[] strs = new String[]{"7b", "b122", "2451", "9", "0xb122", "0x2451", "0x9"};
        int[] expects = new int[]{123, 45346, 9297, 9, 45346, 9297, 9};
        for (int i = 0; i < strs.length; i++) {
            int actual = conversion.fromHexStr(strs[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void toDoubleBinaryStr() {
        Conversion conversion = new Conversion();
        double[] vals = new double[] {0.625, 0.1, 2.625};
        String[] expects = new String[] {"0.101", "0.00011001100110011001100110011001", "10.101"};
        for (int i = 0; i < vals.length; i++) {
            String actual = conversion.toBinaryStr(vals[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void toBinaryStr() {
        Conversion conversion = new Conversion();
        int[] vals = new int[]{123, 45346, 9297, 9};
        String[] expects = new String[]{"1111011", "1011000100100010", "10010001010001", "1001"};
        for (int i = 0; i < vals.length; i++) {
            String actual = conversion.toBinaryStr(vals[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void toOctalStr() {
        Conversion conversion = new Conversion();
        int[] vals = new int[]{123, 45346, 9297, 9};
        String[] expects = new String[]{"173", "130442", "22121", "11"};
        for (int i = 0; i < vals.length; i++) {
            String actual = conversion.toOctalStr(vals[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void toHexStr() {
        Conversion conversion = new Conversion();
        int[] vals = new int[]{123, 45346, 9297, 9};
        String[] expects = new String[]{"7b", "b122", "2451", "9"};
        for (int i = 0; i < vals.length; i++) {
            String actual = conversion.toHexStr(vals[i]);
            assertEquals(expects[i], actual);
        }
    }

}