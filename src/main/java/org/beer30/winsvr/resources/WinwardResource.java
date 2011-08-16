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
package org.beer30.winsvr.resources;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import net.windward.datasource.dom4j.Dom4jDataSource;
import net.windward.xmlreport.ProcessPdf;
import net.windward.xmlreport.ProcessReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beer30.winsvr.WindwardDocMsg;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: tsweets
 * Date: 8/8/11
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/windward")
public class WinwardResource  {
    protected final Log logger = LogFactory.getLog(getClass());

    @Path("XML")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @POST
    public InputStream post(@FormDataParam("msg") WindwardDocMsg msg, @FormDataParam("msg") FormDataContentDisposition contentDisposition) throws Exception {

        logger.info("Passed in Content Disposition Info: " + contentDisposition.toString());

        ByteArrayOutputStream report = new ByteArrayOutputStream();
        FileOutputStream template_temp = new FileOutputStream("/tmp/template-temp.rtf");
        template_temp.write(msg.getTemplate());
        template_temp.close();

        InputStream template = new ByteArrayInputStream(msg.getTemplate());

        InputStream xmlData = new ByteArrayInputStream(msg.getXmlData().getBytes("UTF-8"));

        // Init Windward
        ProcessReport.init();

        // Create New Doc
        ProcessPdf processor = new ProcessPdf(template, report);
        processor.processSetup();
        processor.processData(new Dom4jDataSource(xmlData),"");
        processor.processComplete();

        logger.info("Creating Document from: " + msg.getTemplateName() + " template");
        ByteArrayInputStream output = new ByteArrayInputStream(report.toByteArray());
        return output;
    }
}
