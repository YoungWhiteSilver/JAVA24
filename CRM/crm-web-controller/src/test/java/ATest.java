import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/13
 */

public class ATest {

    @Test
    public void Atest() {

        String num = "123123123";
        //九亿 八千 七百 六十 五 万 四千 三百 二十 一
        char[] numChar = num.toCharArray();

        String  newNum = "";

       StringBuilder stringBuilder = new StringBuilder();

        int j = 0;

        for(int i = (numChar.length - 1); i >= 0; i --) {

            if('1' == numChar[i]) {
                numChar[i] = '一';
            }
            if('2' == numChar[i]) {
                numChar[i] = '二';
            }
            if('3' == numChar[i]) {
                numChar[i] = '三';
            }
            if('4' == numChar[i]) {
                numChar[i] = '四';
            }
            if('5' == numChar[i]) {
                numChar[i] = '五';
            }
            if('6' == numChar[i]) {
                numChar[i] = '六';
            }
            if('7' == numChar[i]) {
                numChar[i] = '七';
            }
            if('8' == numChar[i]) {
                numChar[i] = '八';
            }
            if('9' == numChar[i]) {
                numChar[i] = '九';
            }if('0' == numChar[i]) {
                numChar[i] = '零';
            }

           if (j == 1 || j==  5 || j == 9) {

               stringBuilder.insert(0, "十");

           }

           if(j== 2 || j ==6) {

               stringBuilder.insert(0, "百");

           }

           if(j == 3 || j== 7) {

               stringBuilder.insert(0, "千");
           }

           if(j == 4) {

               stringBuilder.insert(0, "万");

           }

           if(j == 8) {

               stringBuilder.insert(0, "亿");

           }

           stringBuilder.insert(0, numChar[i]);
           j ++;

       }

        System.out.println(stringBuilder);
    }

}
