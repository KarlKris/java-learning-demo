package com.algorithm;

//三色旗 （按B W R归并） 归并算法 归并排序
/*
 * 总结：
 * 有限条件归并问题
 * 按条件个数(这里是三个颜色的旗子)分组
 * 界定每个组的初始界限，其中初始界限中要有两个作为归并的指针（根据问题要求选）
 * 意思是这两个数相加为总个数，左边处理完一个元素，左边指针+1
 *       右边处理完一个元素，右指针-1，当两个指针相遇时，则问题解决
 * 剩下的分组的界限均为该组下一个属于该组的元素的下标
 * 具体界限值看问题要求而定
 * 这里是靠左边的为0，靠右边的为总数
 */
public class ThreeColorFlags {

    //交换x和y下标的元素
    public void swap(char[] flags, int x, int y) {
        char temp;
        temp = flags[x];
        flags[x] = flags[y];
        flags[y] = temp;
    }

    public String move(char[] flags) {
        int wFlag = 0; //类似左指针的意思。
        int bFlag = 0;//blue群组最右边的下标。意思是下一个blue元素要存储的下标
        int rFlag = flags.length - 1;
        //wFlag+rFlag=flags.length
        while (wFlag <= rFlag) {
            //如果左指针位置元素是while，则指针+1，代表将一个未处理的白色元素移至白色群组
            if (flags[wFlag] == 'W') {
                wFlag++;
                //如果左指针位置元素是blue，则与blue的下一个位置交换元素，代表将一个未处理的蓝色元素移至蓝色群组
                //wFlag+1表示处理掉一个未处理的元素；bFlag+1代表下一个blue元素要存储的下标
            } else if (flags[wFlag] == 'B') {
                swap(flags, bFlag, wFlag);
                bFlag++;
                wFlag++;
            } else {
                //此时flags[wFlag] == 'R'。
                //若rFlag下标的元素为red，则rFlag-1,代表处理完一个未处理的红色元素移至红色群组
                while (wFlag < rFlag && flags[rFlag] == 'R') {
                    rFlag--;
                }
                //若rFlag下标的元素不为red，则rFlag下标与wFlag下标元素交换
                //这意味着将一个非red元素移入白色群组，red元素移至红色群组
                //接下来的flags[wFlag]会满足上面其中一条条件
                swap(flags, rFlag, wFlag);
                //代表处理完一个未处理的红色元素（flags[wFlag] == 'R'）移至红色群组
                rFlag--;
            }
        }
        return new String(flags);
    }

    public static void main(String[] args) {
        String flags = "RWBRWBRWBWWRW";
        ThreeColorFlags t = new ThreeColorFlags();
        flags = t.move(flags.toCharArray());
        System.out.println(flags);

    }


}
