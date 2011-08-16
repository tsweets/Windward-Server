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

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.test.framework.JerseyTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tsweets
 * Date: 6/19/11
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class JAXBTest extends JerseyTest {
    // TODO Remove Hard Coded Paths
    public JAXBTest() throws Exception {
        super("org.beer30.winsvr.resources");
    }


    @Test
    public void WindwardTest() throws Exception {

        File reportFile = new File("/tmp/report.pdf");
        FileUtils.deleteQuietly(reportFile);
        assertFalse(reportFile.exists());

        WindwardDocMsg msg = new WindwardDocMsg();
        msg.setTemplateName("QdiaNoticeTemplate.rtf");

        msg.setTemplate(FileUtils.readFileToByteArray(new File("/Users/tsweets/IdeaProjects/Windward-Server/src/main/resources/QdiaNoticeTemplate.rtf")));

        File dataFile = new File("/Users/tsweets/IdeaProjects/Windward-Server/src/main/resources/data.xml");
        String xmlData = FileUtils.readFileToString(dataFile);
        msg.setXmlData(xmlData);

        FormDataMultiPart mp = new FormDataMultiPart();
        mp.bodyPart(new FormDataBodyPart(FormDataContentDisposition.name("msg").fileName("msg").build(), msg, MediaType.APPLICATION_XML_TYPE));
        WebResource webResource = resource().path("windward/XML");

        InputStream output = webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(InputStream.class, mp);
        assertNotNull(output);

        FileOutputStream report = new FileOutputStream("/tmp/report.pdf");
        IOUtils.copy(output,report);

        assertTrue(reportFile.exists());
    }
}
