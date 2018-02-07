/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.service;

import JProg.JProgAStatement;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 *
 * @author yaroslav
 */
public class JProgServAdminStep extends JProgAStatement {
    
    static Logger logger = Logger.getLogger(JProgServAdminStep.class);
    
    private String m_strFinishedStep;
    public String GetFinishedStep() { return m_strFinishedStep;}
    public void SetFinishedStep( String strNewStep) { m_strFinishedStep = strNewStep;}
    
    public JProgServAdminStep( String strStep) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_ADMIN_STEP);
        m_strFinishedStep = strStep;
    }
    
    public JProgServAdminStep( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_ADMIN_STEP);
        Element eAdmStep = el.element( "Admin.Step");
        m_strFinishedStep = null;
        try {
            m_strFinishedStep = eAdmStep.getText();
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input.");
        }
    }

    @Override
    public String GetAsString() {
        String strResult;
        strResult = "SERVICE. Административное сообщение о завершении этапа '" + m_strFinishedStep + "'";
        return strResult;
    }
    
    /*
    @Override
    public Document GetAsXML() {
        Document document = DocumentHelper.createDocument();
        //Element root = document.addElement( "Condition" );
        Element root = document.addElement( "Operation" );
        root.addElement( "Type").addText( "PAUSE");
        root.addElement( "Duration").addText( "" + m_nDuration);
        return document;
    }
    */

    @Override
    public void AddXMLStatement(Element root) {
        root.addElement( "Type").addText( "SERVICE.ADMIN.STEP");
        root.addElement( "Admin.Step").addText( m_strFinishedStep);
    }
}
