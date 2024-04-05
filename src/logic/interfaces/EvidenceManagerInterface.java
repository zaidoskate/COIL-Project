/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.Evidence;

/**
 *
 * @author chima
 */
public interface EvidenceManagerInterface {
    public int uploadEvidence(Evidence evidence);
    public int obtainEvidence(Evidence evidence, String outputPath);
}
