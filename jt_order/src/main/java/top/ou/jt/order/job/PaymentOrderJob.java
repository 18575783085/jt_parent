/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: PaymentOrderJob
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 20:07
 * Description: 时间任务调度器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.order.job;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import top.ou.jt.order.mapper.OrderMapper;

/**
 * 〈一句话功能简述〉<br> 
 * 〈时间任务调度器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 20:07
 * @since 1.0.0
 */
public class PaymentOrderJob extends QuartzJobBean{
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //1.通过加载文件，获取spring的上下文对象
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getJobDetail();

        //2.这个对象能够获取OrderMapper实例，完成对应sql语句的调度
        applicationContext.getBean(OrderMapper.class).paymentOrderScan(new DateTime().minusDays(1).toDate());
    }
}
