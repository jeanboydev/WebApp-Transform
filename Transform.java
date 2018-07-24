import java.io.*;
import java.util.Map;


class Transform {

    private static final String[] IGNORE_SETTINGS = {".git"};

    private static String FROM_PATH = "";
    private static String OUT_PATH = "";

    public static void run(String from, String out) {
        FROM_PATH = from;
        OUT_PATH = out;
        readFiles(from);
    }

    private static void readFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String name = file.getName();
            System.out.println("====>" + name);

            boolean isNeedIgnore = false;
            for (String mark : IGNORE_SETTINGS) {
                if (mark.equals(name)) {
                    isNeedIgnore = true;
                    break;
                }
            }
            if (isNeedIgnore)
                return;

            File[] fileList = file.listFiles();
            if (fileList != null) {
                for (File item : fileList) {
                    readFiles(item.getAbsolutePath());
                }
            }
        }
        if (file.isFile()) {
            System.out.println("    |--" + file.getAbsolutePath());
            compiler(file);
        }
    }

    private static void compiler(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(RulesXML.SUFFIX_WECHAT) != -1) {
            System.out.println("        |--wxml--->" + file.getName());
            transform(file, RulesXML.SUFFIX_WECHAT, RulesXML.weChatToAlipay);
        } else if (fileName.lastIndexOf(RulesCSS.SUFFIX_WECHAT) != -1) {
            System.out.println("        |--wxss--->" + file.getName());
            transform(file, RulesCSS.SUFFIX_WECHAT, RulesCSS.weChatToAlipay);
        } else if (fileName.lastIndexOf(RulesJSON.SUFFIX_JSON) != -1) {
            System.out.println("        |--json--->" + file.getName());
            transform(file, RulesJSON.SUFFIX_JSON, RulesJSON.weChatToAlipay);
        } else {
            transform(file);
        }
    }

    private static void transform(File file) {
        String filePath = file.getAbsolutePath();
        int index = filePath.indexOf(FROM_PATH) + FROM_PATH.length();
        String suffixPath = filePath.substring(index, filePath.length());
        String outPath = OUT_PATH + suffixPath;
        System.err.println("        |--filePath--->" + filePath);
        System.err.println("        |--outPath--->" + outPath);
        copyFile(filePath, outPath);
    }

    private static void transform(File file, String suffix, Map<String, String> tagMap) {
        String filePath = file.getAbsolutePath();
        int index = filePath.indexOf(FROM_PATH) + FROM_PATH.length();
        String suffixPath = filePath.substring(index, filePath.length());
        String outPath = OUT_PATH + suffixPath.replace(suffix, tagMap.get(suffix));
        System.err.println("        |--filePath--" + suffix + "-->" + filePath);
        System.err.println("        |--outPath--" + suffix + "-->" + outPath);
        transformContent(filePath, outPath, tagMap);
    }

    private static void transformContent(String filePath, String outPath, Map<String, String> tagMap) {
        try {
            FileReader reader = new FileReader(filePath);
            char[] data = new char[1024];
            int rn = 0;
            StringBuilder sb = new StringBuilder();
            while ((rn = reader.read(data)) > 0) {
                String str = String.valueOf(data, 0, rn);
                sb.append(str);
            }
            reader.close();
            String str = sb.toString();
            if (tagMap != null) {//有tag，则替换
                for (String tag : tagMap.keySet()) {
                    str = str.replace(tag, tagMap.get(tag));
                }
            }

            //目录不存在，则创建
            File outFile = new File(outPath);
            File parentFile = outFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            outFile.createNewFile();

            FileWriter writer = new FileWriter(outPath);
            writer.write(str.toCharArray());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(String filePath, String outPath) {
        try {
            File from = new File(filePath);
            File to = new File(outPath);

            if (!from.exists()) return;
            File parentFile = to.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            to.createNewFile();

            FileInputStream ins = new FileInputStream(from);
            FileOutputStream out = new FileOutputStream(to);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }

            ins.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}