package com.activiti.helloword;


import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 1.部署流畅
 * 2.启动流程实例
 * 3.请假人发出请假申请
 * 4.经纪人审批（查看任务）
 * 5.BOSS审批
 * @author 60988
 *
 */
public class HelloWorldTest {
	/*
	 * 部署流程
	 */
	@Test
	public void testDeploy(){
		//获得流程引擎
		ProcessEngine processEngine  =ProcessEngines.getDefaultProcessEngine();//默认流程引擎
		processEngine.getRepositoryService().createDeployment()
		.addClasspathResource("qingjia.bpmn")
		.addClasspathResource("qingjia.png")
		.deploy();
	}
	
	/*
	 * 启动流程实例
	 */
	
	@Test
	public void testStrat(){
		ProcessEngine processEngine  =ProcessEngines.getDefaultProcessEngine();
		processEngine.getRuntimeService()
		.startProcessInstanceById("qingjia:1:4");
	}
	
	/*
	 * 完成请假申请
	 */
	@Test
	public void testQingjia(){
		ProcessEngine processEngine  =ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService()
		.complete("604");
	}
	
	/*
	 * 范冰冰经纪人查询当前正在执行的任务
	 */
	@Test
	public void chaxun(){
		ProcessEngine processEngine  =ProcessEngines.getDefaultProcessEngine();
		List<Task> tasks = processEngine.getTaskService()
		.createTaskQuery()
		.taskAssignee("范冰冰经纪人")
		.list();
		for(Task task :tasks){
			System.out.println(task.getName());
		}
		
	}
	
	/*
	 * 范冰冰经纪人完成任务
	 */
	@Test
	public void wancheng(){
		ProcessEngine processEngine  =ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService()
		.complete("702");
	}
	
	
	/*
	 * BOSS审批
	 */
	@Test
	public void boss(){
		ProcessEngine processEngine  =ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService()
		.complete("802");
	}
	
}
