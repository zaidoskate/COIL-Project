/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.LogicException;
import logic.domain.FinalDocumentation;
/**
 *
 * @author zaido
 */
public interface FinalDocumentationManagerInterface {
    
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    
}
