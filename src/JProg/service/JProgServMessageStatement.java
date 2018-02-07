/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.service;

import JProg.JProgAStatement;
import static JProg.service.JProgServPauseStatement.logger;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public class JProgServMessageStatement extends JProgAStatement {
    private String m_strMessage;
    private String m_strFileToPlay;
    
    private String m_strReminderFileToPlay;
    private int m_nReminderPeriod;
    
    public String GetMessage() {
        return m_strMessage;
    }
    
    public String GetSoundWavFile() {
        return m_strFileToPlay;
    }
    
    public String GetReminderWav() {
        return m_strReminderFileToPlay;
    }
    
    public int GetReminderPeriod() {
        return m_nReminderPeriod;
    }
    
    public void SetMessage( String strNewMessage) {
        m_strMessage = strNewMessage;
    }
    
    public JProgServMessageStatement( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_MESSAGE);
        
        Element eMessage = el.element( "Message");
        m_strMessage = eMessage.getText();
        
        Element eWavFile = el.element( "WavFirst");
        m_strFileToPlay = eWavFile.getText();
        
        Element eWavPeriodicFile = el.element( "WavPeriodic");
        m_strReminderFileToPlay = eWavPeriodicFile.getText();
        
        Element ePeriod = el.element( "Period");
        m_nReminderPeriod = 10;
        try {
            m_nReminderPeriod = Integer.parseInt( ePeriod.getText());
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input. Remider period will be constructed as default 10 sec");
        }
    }
    
    public JProgServMessageStatement( String strMessage, String strFileToPlay,
                            String strReminder, int nReminder) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_MESSAGE);
        m_strMessage = strMessage;
        m_strFileToPlay = strFileToPlay;
        m_strReminderFileToPlay = strReminder;
        m_nReminderPeriod = nReminder;
    }
            
    @Override
    public String GetAsString() {
        String strResult;
        
        //if( m_strMessage.length() <= 10)
            strResult = "SERVICE. Сообщение " + "\"" + m_strMessage + "\"";
        //else
        //    strResult = "SERVICE. Сообщение " + "\"" + m_strMessage.substring( 0, 10)+ "...\"";
        
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    @Override
    public void AddXMLStatement( Element root) {
        root.addElement( "Type").addText( "SERVICE.MESSAGE");
        root.addElement( "Message").addText( m_strMessage);
        root.addElement( "WavFirst").addText( m_strFileToPlay);
        root.addElement( "WavPeriodic").addText( m_strReminderFileToPlay);
        root.addElement( "Period").addText( "" + m_nReminderPeriod);
    }
    
}
