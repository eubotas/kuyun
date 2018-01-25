package com.kuyun.eam.admin.controller.manager;

import com.kuyun.eam.admin.util.TicketJob;
import com.kuyun.eam.util.QuartzUtil;

public class TestSchedule {

    public static void main(String[] args) throws Exception{
    TicketJob job=new TicketJob(1);
    QuartzUtil q=new QuartzUtil(job);
    q.run();
    }
}
