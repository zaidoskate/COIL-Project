/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import java.util.ArrayList;
import logic.domain.Course;
import logic.domain.TakesACourse;
import logic.domain.User;

public interface TakesACourseManagerInterface {
    int insertTakesACourse(TakesACourse takesACourse);
    ArrayList<User> getUsersByCourse(Course course);
    ArrayList<Course> getCourseByUser(User user);
}
