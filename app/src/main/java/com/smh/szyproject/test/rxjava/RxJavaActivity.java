package com.smh.szyproject.test.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.arialyy.aria.core.Aria;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.BaseObserver;
import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.other.Rx.ExceptionHandle;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.utilCode.Utils;
import com.smh.szyproject.test.build.CustomDialog;
import com.smh.szyproject.test.build.IDialogView;

import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author : smh
 * date   : 2020/9/3 10:43
 * desc   :
 */
public class RxJavaActivity extends BaseActivity implements View.OnClickListener {

    private String url = "http://i3.hoopchina.com.cn/user/289/2737289/13059794690.jpg";

    List<Student> students;
    @BindView(R.id.iv_img)
    ImageView imageView;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    //被观察者Observable是起点，观察者Observer是终点
    @Override
    public void init(Bundle savedInstanceState) {

    }


    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
//                method();
//                method1();
//                method4();
                method5FlatMap();
//                method6();
                break;
        }
    }

    /**
     * map--1----a
     * map--2----a!!!
     * map--1----b
     * map--2----b!!!
     * map--1----c
     * map--2----c!!!
     * 意思就是Observable.just再发射
     * 1、先走apply方法
     * 2、return Observable.just
     * 3、next里的s就是just发射过来的s + "!!!"
     */
    private void method6() {
        Observable.just("a", "b", "c").flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NotNull String s) throws Exception {
                L.e("1----" + s);
                return Observable.just(s + "!!!");
            }
        }).subscribe(new BaseObserver<String>() {
            @Override
            public void next(String s) {
                L.e("2----" + s);
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });
    }

    private void method5FlatMap() {
        //学生集合里有俩学生，每个学生有三门课
        List<Course> courses1 = new ArrayList<>();
        courses1.add(new Course("语文"));
        courses1.add(new Course("数学"));
        courses1.add(new Course("英语"));
        Student szy = new Student();
        szy.setName("A");
        szy.setCourseList(courses1);


        List<Course> courses2 = new ArrayList<>();
        courses2.add(new Course("物理"));
        courses2.add(new Course("化学"));
        courses2.add(new Course("生物"));

        Student zm = new Student();
        zm.setName("B");
        zm.setCourseList(courses2);
        students = new ArrayList<>();
        students.add(szy);
        students.add(zm);
        print();

    }

    private void print() {
        //打印所有学生的课程
//        for (Student student : students) {
//            for (Course course : student.getCourseList()) {
//                L.e("course:" + course.getName());
//            }
//        }

//        //使用flatMap
        Observable.just(students)
                .flatMap(new Function<List<Student>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<Student> students) throws Exception {
                        return Observable.just(students);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void next(Object o) {
                        List<Student> s = (List<Student>) o;
                        if (s != null) {
                            for (Student student : s) {
                                L.e("姓名" + student.getName());
                                //输出姓名
                            }
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                });


        //使用flatMap
        Observable.just(students)
                .flatMap(new Function<List<Student>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<Student> students) throws Exception {
                        return Observable.fromArray(students);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void next(Object o) {
                        List<Student> s = (List<Student>) o;
                        if (s != null) {
                            for (Student student : s) {
                                L.e("姓名" + student.getName());
                            }
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                });



    }

    public class Course {
        private String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public class Student {
        private String name;
        private List<Course> courseList = new ArrayList<>();

        public String getName() {
            return name;
        }

        public List<Course> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<Course> courseList) {
            this.courseList = courseList;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    //用rxJava下载
    private void method() {
        //path是下载路径，它是事件源，它发射流动
        //path本来是String类型，经过变换，变换成其他类型
        //最开始是String类型，要转换为bitmap类型，就修改，不然是object类型
        Observable.just(url)//just我是第二步执行
                .map(new Function<String, Bitmap>() {//map是第三步，拿到url之后开始执行事件
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        //这里处理耗时操作，访问网络啊什么的,返回的类型要是Bitmap
                        Bitmap bm = null;
                        URL iconUrl = new URL(s);
                        URLConnection conn = iconUrl.openConnection();
                        HttpURLConnection http = (HttpURLConnection) conn;
                        int length = http.getContentLength();
                        conn.connect();
                        // 获得图像的字符流
                        InputStream is = conn.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is, length);
                        bm = BitmapFactory.decodeStream(bis);
                        bis.close();
                        is.close();// 关闭流
                        return bm;
                    }
                })
                .subscribeOn(Schedulers.io())//给上游一个异步线程， 这个io代表要做异步耗时操作

                .observeOn(AndroidSchedulers.mainThread())// 给下游分配安卓主线程,拿到图片后干啥
                //接着开始订阅 ，订阅是把起点和重点关联起来
                //上面是起点
                .subscribe(
                        //括弧里面是终点Observer
                        new Observer<Bitmap>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                //我是第一步先执行,比上面的just还先执行
                            }

                            @Override
                            public void onNext(Bitmap bitmap) {
                                //我是第四步，成功拿到bitmap
                                imageView.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onError(Throwable e) {
                                L.e("出问题了onError");
                            }

                            @Override
                            public void onComplete() {
                                L.e("我是第五步最终点,只有执行我，才代表整个执行完");
                            }
                        }
                );
    }


    private void method4() {

        Function function = new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return null;
            }
        };


        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");

        Observable.just(strings).flatMap(function).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void next(Object o) {
                        L.e("" + o.toString());
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                });
    }


    private void method3() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        Observable.just(list).map((List<String> strings) -> {
            return Integer.parseInt(strings.get(0));
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Integer>() {
                    @Override
                    public void next(Integer integer) {
                        L.e("" + integer);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                    }
                });
    }


    private void method2() {

        Observable.just("a", "b", "c").map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return "??" + s;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<String>() {
            @Override
            public void next(String s) {
                L.e("" + s);
                //连续发射三次  输出结果 a b c
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });
    }


    private void method1() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        Observable.just(list).map(new Function<List<String>, Integer>() {
            @Override
            public Integer apply(List<String> strings) throws Exception {
                return Integer.parseInt(strings.get(0));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                L.e("我是:" + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
