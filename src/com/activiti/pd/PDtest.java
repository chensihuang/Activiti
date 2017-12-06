package com.activiti.pd;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * 流程定义 ：
 * 	1.部署
 * 	2.查询部署
 * 	3.查看图片
 * 	4.删除部署
 * @author 60988
 *
 */
public class PDtest {
	/*
	 * 通过classpath路径进行部署
	 * 	涉及到的表 
 	 *		act_ge_bytearray:
	 *        1、英文解释
	 *           act:activiti
	 *           ge:general
	 *           bytearray:二进制
	 *        2、字段
	 *           name_:文件的路径加上名称
	 *           bytes_:存放内容
	 *           deployment_id_:部署ID
	 *        3、说明：
	 *             如果要查询文件(bpmn和png)，需要知道deploymentId
	 *      act_re_deployment
	 *        1、解析
	 *           re:repository
	 *           deployment:部署  用户描述一次部署
	 *        2、字段
	 *            ID_：部署ID  主键
	 *      act_re_procdef
	 *        1、解释
	 *            procdef: process definition  流程定义
	 *        2、字段
	 *            id_:pdid:pdkey:pdversion:随机数
	 *            name:名称
	 *            key:名称
	 *            version:版本号
	 *                如果名称不变，每次部署，版本号加1
	 *                如果名称改变，则版本号从1开始计算
	 *            deployment_id_:部署ID
	 */
	@Test
	public void testDeployFromClasspath(){
		ProcessEngine pe  =ProcessEngines.getDefaultProcessEngine();
		pe.getRepositoryService()
		.createDeployment()
		.addClasspathResource("qingjia.bpmn")
		.addClasspathResource("qingjia.png")
		.deploy();
	}
	/**
	 * 通过 inputstream完成部署
	 */
	@Test
	public void testDeployFromInputStream(){
		InputStream bpmnStream = this.getClass().getClassLoader().getResourceAsStream("qingjia.bpmn");
		//得到流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getRepositoryService()
		.createDeployment()
		.addInputStream("qingjia.bpmn", bpmnStream)
		.deploy();
	}
	
	/*
	 * 通过zipinputstream完成部署
	 */
	@Test
	public void zipinputstream(){
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("qingjia.zip");
		ZipInputStream zip =new ZipInputStream(in);
		//得到流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getRepositoryService()
		.createDeployment()
		.addZipInputStream(zip)
		.deploy();
		
	}
	
	/*
	 * 删除
	 */
	@Test
	public void delete(){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getRepositoryService()
		.deleteDeployment("1", true);
	}
	
	
}
