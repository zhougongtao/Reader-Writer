package experiment.GUI;

import java.util.ArrayList;
import java.util.Random;

public class Message {
    public static ArrayList<String> listA = new ArrayList<>();  //单个字符队列
    public static ArrayList<String> listB = new ArrayList<>();  //多个字符串队列
    public static ArrayList<Integer> reading=new ArrayList<>();    //读取
    public static ArrayList<Integer> writing=new ArrayList<>();    //写作

    public static int blen = 12;                                //B组的长度


    public static void init() {                //初始化变量
        int jj = 'A', kk = 'A';
        String midd = "";                            //临时字符串

        for (int ii = 0; ii <= 25; ii++) {                //给A赋值
            listA.add(String.valueOf((char) jj));
            jj = jj + 1;
        }

        for (int ii = 0; ii <= blen - 1; ii++) {            //给B赋值
            midd = midd + (char) kk;
            midd = midd + ((char) (kk + 1));
            midd = midd + ((char) (kk + 2));

            listB.add(midd);
            midd = "";
            kk = kk + 1;
        }
        for (int ii = 0; ii <= 25; ii++) {                //给used赋值
            reading.add(0);
            writing.add(0);
        }

    }


    public static String tostring() {
        String temm = "";

        //ListB 的字符串全部输出出来
        for (int i = 0; i < listB.size(); i = i + 1) {
            if(reading.get(i)>0){
                temm = temm +"\""+listB.get(i)+"\"";
            } else if(writing.get(i)>0){
                temm = temm +"<"+listB.get(i)+">";
            }else{
                temm = temm +" " + listB.get(i) + " ";
            }
            temm += "   ";
        }

        return temm;                    //返回更改后的字符串
    }
    public static int randLen() {
        long randomNum = System.currentTimeMillis();
        return (int) (randomNum % (blen - 1 - 0) + 0);
    }
    public static String modify(int n) {
        String midd = "";                            //临时字符串

        int ra1, ra2, ra3;                        //三个随机数
        int min = 0;
        int max = 25;
        long randomNum = System.currentTimeMillis();
        ra1 = (int) (Math.random() * (max - min) + min);
        Random r = new Random(ra1);
        ra2 = r.nextInt(max);
        ra3 = (int) (randomNum % (max - min) + min);

        midd = midd + listA.get(ra1);
        midd = midd + listA.get(ra2);
        midd = midd + listA.get(ra3);

        listB.set(n, midd);
        int item=writing.get(n);
        writing.set(n, item+1);
        return midd;
    }

    //添加正在读标记
    public static int isreading() {
        int tem, temra;
        tem = (int) (Math.random() * (600 - 30) + 30);
        Random r = new Random(tem);
        temra = r.nextInt(blen);

        int item=reading.get(temra);
        reading.set(temra, item+1);
        return temra;
    }

    //取消读标记
    public static void noreading(int aa) {
        int item=reading.get(aa);
        reading.set(aa, item-1);
    }
    //取消读标记
    public static void nowriting(int aa) {
        int item=writing.get(aa);
        writing.set(aa, item-1);
    }

    // 读取确定位置的字符串
    public static String getNode(int n) {
        return listB.get(n);
    }

}


