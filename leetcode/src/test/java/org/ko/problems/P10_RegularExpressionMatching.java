package org.ko.problems;

/**
 * Implement regular expression matching with support for '.' and '*'.
 */
public class P10_RegularExpressionMatching {

    public boolean isMatch(String s,String p){
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        //初始化dp表
        int[][] dp = new int[pCharArray.length+1][sCharArray.length+1];
        for(int i = 0;i <sCharArray.length+1;i++){
            dp[pCharArray.length][i] = 2; //false
        }
        dp[pCharArray.length][sCharArray.length] = 1;//true

        //填表
        boolean flagStar = false; //记录*号
        int matchedIndex = sCharArray.length;
        for(int i = pCharArray.length-1; i>-1;i--){
            if(pCharArray[i] == '*'){
                flagStar = true;
                for(int k = 0;k < matchedIndex+1;k++){
                    dp[i][k] = dp[i+1][k];
                }
                continue;
            }
            if(flagStar){
                boolean flagNextMatched = false;
                for(int j = matchedIndex; j > -1;j--){
                    if(flagNextMatched == true){
                        if(pCharArray[i] == '.'){
                            dp[i][j] = 1;
                        }else if(pCharArray[i] == sCharArray[j]){
                            dp[i][j] = 1;
                        }else{
                            if(dp[i+1][j] == 1){
                                flagNextMatched = true;
                                dp[i][j] = 1;
                            }else{
                                flagNextMatched = false;
                                dp[i][j] = 2;
                            }
                        }
                    }else{
                        if(dp[i+1][j] == 1){
                            flagNextMatched = true;
                            dp[i][j] = 1;
                        }else{
                            flagNextMatched = false;
                            dp[i][j] = 2;
                        }
                    }
                }
            }else{
                matchedIndex--;
                for(int j = matchedIndex; j > -1; j--){
                    if(dp[i+1][j+1] == 1 &&(pCharArray[i]=='.' || pCharArray[i] == sCharArray[j]) ){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = 2;
                    }
                }
            }
            flagStar = false;

        }
        return dp[0][0] == 1 ? true : false;
    }

}
