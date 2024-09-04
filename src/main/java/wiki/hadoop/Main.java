package wiki.hadoop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.system.SystemUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    static String baseDir;
    static final String docsPath = "/Users/mac/IdeaProjects/private-blog/docs";

    public static void main(String[] args) {
        // Determine base directory based on OS
        if (SystemUtil.getOsInfo().isMac()) {
            args = new String[]{"/Users/mac/personal/markdown/"};
        }
        baseDir = args[0];

        // Process all files and directories
        Arrays.stream(FileUtil.ls(baseDir))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        if (!file.getName().matches(".*[!！].*")) {
                            System.out.println("Processing directory: " + file.getName());
                            processDirectory(file);
                        }
                    } else if (file.isFile()) {
                        System.out.println("Processing file: " + file.getName());
                        processFile(file);
                    } else {
                        System.out.println("Unknown file type: " + file.getName());
                    }
                });
    }

    public static void processDirectory(File dir) {
        Arrays.stream(dir.listFiles())
                .forEach(file -> {
                    if (file.isDirectory()) {
                        System.out.println("Processing subdirectory: " + file.getName());
                        processDirectory(file);
                    } else if (file.isFile()) {
                        processFile(file);
                    } else {
                        System.out.println("Unknown file type in directory: " + file.getName());
                    }
                });
    }

    public static void processFile(File file) {
        String relativePath = file.getPath().replace(baseDir, "");

        if (relativePath.endsWith(".md")) {
            if (!file.getName().matches("^\\d{2}.*")) {
                System.out.println("Skipping non-formal file: " + file.getName());
                return;
            }

            System.out.println("Processing Markdown file: " + file.getName());
            String content = FileUtil.readString(file, StandardCharsets.UTF_8);
            String newContent = String.format("---\ntitle: %s\ndate: %s\nsource: %s\n---\n%s",
                    file.getName(),
                    DateUtil.date().toString("yyyy-MM-dd"),
                    file.getName(),
                    content);

            String outputPath = docsPath + File.separator + "source/_posts/" + relativePath;
            FileUtil.writeUtf8String(newContent, outputPath);
        }

        System.out.println("Processing images in file: " + file.getPath());
        processImages(file);
    }

    public static void processImages(File file) {
        String parentPath = file.getParentFile().getPath();

        FileUtil.readLines(file, StandardCharsets.UTF_8).stream()
                .filter(line -> line.matches(".*!\\[.*\\]\\(.*\\).*"))
                .forEach(line -> {
                    if (line.contains(".assets")) {
                        String imageName = line.split("\\(")[1].split("\\)")[0];
                        String fullImagePath = parentPath + File.separator + imageName;

                        if (FileUtil.exist(fullImagePath)) {
                            System.out.println("Image found: " + imageName);
                            FileUtil.copy(fullImagePath, docsPath + File.separator + "source/" + imageName, true);
                        } else {
                            System.out.println("Image not found: " + imageName);
                        }
                    }
                });
    }
}
