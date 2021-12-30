package com.yzc.springcloud.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;


@Component
public class MyJobTest extends IJobHandler {


    @XxlJob(value = "myJobTest")
    public ReturnT<String> execute(String s) throws Exception {
        int [][] what = new int[11][11];
        what[1][2]=3;
        what[2][2]=3;
  for(int[] row:what) {
      for(int item:row) {
          System.out.println(item);
      }
  }
        return null;
    }
}
