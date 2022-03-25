package com.cn;

public class Main {
    
    public static void main(String[] args) {
        Main main = new Main();

        System.out.println(main.add("9","9"));
        
    }

    public String add(String a, String b) {
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        
        int len = Math.max(aChar.length, bChar.length);
        int up = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < len ; i ++) {
            int sum = (i >= aChar.length ? 0 : (aChar[i] - '0'))+ (i >= bChar.length ? 0 : (bChar[i] - '0'))+ up;
            int current = sum % 10;
            up = sum/10;
            sb.append(current);
        }
        if (up != 0) {
            sb.append(up);
        }
        
        char[] result = sb.toString().toCharArray();
        
        return new String(result);
        
    }
}