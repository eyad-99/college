package com.example.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollegeApp {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApp.class, args);
	}

}




/*



	<form th:action="@{/student/registerForm}"  method="GET">
			  <button type="submit" class="btn btn-info col-2">Save</button>
						
		</form>


*/
/*
<label>courses:
    <input type="checkbox" name="courses"
        th:each="course : ${availableCourses}"
        th:text="${course.name}"
        th:value="${course.id}"
        th:field="*{availableCourses}"
    />
    <br>
</label>
*/