/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.Student;

public interface StudentManagerInterface {
    int insertStudent(Student student);
    Student getStudentByIdUser(int id);
}
