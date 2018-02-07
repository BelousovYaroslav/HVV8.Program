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
public class JProgServPauseStatement extends JProgAStatement {
    
    static Logger logger = Logger.getLogger(JProgServPauseStatement.class);
    
    private int m_nDuration;
    public int GetDuration() { return m_nDuration;}
    public void SetDuration( int nNewDuration) { m_nDuration = nNewDuration;}
    
    public JProgServPauseStatement( int nDuration) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_PAUSE);
        m_nDuration = nDuration;
    }
    
    public JProgServPauseStatement( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_PAUSE);
        Element eDuration = el.element( "Duration");
        m_nDuration = 1000;
        try {
            m_nDuration = Integer.parseInt( eDuration.getText());
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input. Pause will be constructed as default 1 sec");
        }
    }

    @Override
    public String GetAsString() {
        String strResult;
        
        int nHour = m_nDuration / 3600000;
        int nMin = ( m_nDuration % 3600000) / 60000;
        int nSec = (( m_nDuration % 3600000) % 60000) / 1000;
            
        if( m_nDuration >= 3600000) {
            //more than hour
            strResult = "SERVICE. Пауза " + nHour + "ч.  ";
            if( nMin != 0)
                strResult += nMin + " мин.  ";
            if( nSec != 0)
                strResult += nSec + " сек.";
        }
        else if( m_nDuration >= 60000) {
            strResult = "SERVICE. Пауза " + nMin + " мин.  ";
            if( nSec != 0)
                strResult += nSec + " сек.";
        }
        else if( m_nDuration >= 1000) {
            strResult = "SERVICE. Пауза " + nSec + " сек.";
        }
        else {
            strResult = "SERVICE. Пауза " + m_nDuration + " мсек";
        }
         
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
        root.addElement( "Type").addText( "SERVICE.PAUSE");
        root.addElement( "Duration").addText( "" + m_nDuration);
    }
}
