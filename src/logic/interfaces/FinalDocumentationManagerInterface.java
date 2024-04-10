/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.FinalDocumentation;
/**
 *
 * @author zaido
 */
public interface FinalDocumentationManagerInterface {
    
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation);
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation);
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation);
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation);
    
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath);
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath);
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath);
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath);
    
}
