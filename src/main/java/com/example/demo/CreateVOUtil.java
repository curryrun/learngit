package com.example.demo;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateVOUtil {

    private static final String type_char = "char";

    private static final String type_date = "date";

    private static final String type_timestamp = "timestamp";

    private static final String type_int = "int";

    private static final String type_bigint = "bigint";
    
    private static final String type_Long = "Long";
    
    private static final String type_String = "String";
    
    private static final String type_Time = "Date";
    
    private static final String type_List = "List";

    private static final String type_text = "text";

    private static final String type_bit = "bit";

    private static final String type_decimal = "decimal";

    private static final String type_blob = "blob";

    private static final String type_double = "double";
    
    private final String VO_input_path = "C:/VOTest/input";
    
    private final String VO_output_path = "C:/VOTest";
    
    private final String VO_package = "com.puhui.app.api.vo.newApp";
    
    private String tableName = null;

    private String beanName = null;

    private String mapperName = null;

    private Connection conn = null;
    

    public void generate() throws ClassNotFoundException, SQLException, IOException {
        File file = new File(VO_input_path);
        File[] fileList = file.listFiles();
        List<String> fileNameList = new ArrayList<>();
        List<JSONObject> JsonList = new ArrayList<>();
        
        for(int i =0; i< fileList.length; i++){
            String fileName = fileList[i].getName();
            String contentStr = readFileByLines(VO_input_path + "/" + fileName);
            JSONObject contentObj = JSONObject.parseObject(contentStr);
            fileNameList.add(fileName);
            JsonList.add(contentObj);
        }
    
        buildVO(fileNameList, JsonList);
    }
    
    private static String fixFileName(String fileName){
        String[] fileNameArr = fileName.split("\\.");
        fileName = fileNameArr[0];
        fileName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
        return fileName;
    }
    
    public static String readFileByLines(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }
    
    private void buildVO(List<String> classNameList, List<JSONObject> outObjList) throws IOException {
        File folder = new File(VO_output_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for(int i = 0; i< classNameList.size(); ++i){
            String className = classNameList.get(i);
            File outFile = new File(VO_output_path, fixFileName(className) + ".java");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            bw.write("package " + VO_package + ";");
            bw.newLine();
            bw.newLine();
            bw.write("import java.util.List;");
    
            bw.newLine();
            bw.newLine();
            bw.write("public class " + fixFileName(className) + " " + "{");
            bw.newLine();
            bw.newLine();
            
            JSONObject oneObj = outObjList.get(i);
    
            for (Map.Entry<String, Object> entry : oneObj.entrySet()) {
                
                String paramName = entry.getKey();
                Object paramValueObj = entry.getValue();
                String paramValue = "";
                if(paramValueObj != null){
                    paramValue = String.valueOf(paramValueObj);
                }
                
    
                bw.newLine();
                bw.write("\t"+"  private " + getType(paramName, paramValue) + " " + paramName);
                System.out.println(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
            // ----------定义Mapper中的方法End----------
            bw.newLine();
            bw.write("}");
            bw.flush();
            bw.close();
        }
    }
    
    public static String getType(String paramName, String paramValue){
        if(paramName.contains("id") || paramName.contains("Id")){
            return type_Long;
        }
        if(paramName.contains("time") || paramName.contains("Time")){
            return type_Time;
        }
        if(paramValue.contains("[]")){
            return type_List;
        }
        try {
            Double.parseDouble(paramValue);
            return type_double;
        }catch (RuntimeException e){
            return type_String;
        }
    }

    public static void main(String[] args) {
        try {
            new CreateVOUtil().generate(); // 自动打开生成文件的目录 //
            // Runtime.getRuntime().exec("cmd /c start explorer //
            // G:\\mybatiestest\\");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}