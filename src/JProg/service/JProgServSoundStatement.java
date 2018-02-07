/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.service;

import JProg.JProgAStatement;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public class JProgServSoundStatement extends JProgAStatement {
    static Logger logger = Logger.getLogger( JProgServSoundStatement.class);
    
    private String m_strWavFile;
    public String GetWavFile() { return m_strWavFile;}
    public void SetWavFile( String strNewWavFile) { m_strWavFile = strNewWavFile;}
    
    public JProgServSoundStatement( String strWavFile) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_SOUND);
        m_strWavFile = strWavFile;
    }

    public JProgServSoundStatement( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_SERVICE_SOUND);
        Element eWavFile = el.element( "WavFile");
        m_strWavFile = eWavFile.getText();
    }
    
    @Override
    public String GetAsString() {
        String strResult = "SERVICE. Звуковой сигнал \"" + m_strWavFile + "\"";
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
        root.addElement( "Type").addText( "SERVICE.SOUND");
        root.addElement( "WavFile").addText( m_strWavFile);
    }
}
