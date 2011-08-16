/* Windward Server - Copyright (C) 2011  Tony Sweets tony.sweets at gmail dot com */
/*
 *  This file is part of Windward Server.
 *
 *  Windward Server is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Windward Server is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Windward Server.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.beer30.winsvr;

import junit.framework.TestCase;
import net.windward.datasource.dom4j.Dom4jDataSource;
import net.windward.xmlreport.ProcessPdf;
import net.windward.xmlreport.ProcessReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: tsweets
 * Date: 8/7/11
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateSimpleReportTest extends TestCase {
    // TODO: Remove Hard Coded Paths

    public void testCreatePDF() throws Exception {
        File templateFile = new File("/Users/tsweets/IdeaProjects/Windward-Server/src/main/resources/QdiaNoticeTemplate.rtf");
        assertEquals("QdiaNoticeTemplate.rtf",templateFile.getName());

        InputStream template = new FileInputStream(templateFile);

        FileOutputStream report = new FileOutputStream("/tmp/report.pdf");

        File dataFile = new File("/Users/tsweets/IdeaProjects/Windward-Server/src/main/resources/data.xml");
        assertEquals("data.xml",dataFile.getName());
        InputStream xmlData = new FileInputStream(dataFile);


        // Init Windward
        ProcessReport.init();

        // Create New Doc
        ProcessPdf processor = new ProcessPdf(template, report);
        processor.processSetup();
        processor.processData(new Dom4jDataSource(xmlData),"");
        processor.processComplete();




    }
}
