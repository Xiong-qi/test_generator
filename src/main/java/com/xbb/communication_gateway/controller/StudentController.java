package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.Student;
import com.xbb.communication_gateway.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController{
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		Student student=simpleModel(Student.class);
		return easyuiPageResult(studentService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), student));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		Student student=simpleModel(Student.class);
		Boolean result=studentService.save(student);
		return result?AjaxJson.success().setData(student):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		Student student=simpleModel(Student.class);
		student.remove("studentname", "password");
		Boolean result=studentService.update(student);
		return result?AjaxJson.success().setData(student):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		Student student=simpleModel(Student.class);
		Boolean result=studentService.delete(student);
		return result?AjaxJson.success().setData(student):AjaxJson.failure();
	}

}
