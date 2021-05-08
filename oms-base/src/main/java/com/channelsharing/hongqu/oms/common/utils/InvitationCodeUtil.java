package com.channelsharing.hongqu.oms.common.utils;


/**
 * update by liuhangjun on 2018/7/15.
 * <p>
 * 邀请码工具类
 */
public class InvitationCodeUtil {
    
    //验证码长度
    private int len = 6;
    
    //验证码字符列表，邀请码一旦生成，此字符顺序不能变化
    private static final char STUFFS[] = {
            'T', '6', 'C', 'I', '9', 'F', 'X', 'H',
            'D', 'J', 'K', 'L', 'M', '8', 'R', 'Q',
            'P', '2', 'A', 'U', 'V', 'W', 'G', 'Y',
            'E', 'S', '3', '7', '5', 'B', '4', 'N'};
    
    private int permutation;
    private int maxCombination;
    
    /**
     * 初始化邀请码长度
     * @param len
     */
    public InvitationCodeUtil(int len) {
        this.len = len;
        permutation = permutation(len);
        maxCombination = combination(STUFFS.length, len);
    }
    
    
    public int decode(String code) {
        if (code.length() != len) {
            throw new RuntimeException("invalid code");
        }
        char[] chars = new char[len];
        for (int i = 0; i < len; ++i) {
            chars[i] = code.charAt(i);
        }
        int com = combination(chars);
        int per = permutation(chars);
        return com * permutation + per;
    }
    
    public String encode(int val) {
        int com = val / permutation;
        if (com >= maxCombination) {
            throw new RuntimeException("id is too bigger");
        }
        int per = val % permutation;
        char[] chars = combination(com);
        chars = permutation(chars, per);
        return new String(chars);
    }
    
    private char[] combination(int com) {
        char[] chars = new char[len];
        int start = 0;
        int index = 0;
        while (index < len) {
            for (int s = start; s < STUFFS.length; ++s) {
                int c = combination(STUFFS.length - s - 1, len - index - 1);
                if (com >= c) {
                    com -= c;
                    continue;
                }
                chars[index++] = STUFFS[s];
                start = s + 1;
                break;
            }
        }
        return chars;
    }
    
    private char[] sort(char[] src) {
        char[] sort = new char[src.length];
        int index = 0;
        for (int i = 0; i < STUFFS.length; ++i) {
            if (find(src, STUFFS[i]) != -1) {
                sort[index++] = STUFFS[i];
            }
        }
        return sort;
    }
    
    private int combination(char[] chars) {
        int[] offset = new int[len];
        char[] sort = sort(chars);
        for (int i = 0; i < sort.length; ++i) {
            offset[i] = find(STUFFS, sort[i]);
            if (offset[i] == -1) {
                throw new RuntimeException("invalid code");
            }
        }
        int com = 0;
        for (int i = 0; i < offset.length; ++i) {
            if (i == 0) {
                if (offset[0] == 0) {
                    continue;
                }
                for (int n = 0; n < offset[0]; ++n) {
                    com += combination(STUFFS.length - n - 1, len - 1);
                }
                continue;
            }
            
            if (offset[i] - offset[i - 1] <= 1) {
                continue;
            }
            
            for (int n = offset[i - 1] + 1; n < offset[i]; ++n) {
                com += combination(STUFFS.length - n - 1, len - i - 1);
            }
        }
        
        return com;
    }
    
    private char[] permutation(char[] chars, int per) {
        char[] tmpChars = new char[chars.length];
        System.arraycopy(chars, 0, tmpChars, 0, chars.length);
        int[] offset = new int[chars.length];
        int step = chars.length;
        for (int i = chars.length - 1; i >= 0; --i) {
            offset[i] = per % step;
            per /= step;
            step--;
        }
        
        for (int i = 0; i < chars.length; ++i) {
            if (offset[i] == 0)
                continue;
            char tmp = tmpChars[i];
            tmpChars[i] = tmpChars[i - offset[i]];
            tmpChars[i - offset[i]] = tmp;
        }
        
        return tmpChars;
    }
    
    private int find(char[] chars, char ch) {
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == ch) {
                return i;
            }
        }
        return -1;
    }
    
    private int permutation(char[] chars) {
        char[] sort = sort(chars);
        int[] offset = new int[chars.length];
        for (int i = chars.length - 1; i >= 0; --i) {
            int f = find(chars, sort[i]);
            offset[i] = i - f;
            char tmp = chars[i];
            chars[i] = chars[i - offset[i]];
            chars[i - offset[i]] = tmp;
        }
        int per = 0;
        int step = 1;
        for (int i = 0; i < offset.length; ++i) {
            per = per * step + offset[i];
            step++;
        }
        return per;
    }
    
    private int combination(int n, int m) {
        int com = 1;
        for (int i = n - m + 1; i <= n; ++i) {
            com *= i;
        }
        for (int i = 2; i <= m; ++i) {
            com /= i;
        }
        return com;
    }
    
    private int permutation(int n) {
        int per = 1;
        for (int i = 2; i <= n; ++i) {
            per *= i;
        }
        return per;
    }
    
    
    public static void main(String[] args) {
        InvitationCodeUtil invitationCodeUtil = new InvitationCodeUtil(5);
        String tmp = invitationCodeUtil.encode(100000);
        System.out.println(tmp);
        
    }
}
