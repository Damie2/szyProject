package com.smh.szyproject.test.suanfaAlgorithm;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/8/3 10:07
 * desc   : 快速排序
 */
public class algorithm extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }


    @Override
    public void init(Bundle savedInstanceState) {
//        getWindow().getDecorView().postDelayed(() -> {
//            fastSort();
//        }, 2000);

//        fastSort();
    }

    private void fastSort() {

        int[] ints = new int[10];
        ints[0] = 5;
        ints[1] = 2;
        ints[2] = 1;
        ints[3] = 6;
        ints[4] = 50;
        ints[5] = 35;
        ints[6] = 4;
        ints[7] = 6;
        ints[8] = 20;
        ints[9] = 3;
        L.e("???");
        QuickSort(ints, 2, 9);
        L.e("123???");
        for (int i:ints){
            L.e(""+i);
        }
        L.e("???");
    }

    private void quickSort(int[] ints, int lo, int hi) {
        //将l0,h1两个副本进行拷贝
        int i = lo, j = hi;
        //声明基准值
        int temp;
        //如果划分长度大于等于2 hi大于lo
        if (i < j) {
            //取基准位左端端点
            temp = ints[i];
            //当hi不等于lo时循环
            while (i != j) {
                //当hi>lo并且ints[hi]>temp时循环
                while (j < i && ints[j] > temp) {
                    --j;
                }
                while (i < j && ints[i] < temp) {
                    ++i;
                }
                ints[j] = ints[i];
            }
            ints[i] = temp;
            quickSort(ints, lo, i - 1);
            quickSort(ints, i + 1, hi);
        }
    }


    void QuickSort(int R[], int lo, int hi) {
        int i = lo, j = hi;
        int temp;
        if (i < j) {
            temp = R[i];
            while (i != j) {
                while (j > i && R[j] > temp) --j;
                R[i] = R[j];
                while (i < j && R[i] < temp) ++i;
                R[j] = R[i];
            }
            R[i] = temp;
            QuickSort(R, lo, i - 1);
            QuickSort(R, i + 1, hi);
        }
    }

}
