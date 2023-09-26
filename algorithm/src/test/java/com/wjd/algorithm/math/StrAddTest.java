package com.wjd.algorithm.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrAddTest {

    @Test
    void add() {
        String[] nums1 = {"243", "0", "9999999"};
        String[] nums2 = {"564", "0", "9999"};
        String[] expects = {"708", "0", "89990001"};
        for (int i = 0; i < nums1.length; i++) {
            String actual = new StrAdd().add(nums1[i], nums2[i]);
            assertEquals(expects[i], actual);
        }
    }

    @Test
    void reverseAdd() {
        String[] nums1 = {"7243", "243", "0"};
        String[] nums2 = {"564", "564", "0"};
        String[] expects = {"7807", "807", "0"};
        for (int i = 0; i < nums1.length; i++) {
            String actual = new StrAdd().reverseAdd(nums1[i], nums2[i]);
            assertEquals(expects[i], actual);
        }
    }

}