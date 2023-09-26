package com.wjd.algorithm.strings.reader;

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
    private static final String DIR = Objects.requireNonNull(FileStringsReader.class.getResource("")).getPath();
    /**
     * 文件名称
     */
    private final String fileName;

    public FileStringsReader(String fileName) {
        this.fileName = fileName;
    }

    public String[] read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIR, fileName)))) {
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
