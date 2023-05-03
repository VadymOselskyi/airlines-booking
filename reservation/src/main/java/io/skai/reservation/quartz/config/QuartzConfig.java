package io.skai.reservation.quartz.config;

import io.skai.reservation.quartz.SaveHistoryJob;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.text.ParseException;

@Configuration
@RequiredArgsConstructor
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class QuartzConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(SaveHistoryJob.class);
        jobDetailFactoryBean.setDescription("Invoke historyTicketService and kafkaProducer");
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean trigger(JobDetail jobDetail) throws ParseException {
        CronTriggerFactoryBean cronTriggerFactory = new CronTriggerFactoryBean();
        cronTriggerFactory.setJobDetail(jobDetail);
        cronTriggerFactory.setCronExpression("0 0 0/1 1/1 * ? *");
        cronTriggerFactory.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        cronTriggerFactory.afterPropertiesSet();
        return cronTriggerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job, DataSource quartzDataSource, SpringBeanJobFactory springBeanJobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setJobFactory(springBeanJobFactory);
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setDataSource(quartzDataSource);
        return schedulerFactory;
    }
}