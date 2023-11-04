package com.wjd.algorithm.strings.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 从文件中读取字符串
 *
 * @author weijiaduo
 * @since 2023/4/15
 */
public class FileStringsReader {

    /**
     * 文件目录
     */
    private final String dir;
    /**
     * 文件名称
     */
    private final String fileName;

    public FileStringsReader(Class<?> clazz, String fileName) {
        dir = Objects.requireNonNull(clazz.getResource("")).getPath();
        this.fileName = fileName;
    }

    public String[] read() {
        try (FileReader fr = new FileReader(new File(dir, fileName));
             BufferedReader reader = new BufferedReader(fr)) {
            List<String> list = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                String[] ts = line.trim().split("\\s+");
                Collections.addAll(list, ts);
                line = reader.readLine();
            }
            return list.toArray(new String[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
